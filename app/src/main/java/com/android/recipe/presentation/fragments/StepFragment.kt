package com.android.recipe.presentation.fragments

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
import com.android.recipe.databinding.FragmentStepBinding
import com.android.recipe.presentation.RecipeViewModel
import com.android.recipe.presentation.Step
import com.android.recipe.presentation.adapters.StepAdapter
import kotlinx.coroutines.*


class StepFragment : Fragment() {
    private var _binding: FragmentStepBinding? = null
    private val binding: FragmentStepBinding
        get() = _binding ?: throw RuntimeException("FragmentStepBinding is null")

    private val args by navArgs<StepFragmentArgs>()
    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepBinding.inflate(inflater, container, false)
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
        getStepList()
    }

    private fun getStepList() {
        var listOfSteps: List<Deferred<Step>>
        viewModel.getStepsListByRecipeId(args.recipeId).observe(viewLifecycleOwner) { list ->
            listOfSteps = list.map {
                lifecycleScope.async(Dispatchers.IO) {
                    val ingredientsList =
                        viewModel.getStepWithIngredients(it.description).ingredients.joinToString(", ") {
                            it.name
                        }
                    Step(
                        number = it.number,
                        equipments = it.equipments,
                        description = it.description,
                        ingredients = ingredientsList
                    )
                }
            }
            lifecycleScope.launch {
                val stepAdapter = StepAdapter(listOfSteps.awaitAll())
                binding.lvStep.adapter = stepAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}