package com.example.shoestore.allShoes

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.R
import com.example.shoestore.authentication.LOGIN_STATES
import com.example.shoestore.authentication.PREFERENCE_NAME
import com.example.shoestore.databinding.FragmentAllShoesBinding
import com.example.shoestore.databinding.ItemShoeListBinding
import com.example.shoestore.model.Shoe
import com.example.shoestore.utils.onClick


class AllShoesFragment : Fragment() {
 lateinit var binding: FragmentAllShoesBinding

    private val viewModel :AllShoesViewModel by  activityViewModels()
    lateinit var   sharedPreference  : SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_all_shoes,container,false)
        setHasOptionsMenu(true)
        binding.addShoes onClick :: navigateToDetailsFragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setUpShoeList()

    }
    private fun setUpShoeList() {
        viewModel.shoeList.observe(viewLifecycleOwner){shoesList->
            shoesList.let { shoes->
                shoes?.forEach{ shoe->
                    addShoe(shoe)
                  } }

        }
    }

    private fun addShoe(shoe: Shoe) {
        val cardItemView=ItemShoeListBinding.inflate(layoutInflater)
         cardItemView.shoe = shoe
        binding.shoesList.addView(cardItemView.root)
    }

    private fun navigateToDetailsFragment() {
        val action =AllShoesFragmentDirections.actionAllShoesFragmentToShoeDetailsFragment()
         findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoes_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navigateToLoginFragment()
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }



    private fun navigateToLoginFragment() {
        logout()
        val action = AllShoesFragmentDirections.actionAllShoesFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun logout() {
        sharedPreference   = requireActivity().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreference.edit()
        editor.putBoolean(LOGIN_STATES,false)
        editor.commit()

    }

}