package com.vivekupasani.todolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.vivekupasani.todolist.R
import com.vivekupasani.todolist.databinding.FragmentProfileBinding
import com.vivekupasani.todolist.models.User


class Profile : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var navController: NavController
    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        setProgress(false)

        binding.btnAuth.setOnClickListener {
            navController.navigate(R.id.action_profile_to_signIn)
        }

        val currentUser = auth?.currentUser

        if (currentUser != null) {
            binding.afterSignUp.isVisible = true
            binding.beforeSignUp.isVisible = false
            setValues()
        } else {

            binding.afterSignUp.isVisible = false
            binding.beforeSignUp.isVisible = true
        }

        binding.btnLog0ut.setOnClickListener {
            auth.signOut()
            navController.navigate(R.id.action_profile_to_home2)
        }


        return binding.root
    }

    private fun setValues() {
        setProgress(true)
        val currenUser = auth.currentUser?.uid
        if (currenUser != null) {
            firestore.collection("Users")
                .document(currenUser)
                .get()
                .addOnSuccessListener {
                    val user = it.toObject(User::class.java)

                    binding.fullName.text = user?.name
                    binding.email.text = user?.email
                    binding.phone.text = "+91 " + user?.phoneNumber

                    setProgress(false)
                }
        }
    }

    fun setProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.isVisible = true
        } else {
            binding.progressBar.isVisible = false
        }
    }


}