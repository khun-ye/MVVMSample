package com.ytn.mvvm.data.api

import com.ytn.mvvm.data.api.model.restaurantListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestEndpoint {
    @GET("place/nearbysearch/json")
    fun getRestaurants(
        @Query("location") location: String,
        @Query("radius") radius: Int?,
        @Query("type") type: String,
        @Query("key") key: String
    ): Single<restaurantListResponse>
}
