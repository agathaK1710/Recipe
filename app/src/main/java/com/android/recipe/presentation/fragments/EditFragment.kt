package com.android.recipe.presentation.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.android.recipe.R
import com.android.recipe.databinding.FragmentEditBinding
import com.android.recipe.presentation.RecipeApp
import com.android.recipe.presentation.ViewModelFactory
import com.android.recipe.presentation.fragments.ProfileFragment.Companion.REQUEST_KEY
import com.android.recipe.presentation.fragments.ProfileFragment.Companion.URI_STRING
import com.android.recipe.presentation.fragments.fragmentContainers.MainContainerFragment
import javax.inject.Inject

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding: FragmentEditBinding
        get() = _binding ?: throw RuntimeException("FragmentEditBinding is null")
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as RecipeApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val uri = data?.data
                    val bitmapImage =
                        MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
                    binding.ivPhoto.setImageBitmap(bitmapImage)
                    setFragmentResult(REQUEST_KEY, bundleOf(URI_STRING to uri.toString()))
                }
            }
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
        binding.ivPhoto.setOnClickListener {
            val intent =
                Intent().apply {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    action = Intent.ACTION_PICK
                    type = "image/*"
                }
            resultLauncher.launch(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        hideTabLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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