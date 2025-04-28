package com.example.tamrinkotlin

// مدل داده برای هر دسته‌بندی
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)


// مدل داده برای پاسخ API
data class CategoriesResponse(val categories: List<Category>)