package com.ytn.mvvm.data.api.model

import com.google.gson.annotations.SerializedName

class restaurantListResponse {

    @SerializedName("results")
    var results: List<restaurantItem>? = null

    inner class restaurantItem {
        @SerializedName("geometry")
        var geometry: geometryItem? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("vicinity")
        var vicinity: String? = null
    }

    inner class geometryItem {
        @SerializedName("location")
        var locationItem: locationItem? = null
    }

    inner class locationItem {
        @SerializedName("lat")
        var lat: Double? = null
        @SerializedName("lng")
        var lng: Double? = null
    }
}
