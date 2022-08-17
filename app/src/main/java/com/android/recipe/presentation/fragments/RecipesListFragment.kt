package com.android.recipe.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.recipe.R
import com.android.recipe.databinding.FragmentRecipesListBinding
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.presentation.MainActivity
import com.android.recipe.presentation.RecipeApp
import com.android.recipe.presentation.RecipeViewModel
import com.android.recipe.presentation.ViewModelFactory
import com.android.recipe.presentation.adapters.CuisineAdapter
import com.android.recipe.presentation.adapters.CuisineViewHolder
import com.android.recipe.presentation.adapters.RecipeAdapter
import javax.inject.Inject

class RecipesListFragment : Fragment() {
    private var _binding: FragmentRecipesListBinding? = null
    private val binding: FragmentRecipesListBinding
        get() = _binding ?: throw RuntimeException("FragmentRecipesListBinding is null")

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
        (activity as MainActivity).setVisibility(View.VISIBLE)
    }

    override fun onResume() {
        super.onResume()
        setupRV()
    }

    private fun setupRV() {
        val cuisines = resources.getStringArray(R.array.cuisine_list)
        val cuisineList = listOf(
            Cuisine(R.drawable.chinese, cuisines[0]),
            Cuisine(R.drawable.british, cuisines[1]),
            Cuisine(R.drawable.american, cuisines[2]),
            Cuisine(R.drawable.italian, cuisines[3]),
            Cuisine(R.drawable.mexican, cuisines[4]),
            Cuisine(R.drawable.french, cuisines[5]),
            Cuisine(R.drawable.german, cuisines[6]),
            Cuisine(R.drawable.spanish, cuisines[7])
        )
        val cuisineAdapter = CuisineAdapter(cuisineList)
        val rvAdapter = RecipeAdapter()
        binding.recipesRV.adapter = rvAdapter
        viewModel.getRecipesByCuisine(cuisines[0]).observe(viewLifecycleOwner) { list ->
            rvAdapter.submitList(list)

        }
        cuisineAdapter.onClickListener = {
            viewModel.getRecipesByCuisine(it.name).observe(viewLifecycleOwner) { list ->
                rvAdapter.submitList(list)
            }
        }
        binding.cuisineRV.adapter = cuisineAdapter
        val listener = object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> rv.parent
                        .requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        }

        binding.recipesRV.addOnItemTouchListener(listener)
        binding.cuisineRV.addOnItemTouchListener(listener)
        rvAdapter.onClickListener = {
            findNavController().navigate(
                RecipesListFragmentDirections.actionRecipesListFragmentToRecipeDetailFragment(
                    it.id,
                    true
                )
            )
        }
    }

//    private fun searchRecipe(list: LiveData<List<RecipeInfo>>) {
//        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                val filteredList = Transformations.map(list) {
//                    it.filter { recipeInfo ->
//                        recipeInfo.title.lowercase().split(" ").contains(newText?.lowercase())
//                    }
//                }
//                filteredList.observe(viewLifecycleOwner) {
//                    if (it.isNotEmpty()) {
//                        rvAdapter.submitList(it)
//                    } else {
//                        list.observe(viewLifecycleOwner) {
//                            rvAdapter.submitList(it)
//                        }
//                    }
//                }
//                return false
//            }
//        })
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}