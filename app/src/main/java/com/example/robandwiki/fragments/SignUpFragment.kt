package com.example.robandwiki.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.robandwiki.R
import com.example.robandwiki.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        auth = FirebaseAuth.getInstance()

        val emailEditText = view.findViewById<EditText>(R.id.email_edit_text)
        val passwordEditText = view.findViewById<EditText>(R.id.password_edit_text)
        val signUpButton = view.findViewById<Button>(R.id.signup_button)
        val switchToLoginTextView = view.findViewById<TextView>(R.id.switch_to_login_text_view)

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(activity, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(activity, "Registration successful!", Toast.LENGTH_SHORT).show()
                            val navController = Navigation.findNavController(requireView())
                            navController.navigate(R.id.action_signUpFragment_to_homeFragment)
                        } else {
                            Toast.makeText(activity, "Registration failed. Please try again later.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        switchToLoginTextView.setOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        return view
    }
}