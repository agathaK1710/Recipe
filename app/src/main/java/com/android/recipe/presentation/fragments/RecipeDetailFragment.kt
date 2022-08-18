package com.android.recipe.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.recipe.R
import com.android.recipe.databinding.FragmentRecipeDetailBinding
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.presentation.*
import com.android.recipe.presentation.adapters.RecipeDetailAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import javax.inject.Inject

class RecipeDetailFragment : Fragment() {
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding: FragmentRecipeDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentRecipeDetailBinding is null")

    private val args by navArgs<RecipeDetailFragmentArgs>()
    private lateinit var ingredients: List<Deferred<Ingredient>>

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
        (activity as MainActivity).setVisibility(View.GONE)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRV()
        lifecycleScope.launch(Dispatchers.Main) {
            setViews(viewModel.getRecipeInfo(args.id))
        }
        setStepBtnClickListener()
        setOnBackPressed()
    }

    private fun setStepBtnClickListener() {
        binding.button.setOnClickListener {
            if (arguments?.getBoolean("isNavigation") == false) {
                val stepFragment = StepFragment()
                val args = Bundle().apply {
                    putInt("recipeId", it.id)
                    putBoolean("isNavigation", false)
                }
                stepFragment.arguments = args
                parentFragmentManager.commit {
                    replace(R.id.fragment_container2, stepFragment)
                    addToBackStack(null)
                }
            } else {
                findNavController().navigate(
                    RecipeDetailFragmentDirections.actionRecipeDetailFragmentToStepFragment(
                        args.id,
                        true
                    )
                )
            }
        }
    }

    private fun setUpRV() {
        viewModel.getIngredientWithAmountList(args.id).observe(viewLifecycleOwner) { list ->
            ingredients = list.map { ingredientWithAmount ->
                lifecycleScope.async(Dispatchers.IO) {
                    val ingredientInfo =
                        viewModel.getIngredientInfo(ingredientWithAmount.ingredientId)
                    Ingredient(
                        name = ingredientInfo.name,
                        image = "https://spoonacular.com/cdn/ingredients_250x250/${ingredientInfo.image}",
                        amount = ingredientWithAmount.amount,
                        unit = ingredientWithAmount.unit
                    )
                }
            }
            lifecycleScope.launch {
                val detailAdapter = RecipeDetailAdapter(ingredients.awaitAll())
                binding.rvIngredients.adapter = detailAdapter
            }

        }
    }

    private fun setOnBackPressed() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (arguments?.getBoolean("isNavigation") == true) {
                        findNavController().popBackStack()
                    } else {
                        parentFragmentManager.popBackStack()
                    }
                }
            })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setViews(recipe: RecipeInfo) {
        with(binding) {
            tvTitle.text = recipe.title
            tvReadyInMinutes.text = recipe.readyInMinutes.toString()
            tvHealthScore.text = recipe.healthScore.toString()
            pbHeathScore.progress = recipe.healthScore
            tvCalories.text = recipe.calories.toInt().toString()
            Picasso.get().load(recipe.image).into(ivRecipeImage)
            tvServings.text = recipe.servings.toString()
            tvDishTypes.text = recipe.dishTypes
            if (recipe.favouriteRecipe == 1) {
                ivHeart.setImageResource(R.drawable.read_heart)
            } else {
                ivHeart.setImageResource(R.drawable.heart)
            }
            ivHeart.setOnClickListener {
                if (recipe.favouriteRecipe == 0) {
                    (it as ImageView).setImageResource(R.drawable.read_heart)
                    addToFavourites(recipe)
                } else {
                    (it as ImageView).setImageResource(R.drawable.heart)
                    removeAtFavourites(recipe)
                }
            }
        }
    }

    private fun removeAtFavourites(recipe: RecipeInfo) {
        lifecycleScope.launch(Dispatchers.IO) {
            recipe.favouriteRecipe = 0
            viewModel.editRecipe(recipe)
        }
    }

    private fun addToFavourites(recipe: RecipeInfo) {
        lifecycleScope.launch(Dispatchers.IO) {
            recipe.favouriteRecipe = 1
            viewModel.editRecipe(recipe)
        }
    }
}