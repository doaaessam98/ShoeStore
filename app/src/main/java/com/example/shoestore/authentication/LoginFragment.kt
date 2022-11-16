package com.example.shoestore.authentication

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding
import com.example.shoestore.utils.onClick
import timber.log.Timber

const val PREFERENCE_NAME = "shard_name"
const val LOGIN_STATES = "login_states"

class LoginFragment : Fragment() {

   lateinit var binding: FragmentLoginBinding
   lateinit var   sharedPreference  :SharedPreferences
   lateinit var editor:Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    val viewModel :LoginViewModel by viewModels ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_login,container,false)
        binding.viewModel=viewModel
        binding.lifecycleOwner = this

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         initShard()
        loginState()
        observeLoginResult()
        binding.btnSignup.onClick {
            Timber.e( " signup clicked")
        }

    }

    private fun loginState() {
        if(this::sharedPreference.isInitialized) {
            if(sharedPreference.getBoolean(LOGIN_STATES, false)) {
                navigateToShoesListFragment()
            }
        }
    }

    private fun observeLoginResult() {
        viewModel.loginResult.observe(viewLifecycleOwner){ valid->
            if(valid){
                editor.putBoolean(LOGIN_STATES,true)
                editor.commit()
                navigateToWelcomeFragment()

            }

        }
    }

    private fun initShard() {
     sharedPreference   = requireActivity().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
       editor = sharedPreference.edit()
    }

    private fun navigateToWelcomeFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController().navigate(action)
    }


    private fun navigateToShoesListFragment() {
        val action =LoginFragmentDirections.actionLoginFragmentToAllShoesFragment()
        findNavController().navigate(action)
    }


}