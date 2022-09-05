package com.android.recipe.presentation.fragments.fragmentContainers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.recipe.databinding.FragmentAuthContainerBinding

class AuthFragmentContainer : Fragment() {
    private var _binding: FragmentAuthContainerBinding? = null
    private val binding: FragmentAuthContainerBinding
        get() = _binding ?: throw RuntimeException("FragmentAuthContainerBinding is null")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthContainerBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}