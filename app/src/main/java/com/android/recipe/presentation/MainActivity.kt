package com.android.recipe.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.android.recipe.R
import com.android.recipe.databinding.ActivityMainBinding
import com.android.recipe.presentation.adapters.ViewPagerAdapter
import com.android.recipe.presentation.fragments.RegistrationFragment
import com.android.recipe.presentation.fragments.fragmentContainers.MainContainerFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
        ViewModelProvider(this, viewModelFactory)[RecipeViewModel::class.java]
    }

    private val userViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (userViewModel.currentUser != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_main, MainContainerFragment())
        }

//        lifecycleScope.launch(Dispatchers.IO) {
//            resources.getStringArray(R.array.cuisine_list).forEach {
//                viewModel.loadData(it)
//            }
//        }

    }
}
