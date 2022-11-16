package com.example.shoestore.welcomeBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentWelcomeBinding
import com.example.shoestore.utils.onClick

class WelcomeFragment : Fragment() {

lateinit var binding:FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding=  DataBindingUtil.inflate(layoutInflater, R.layout.fragment_welcome, container, false)

        binding.btnNext onClick::navigateToInstructionFragment
        return binding.root
    }


    private fun navigateToInstructionFragment(){
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
        findNavController().navigate(action)
    }

}