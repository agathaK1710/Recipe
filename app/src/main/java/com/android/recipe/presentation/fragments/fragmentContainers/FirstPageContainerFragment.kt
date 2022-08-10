package com.android.recipe.presentation.fragments.fragmentContainers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.recipe.R
import com.android.recipe.presentation.RecipeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstPageContainerFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_page_container, container, false)
    }
}