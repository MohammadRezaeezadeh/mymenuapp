package com.example.tamrinkotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// تنظیم Retrofit با URL پایه
private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// رابط API برای دریافت دسته‌بندی‌ها
interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}

// ایجاد نمونه سرویس
val responseService = retrofit.create(ApiService::class.java)



// مدل داده برای وضعیت UI
data class RecipeState(
    val isLoading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
)