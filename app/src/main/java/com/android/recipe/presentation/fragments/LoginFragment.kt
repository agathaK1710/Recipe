package com.android.recipe.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.recipe.R
import com.android.recipe.databinding.FragmentLoginBinding
import com.android.recipe.presentation.RecipeApp
import com.android.recipe.presentation.UserViewModel
import com.android.recipe.presentation.ViewModelFactory
import com.android.recipe.presentation.fragments.fragmentContainers.MainContainerFragment
import javax.inject.Inject

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val userViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as RecipeApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogIn.setOnClickListener {
            val email = binding.tvEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            signIn(view.context, email, password)
        }
        binding.tvNotAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun signIn(context: Context, email: String, password: String) {
        binding.progressBar.visibility = View.VISIBLE
        userViewModel.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                binding.progressBar.visibility = View.GONE
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container_main,
                        MainContainerFragment()
                    )
                    .commit()
            }.addOnFailureListener {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}