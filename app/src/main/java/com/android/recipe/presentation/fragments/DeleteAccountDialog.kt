package com.android.recipe.presentation.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.recipe.R
import com.android.recipe.presentation.*
import com.android.recipe.presentation.fragments.fragmentContainers.MainContainerFragment
import com.android.recipe.presentation.fragments.fragmentContainers.ThirdPageContainerFragment
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class DeleteAccountDialog : DialogFragment() {
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.delete_account_dialog)
                .setPositiveButton(
                    R.string.yes
                ) { _, _ ->
//                    MainContainerFragment.fragmentManager()
//                        .beginTransaction()
//                        .replace(
//                            R.id.fragment_container_main,
//                            LoginFragment()
//                        )
//                        .commit()
                    //FirebaseAuth.getInstance().currentUser?.delete()

//                    findNavController().popBackStack()
//                    findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)


//                    userViewModel.currentUser?.delete()
//                    if (userId != null) {
//                        userViewModel.reference.child("users")
//                            .child(userId)
//                            .removeValue()
//                    }
                }
                .setNegativeButton(R.string.no,
                    DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}