package com.android.recipe.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.recipe.databinding.FragmentRecipesListBinding
import com.android.recipe.presentation.adapters.RecipeAdapter
import kotlinx.coroutines.launch

class RecipesListFragment : Fragment() {
    private var _binding: FragmentRecipesListBinding? = null
    private val binding: FragmentRecipesListBinding
        get() = _binding ?: throw RuntimeException("FragmentRecipesListBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesListBinding.inflate(inflater, container, false)
//        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        viewModel.recipesList.observe(viewLifecycleOwner) {
//            if (cm.activeNetwork != null) {
//                if (it.isNotEmpty()) {
//                    for (recipe in it) {
//                        if (recipe.favouriteRecipe == 0) {
//                            lifecycleScope.launch {
//                                viewModel.removeRecipe(recipe)
//                            }
//                        }
//                    }
//                }
//            }
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvAdapter = RecipeAdapter()
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