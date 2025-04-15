package com.example.tamrinkotlin

data class category(val idCategory: String,val strCategory: String,
                    val strCategoryThumb: String,val strCategoryDescription: String)
{

}

data class categoriesresponse(val responce : List<category>)