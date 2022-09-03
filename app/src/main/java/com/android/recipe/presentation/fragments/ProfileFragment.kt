package com.android.recipe.presentation.fragments

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.recipe.R
import com.android.recipe.databinding.FragmentProfileBinding
import com.android.recipe.presentation.*
import com.android.recipe.presentation.adapters.RecipeAdapter
import com.android.recipe.presentation.fragments.fragmentContainers.MainContainerFragment
import com.android.recipe.presentation.fragments.fragmentContainers.ThirdPageContainerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding is null")

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
        userViewModel.addUser()
        super.onAttach(context)
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
                .replace(R.id.fragment_container3, EditFragment())
                .addToBackStack(null)
                .commit()

        }
        setViews()
    }

    private fun setViews() {
        userViewModel.currentUser?.uid?.let { userViewModel.getUser(it) }
            ?.observe(viewLifecycleOwner) { user ->
                val bmp = user.imageByteArray?.let {
                    BitmapFactory.decodeByteArray(
                        user.imageByteArray, 0,
                        it.size
                    )
                }
                binding.ivPhoto.setImageBitmap(bmp)
                binding.tvUserName.text = user?.userName
            }
    }

    override fun onResume() {
        super.onResume()
        setTabLayout()
    }

    private fun setTabLayout() {
        MainContainerFragment.getInstance().setVisibility(View.VISIBLE)
    }
}