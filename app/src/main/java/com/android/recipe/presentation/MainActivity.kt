package com.android.recipe.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.recipe.R
import com.android.recipe.databinding.ActivityMainBinding
import com.android.recipe.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as RecipeApp).component
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpTabLayout()

//        lifecycleScope.launch(Dispatchers.IO) {
//            resources.getStringArray(R.array.cuisine_list).forEach {
//                viewModel.loadData(it)
//            }
//        }
    }

    private fun setUpTabLayout() {
        val icons =
            arrayListOf(R.drawable.home, R.drawable.heart, R.drawable.person)
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

    fun setVisibility(visible: Int) {
        binding.tabLayout.visibility = visible
    }
}
