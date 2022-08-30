package com.android.recipe.presentation.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.android.recipe.R
import com.android.recipe.databinding.FragmentEditBinding
import com.android.recipe.presentation.RecipeApp
import com.android.recipe.presentation.User
import com.android.recipe.presentation.UserViewModel
import com.android.recipe.presentation.ViewModelFactory
import com.android.recipe.presentation.fragments.ProfileFragment.Companion.REQUEST_KEY
import com.android.recipe.presentation.fragments.ProfileFragment.Companion.URI_STRING
import com.android.recipe.presentation.fragments.fragmentContainers.AuthFragmentContainer
import com.android.recipe.presentation.fragments.fragmentContainers.MainContainerFragment
import javax.inject.Inject

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding: FragmentEditBinding
        get() = _binding ?: throw RuntimeException("FragmentEditBinding is null")
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var uri: Uri? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as RecipeApp).component
    }

    private val userViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBackPressed()
        binding.tvDelete.setOnClickListener {
            DeleteAccountDialog().show(parentFragmentManager, "tag")
        }
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 1) {
                    userViewModel.auth.signOut()
                    val fm = requireActivity().supportFragmentManager
                    for (i in 0 until fm.backStackEntryCount) {
                        fm.popBackStack()
                    }
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_main, AuthFragmentContainer())
                        .commit()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        userViewModel.currentUser?.uid?.let {
            userViewModel.reference.child(it).get()
                .addOnSuccessListener { dataSnapshot ->
                    val user = dataSnapshot.getValue(User::class.java)
                    binding.etUsername.setText(user?.username)
                    binding.etEmail.setText(user?.email)
                }
        }
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    uri = data?.data
                    val bitmapImage =
                        MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
                    binding.ivPhoto.setImageBitmap(bitmapImage)
                    setFragmentResult(REQUEST_KEY, bundleOf(URI_STRING to uri.toString()))
                }
            }
        binding.btnUpload.setOnClickListener {
            val intent =
                Intent().apply {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    action = Intent.ACTION_PICK
                    type = "image/*"
                }
            resultLauncher.launch(intent)
        }

        userViewModel.storage.child("images/${userViewModel.currentUser?.uid}")
            .getBytes(Long.MAX_VALUE).addOnSuccessListener {
                val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
                binding.ivPhoto.setImageBitmap(bmp)
            }.addOnFailureListener {
                binding.ivPhoto.setImageBitmap(null)
            }

        binding.btnDelete.setOnClickListener {
            binding.ivPhoto.setImageBitmap(null)
        }

        binding.btnSave.setOnClickListener {
            userViewModel.currentUser?.uid?.let { id ->
                uri?.let { uri ->
                    userViewModel.storage.child("images/${id}").putFile(uri)
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        hideTabLayout()
    }

    private fun setOnBackPressed() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                }
            })
    }

    private fun hideTabLayout() {
        MainContainerFragment.getInstance().setVisibility(View.GONE)
    }
}