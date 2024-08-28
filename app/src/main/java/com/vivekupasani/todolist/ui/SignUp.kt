package com.vivekupasani.todolist.ui

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.vivekupasani.todolist.R
import com.vivekupasani.todolist.databinding.FragmentSignupBinding
import com.vivekupasani.todolist.models.User
import java.util.regex.Pattern


class SignUp : Fragment() {

    lateinit var binding: FragmentSignupBinding
    lateinit var navController: NavController
    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore

    //edit txt
    lateinit var name: String
    lateinit var email: String
    lateinit var password: String
    lateinit var phoneNo: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()


        binding.btngoLogin.setOnClickListener {
            navController.navigate(R.id.action_signUp_to_signIn)
        }
        binding.btnSignUp.setOnClickListener {

            email = binding.etEmail.text.toString()
            name = binding.etName.text.toString()
            password = binding.etPassword.text.toString()
            phoneNo = binding.etPhone.text.toString()


            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phoneNo.isNotEmpty()) {
                if (checkDetails()) {
                    signUp(name, email, password, phoneNo)
                }
            } else {
                Toast.makeText(requireContext(), "Fill the information", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    private fun signUp(name: String, email: String, password: String, phoneNo: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser!!.uid
                    val user = User(currentUser, email, name, password, phoneNo)
                    setProgress(true)

                    firestore.collection("Users")
                        .document(currentUser)
                        .set(user)
                        .addOnSuccessListener {
                            setProgress(false)
                            navController.navigate(R.id.action_signUp_to_profile)
                            Toast.makeText(requireContext(), "Signed up successful", Toast.LENGTH_SHORT)
                                .show()
                        }
                        .addOnFailureListener { e ->
                            setProgress(false)
                            Toast.makeText(requireContext(), "Failed to add user: ${e.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                }
            }
    }



    private fun checkDetails(): Boolean {
        var isValid = true

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError("Invalid Email")
            isValid = false
        }

        if (password.length < 7) {
            binding.etPassword.setError("Password must be 7 characters")
            isValid = false
        }

        if (phoneNo.length < 10) {
            binding.etPhone.setError("Invalid phone number")
            isValid = false
        }

        return isValid
    }


    fun setProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.isVisible = true
        } else {
            binding.progressBar.isVisible = false
        }
    }


}