package com.vivekupasani.todolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.vivekupasani.todolist.R
import com.vivekupasani.todolist.databinding.FragmentSigninBinding
import com.vivekupasani.todolist.databinding.FragmentSignupBinding


class SignIn : Fragment() {

    lateinit var binding: FragmentSigninBinding
    lateinit var navController: NavController
    lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        binding.btngoSignUp.setOnClickListener {
            navController.navigate(R.id.action_signIn_to_signUp)
        }

        binding.btnSignIn.setOnClickListener {
            val logmail = binding.etloginEmail.text.toString()
            val logPass = binding.etloginPassword.text.toString()
            if (binding.etloginEmail.text.toString()
                    .isNotEmpty() && binding.etloginPassword.text.toString().isNotEmpty()
            ) {
                signIn(logmail, logPass)
            }
        }


        return binding.root
    }

    private fun signIn(logmail: String, logPass: String) {
        setProgress(true)
        auth.signInWithEmailAndPassword(logmail, logPass)
            .addOnSuccessListener {
                setProgress(false)
                navController.navigate(R.id.action_signIn_to_profile)
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                setProgress(false)
                Toast.makeText(requireContext(), "Failed to add user: ${it.message}", Toast.LENGTH_SHORT)
                    .show()
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