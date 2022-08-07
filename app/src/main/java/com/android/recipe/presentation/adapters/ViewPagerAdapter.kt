package com.android.recipe.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.recipe.presentation.BlankFragment
import com.android.recipe.presentation.ContainerFragment
import com.android.recipe.presentation.RecipeDetailFragment
import com.android.recipe.presentation.RecipesListFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContainerFragment()
            1 -> BlankFragment()
            2 -> BlankFragment()
            else -> {
                BlankFragment()
            }
        }
    }
}