package com.example.shoestore.allShoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailsBinding
import com.example.shoestore.utils.onClick

class ShoeDetailsFragment : Fragment() {
    lateinit var binding:FragmentShoeDetailsBinding
    private val viewModel :AllShoesViewModel by  activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_details,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        onSaveNewShoe()
        binding.btnCancel onClick ::onCancelClicked
        return binding.root
    }

    private fun onSaveNewShoe() {
        viewModel.saveNewShoe.observe(viewLifecycleOwner){valid->
            if(valid){
                navigationToShoeListFragment()
            }

        }



    }

    private fun navigationToShoeListFragment() {
       val action = ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToAllShoesFragment()
        findNavController().navigate(action)
    }

    private fun onCancelClicked() {
        findNavController().popBackStack()
    }

}