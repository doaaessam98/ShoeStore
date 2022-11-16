package com.example.shoestore.authentication

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.R

class LoginViewModel (application: Application):AndroidViewModel(application){


    val userEmail = ObservableField("")
    val userPassword = MutableLiveData("")
    val userEmailError=ObservableField <String>()
    val  userPasswordError=MutableLiveData<String>()
    val setLoading=ObservableField(false)
     private val _loginResult = MutableLiveData<Boolean>()
     val loginResult :LiveData<Boolean>
       get() = _loginResult

     val error = getApplication<Application>().getString(R.string.required)
   fun login(){
       setLoading.set(true)
       val email = userEmail.get()?.trim()?:""
       val password = userPassword.value?.trim()?:""
        when{
            email.isEmpty()->{
                  userEmailError.set(error)
            }
            password.isEmpty()->{
                userPasswordError.value =error
            }
            else->{
                Log.e(TAG, "login: ", )
                setLoading.set(false)
                _loginResult.value = true
            }
        }
   }
}