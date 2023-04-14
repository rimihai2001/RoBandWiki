package com.example.robandwiki.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.robandwiki.MainActivity
import com.example.robandwiki.R
import com.example.robandwiki.databinding.FragmentSplashBinding



class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        navController = findNavController()

        binding.buttonEnterNow.setOnClickListener {
            navController.navigate(R.id.action_splashFragment_to_signInFragment)
        }

        // Rotate the application name text twice
        val rotateAnimator = ObjectAnimator.ofFloat(binding.textView, View.ROTATION, 0f, 360f)
        rotateAnimator.duration = 2000
        rotateAnimator.repeatCount = 0
        rotateAnimator.start()
    }
}
