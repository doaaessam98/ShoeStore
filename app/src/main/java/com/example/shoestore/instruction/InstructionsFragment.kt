package com.example.shoestore.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentInstructionBinding
import com.example.shoestore.utils.onClick


class InstructionsFragment : Fragment() {

lateinit var binding :FragmentInstructionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_instruction,container,false)
        binding.btnStart onClick :: navigateToAllShoesFragment
        return binding.root
    }

    private fun navigateToAllShoesFragment() {
       val action = InstructionsFragmentDirections.actionInstructionsFragmentToAllShoesFragment()
        findNavController().navigate(action)
    }

}