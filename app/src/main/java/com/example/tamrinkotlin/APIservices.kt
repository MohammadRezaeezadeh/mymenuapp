package com.example.tamrinkotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()).build()

interface apiservices {
    @GET("categories.php")
    suspend fun getcategries():categoriesresponse

}
val  responcService = retrofit.create(apiservices::class.java)