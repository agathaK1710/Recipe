package com.android.recipe.presentation.fragments.fragmentContainers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.recipe.R
import com.android.recipe.databinding.FragmentMainContainerBinding
import com.android.recipe.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainContainerFragment : Fragment() {
    private var _binding: FragmentMainContainerBinding? = null
    private val binding: FragmentMainContainerBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        fragManager = parentFragmentManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout(view.context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpTabLayout(context: Context) {
        val icons =
            arrayListOf(R.drawable.home, R.drawable.heart, R.drawable.chef)
        binding.viewPager.adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.setIcon(icons[position])
            }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabIconColor = ContextCompat.getColor(context, R.color.white)
                tab?.icon?.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        tabIconColor,
                        BlendModeCompat.SRC_ATOP
                    )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabIconColor = ContextCompat.getColor(context, R.color.dark_grey)
                tab?.icon?.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        tabIconColor,
                        BlendModeCompat.SRC_ATOP
                    )
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        tabLayoutMediator.attach()
    }

    fun setVisibility(visible: Int) {
        binding.tabLayout.visibility = visible
    }

    companion object {
        private lateinit var instance: MainContainerFragment
        private lateinit var fragManager: FragmentManager
        fun getInstance() = instance
        fun fragmentManager() = fragManager
    }
}