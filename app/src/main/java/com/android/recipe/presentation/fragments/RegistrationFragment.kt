package com.android.recipe.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.recipe.R
import com.android.recipe.databinding.FragmentRegistrationBinding
import com.android.recipe.presentation.RecipeApp
import com.android.recipe.presentation.User
import com.android.recipe.presentation.UserViewModel
import com.android.recipe.presentation.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern
import javax.inject.Inject

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw RuntimeException("FragmentRegistrationBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var email: String = ""
    private var userName: String = ""
    private var password: String = ""
    private var repeatPassword: String = ""

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
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
        setValues()
        binding.btnRegistration.setOnClickListener {
            signUp(view.context, email, password, userName)
        }
    }

    private fun setValues() {
        binding.etEmail.doAfterTextChanged {
            email = it.toString().trim()
            if (email.isEmpty()) {
                binding.tfEmail.error = "Email is empty"
            } else if (!isEmailValid()) {
                binding.tfEmail.error = "Email doesn't correspond requirements"
            } else {
                binding.tfEmail.error = null
            }
        }
        binding.etUsername.doAfterTextChanged {
            userName = it.toString().trim()
            if (userName.isEmpty()) {
                binding.tfUsername.error = "Username is empty"
            } else if (!isUsernameValid()) {
                Log.d("userName", userName)
                binding.tfUsername.error = "Username must match the pattern: agatha, Agatha_k14"
            } else {
                binding.tfUsername.error = null
            }
        }

        binding.etPassword.doAfterTextChanged {
            password = it.toString().trim()
            if (password.isEmpty()) {
                binding.tfPassword.error = "Password is empty"
            } else if (!isPasswordValid()) {
                binding.tfPassword.error = "password must be 6-12 characters long"
            } else {
                binding.tfPassword.error = null
            }
        }
        binding.etRPassword.doAfterTextChanged {
            repeatPassword = it.toString().trim()
            if (repeatPassword.isEmpty()) {
                binding.tfRPassword.error = "Please repeat password"
            } else if (!isRepeatPasswordCorrect()) {
                binding.tfRPassword.error = "Passwords must match"
            } else {
                binding.tfRPassword.error = null
            }
        }
    }

    private fun signUp(context: Context, email: String, password: String, userName: String) {
        binding.progressBar.visibility = View.VISIBLE
        userViewModel.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                binding.progressBar.visibility = View.GONE
                val firebaseUser = authResult.user
                firebaseUser?.let {
                    val user = User(
                        userName,
                        email,
                        password
                    )
                    userViewModel.reference.child(it.uid).setValue(user)
                }
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }.addOnFailureListener {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun isRepeatPasswordCorrect() =
        password == repeatPassword

    private fun isEmailValid(): Boolean {
        val regex =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
        val pattern = Pattern.compile(regex)
        return pattern.matcher(email).matches()
    }


    private fun isUsernameValid(): Boolean {
        val regex = "^[A-Za-z][A-Za-z0-9_]{1,29}$"
        val pattern = Pattern.compile(regex)
        return pattern.matcher(userName).matches()
    }

    private fun isPasswordValid() = password.length in 6..12
}