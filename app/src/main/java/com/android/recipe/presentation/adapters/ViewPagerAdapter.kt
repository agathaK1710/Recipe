package com.android.recipe.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.recipe.presentation.fragments.ProfileFragment
import com.android.recipe.presentation.fragments.fragmentContainers.FirstPageContainerFragment
import com.android.recipe.presentation.fragments.fragmentContainers.SecondPageContainerFragment
import com.android.recipe.presentation.fragments.fragmentContainers.ThirdPageContainerFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstPageContainerFragment()
            1 -> SecondPageContainerFragment()
            2 -> ThirdPageContainerFragment()
            else -> {
                throw RuntimeException("No fragment")
            }
        }
    }
}