package com.android.recipe.presentation.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.recipe.databinding.FragmentProfileBinding
import com.android.recipe.presentation.RecipeApp
import com.android.recipe.presentation.RecipeViewModel
import com.android.recipe.presentation.ViewModelFactory
import com.android.recipe.presentation.adapters.RecipeAdapter
import javax.inject.Inject


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding is null")
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as RecipeApp).component
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RecipeViewModel::class.java]
    }
    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val uri: Uri? = data?.data
                    val bitmapImage = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
                    binding.ivPhoto.setImageBitmap(bitmapImage)
                }
            }
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
        binding.ivPhoto.setOnClickListener {
            val intent =
                Intent().apply {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    action = Intent.ACTION_PICK
                    type = "image/*"
                }
            resultLauncher.launch(intent)
        }
        val myRecipeAdapter = RecipeAdapter()
        binding.rvMyRecipes.adapter = myRecipeAdapter
        viewModel.favouriteRecipesList.observe(viewLifecycleOwner){
            myRecipeAdapter.submitList(it)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}