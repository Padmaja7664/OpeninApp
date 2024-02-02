package com.example.openinapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("dashboardNew")
    fun getProductData(): Call<RecentLink>
}