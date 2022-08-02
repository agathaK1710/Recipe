package com.android.recipe.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.android.recipe.R
import com.android.recipe.databinding.FragmentRecipesListBinding
import com.android.recipe.presentation.adapters.RecipeAdapter
import kotlinx.coroutines.launch

class RecipesListFragment : Fragment() {
    private var isConnected = false
    private var _binding: FragmentRecipesListBinding? = null
    private val binding: FragmentRecipesListBinding
        get() = _binding ?: throw RuntimeException("FragmentRecipesListBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm.activeNetwork != null) {
           isConnected = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvAdapter = RecipeAdapter()
        viewModel.recipesList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && isConnected) {
                for (recipe in it) {
                    if (recipe.favouriteRecipe == 0) {
                        lifecycleScope.launch {
                            viewModel.removeRecipe(recipe)
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.loadData()
        }
        viewModel.recipesList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
        binding.recipesRV.adapter = rvAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}