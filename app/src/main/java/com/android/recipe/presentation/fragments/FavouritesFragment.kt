package com.android.recipe.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.recipe.R
import com.android.recipe.databinding.FragmentFavouritesBinding
import com.android.recipe.presentation.MainActivity
import com.android.recipe.presentation.RecipeViewModel
import com.android.recipe.presentation.adapters.RecipeAdapter

class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding: FragmentFavouritesBinding
        get() = _binding ?: throw RuntimeException("FavouritesFragmentBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favouritesAdapter = RecipeAdapter()
        favouritesAdapter.onClickListener = {
            val recipeDetailFragment = RecipeDetailFragment()
            val args = Bundle().apply {
                putInt("id", it.id)
                putBoolean("isNavigation", false)
            }
            recipeDetailFragment.arguments = args
            parentFragmentManager.commit {
                replace(R.id.fragment_container, recipeDetailFragment)
                addToBackStack(null)
            }
        }
        viewModel.favouriteRecipesList.observe(viewLifecycleOwner){
            favouritesAdapter.submitList(it)
        }
        with(binding.rvFavourites) {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = favouritesAdapter
        }
        (activity as MainActivity).setVisibility(View.VISIBLE)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}