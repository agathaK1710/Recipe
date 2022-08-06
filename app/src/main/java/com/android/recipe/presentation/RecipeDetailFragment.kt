package com.android.recipe.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.recipe.databinding.FragmentRecipeDetailBinding
import com.android.recipe.domain.entities.RecipeInfo
import com.squareup.picasso.Picasso
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding: FragmentRecipeDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentRecipeDetailBinding is null")

    private val args by navArgs<RecipeDetailFragmentArgs>()


    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).goneTabLayout()
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
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
        val recipe = viewModel.getRecipeInfo(args.id)
        recipe.observe(viewLifecycleOwner){
            setViews(it)
        }
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
            tvCalories.text = recipe.calories.toString()
            Picasso.get().load(recipe.image).into(ivRecipeImage)
            tvServings.text = recipe.servings.toString()
            tvEquipment.text = "spoon"
        }
    }
}