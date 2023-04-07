package com.example.robandwiki.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.robandwiki.R
import com.example.robandwiki.databinding.FragmentSignInBinding
import com.example.robandwiki.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val editTextEmail: EditText = view.findViewById(R.id.editTextEmail)
        val editTextPassword: EditText = view.findViewById(R.id.editTextPassword)
        val buttonSignIn: Button = view.findViewById(R.id.buttonSignIn)
        val buttonSwitchToSignUp: Button = view.findViewById(R.id.buttonSwitchToSignUp)
        val buttonGoogleSignIn: Button = view.findViewById(R.id.buttonGoogleSignIn)

        // Set click listener for sign in button
        buttonSignIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            signIn(email, password)
        }

        // Set click listener for switch to sign up button
        buttonSwitchToSignUp.setOnClickListener {
            // Replace the current fragment with the sign up fragment
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        // Set click listener for Google sign in button
        buttonGoogleSignIn.setOnClickListener {
            //TODO: Implement Google sign in with Firebase
        }

        return view
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    Toast.makeText(activity, "Login successful!", Toast.LENGTH_SHORT).show()
                    val navController = Navigation.findNavController(requireView())
                    navController.navigate(R.id.action_signInFragment_to_homeFragment)
                } else {
                    Toast.makeText(activity, "Login failed. Check your credentials again!.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}