package com.android.recipe.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.android.recipe.R
import com.android.recipe.databinding.ActivityMainBinding
import com.android.recipe.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        val icons =
            arrayListOf(R.drawable.home, R.drawable.search, R.drawable.heart, R.drawable.person)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.setIcon(icons[position])
            }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.white)
                tab?.icon?.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        tabIconColor,
                        BlendModeCompat.SRC_ATOP
                    )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.dark_grey)
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
}
