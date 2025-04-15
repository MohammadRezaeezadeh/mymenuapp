package com.example.tamrinkotlin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch



data class Recipesate(val Loading: Boolean = true,
                      val List : List<category> = emptyList(),val error: String?=null)
{

}

class MainViewModel: ViewModel() {


    init {
        fetchCategories()
    }

    private val _Categoriesstate = mutableStateOf(Recipesate())
    val Categorieissstate: State<Recipesate> = _Categoriesstate
    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val responce= responcService.getcategries()
                _Categoriesstate.value = _Categoriesstate.value.copy(Loading = false,List = responce.responce,error = null)

            }catch (e: Exception){
                _Categoriesstate.value = _Categoriesstate.value.copy(Loading = false,error = "error:${e.message}")

            }
        }
    }
}
