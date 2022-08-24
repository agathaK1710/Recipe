package com.android.recipe.presentation.fragments

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.android.recipe.R
import com.android.recipe.databinding.FragmentProfileBinding
import com.android.recipe.presentation.RecipeApp
import com.android.recipe.presentation.RecipeViewModel
import com.android.recipe.presentation.UserViewModel
import com.android.recipe.presentation.ViewModelFactory
import com.android.recipe.presentation.adapters.RecipeAdapter
import com.android.recipe.presentation.fragments.fragmentContainers.MainContainerFragment
import javax.inject.Inject


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding is null")
    private var uri: Uri? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as RecipeApp).component
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RecipeViewModel::class.java]
    }

    private val userViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_KEY) { _, bundle ->
            bundle.getString(URI_STRING)?.let { Log.d(URI_STRING, it) }
            uri = Uri.parse(bundle.getString(URI_STRING))
        }
        Log.d("user", "ProfileFragment ${userViewModel.currentUser?.uid.toString()}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myRecipeAdapter = RecipeAdapter()
        binding.rvMyRecipes.adapter = myRecipeAdapter
        viewModel.favouriteRecipesList.observe(viewLifecycleOwner) {
            myRecipeAdapter.submitList(it)
        }
        binding.btnEdit.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container3, EditFragment.getInstance() ?: EditFragment())
                .addToBackStack(null)
                .commit()

        }
        uri?.let {
            val bitmapImage = MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver,
                uri
            )
            binding.ivPhoto.setImageBitmap(bitmapImage)
        }

        userViewModel.storage.child("images/${userViewModel.currentUser?.uid}")
            .getBytes(Long.MAX_VALUE).addOnSuccessListener {
                val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
                Log.d("image", bmp.toString())
                binding.ivPhoto.setImageBitmap(bmp)
            }.addOnFailureListener {
                binding.ivPhoto.setImageBitmap(null)
            }
    }

    override fun onResume() {
        super.onResume()
        setTabLayout()
    }

    private fun setTabLayout() {
        MainContainerFragment.getInstance().setVisibility(View.VISIBLE)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val URI_STRING = "uri"
        const val REQUEST_KEY = "111"

        private var instance: EditFragment? = null
        fun getInstance() = instance

    }
}