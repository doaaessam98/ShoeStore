package com.example.shoestore.allShoes

import android.app.Application
import android.content.ContentValues.TAG
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.shoestore.R
import com.example.shoestore.model.Shoe
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class AllShoesViewModel(application: Application) :AndroidViewModel(application) {

    val shoeName = ObservableField("")
    val shoeSize = MutableLiveData<Int>()
    val companyName = MutableLiveData("")
    val shoeDescription = ObservableField("")
    val shoeNameError= ObservableField <String>()
    val shoeSizeError =MutableLiveData<String>()
    val companyNameError= ObservableField <String>()
    val shoeDescriptionError =MutableLiveData<String>()



    private val _saveNewShoe = MutableLiveData<Boolean>()
     val  saveNewShoe:LiveData<Boolean> = _saveNewShoe

    private val _shoeList = MutableLiveData<List<Shoe>>(listOf())
    val  shoeList:LiveData<List<Shoe>>
        get()= _shoeList

    val error = getApplication<Application>().getString(R.string.required)

    fun onSaveClicked(){
        val name = shoeName.get()?:""
        val size = shoeSize.value.toString()?:""
        val description = shoeDescription.get()?:""
        val company = companyName.value?:""

     when{
          name.isEmpty()->{

          }
          company.isEmpty()->{

          }
          size.isEmpty()->{

          }
         description.isEmpty()->{

         }
         else -> {
             viewModelScope.launch {
                 addNewShoe(name,company,size,description)
             }



         }



     }


    }

    private suspend  fun addNewShoe(shoeName:String,companyName:String,shoeSize:String,shoeDescription:String){
        val image  =  getImage()
        val newShoe = Shoe(shoeName,shoeSize,shoeDescription,companyName,image)
         _shoeList.value = _shoeList.value ?.plus(newShoe)
         _saveNewShoe.value=true
          delay(300)

          clearDate()
        _saveNewShoe.value =false

    }
//  i know that bad work but using her only to can set image
    private fun getImage(): Drawable {
         val context = getApplication<Application>()
        val images : List<Drawable> = listOf(context.getDrawable(R.drawable.shoe1)!!,context.getDrawable(R.drawable.shoe2)!!,context.getDrawable(R.drawable.shoe3)!!,context.getDrawable(R.drawable.shoe4)!!)
        val random :Int =(0..3).random()
         return images[random]

    }

    private fun clearDate() {
        companyName.value = ""
        shoeName.set("")
        shoeDescription.set("")
        shoeSize.value= 0
    }


}