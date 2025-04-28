package com.example.tamrinkotlin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // وضعیت UI به صورت State
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    init {
        // هنگام ساخت ViewModel، دسته‌بندی‌ها رو بگیر
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                // درخواست به API
                val response = responseService.getCategories()
                // آپدیت وضعیت با داده‌های دریافت‌شده
                _categoriesState.value = _categoriesState.value.copy(
                    isLoading = false,
                    list = response.categories, // استفاده از کلید categories
                    error = null
                )
            } catch (e: Exception) {
                // در صورت خطا، پیام خطا رو ذخیره کن
                _categoriesState.value = _categoriesState.value.copy(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }
}