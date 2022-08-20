package com.android.recipe.presentation.fragments.fragmentContainers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.recipe.R
import com.android.recipe.presentation.RecipeViewModel

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