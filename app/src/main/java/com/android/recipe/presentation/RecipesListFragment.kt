package com.android.recipe.presentation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.recipe.R
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

    private var rvAdapter = RecipeAdapter()

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
        viewModel.recipesList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
        rvAdapter.onClickListener = {
            findNavController().navigate(R.id.action_recipesListFragment_to_recipeDetailFragment)
        }

        binding.recipesRV.adapter = rvAdapter
        searchRecipe()
    }

    private fun searchRecipe() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.actionSearch -> {
                        val searchView = menuItem.actionView as SearchView
                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                val filteredList = Transformations.map(viewModel.recipesList) {
                                    it.filter { recipeInfo ->
                                        recipeInfo.title.lowercase().split(" ")
                                            .contains(newText?.lowercase())
                                    }
                                }
                                filteredList.observe(viewLifecycleOwner) {
                                    if (it.isNotEmpty()) {
                                        rvAdapter.submitList(it)
                                    } else {
                                        viewModel.recipesList.observe(viewLifecycleOwner) {
                                            rvAdapter.submitList(it)
                                        }
                                    }
                                }
                                return false
                            }
                        })
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun addToFavourites(position: Int){
        viewModel.recipesList.observe(viewLifecycleOwner){
            val recipe = it[position]
            lifecycleScope.launch{
                viewModel.editRecipe(recipe)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}