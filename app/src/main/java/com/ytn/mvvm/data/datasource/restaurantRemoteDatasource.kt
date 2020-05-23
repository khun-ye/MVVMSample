package com.ytn.mvvm.data.datasource

import com.ytn.mvvm.data.api.RestEndpoint
import com.ytn.mvvm.data.database.entity.restaurantData
import javax.inject.Inject
import io.reactivex.Single

class restaurantRemoteDatasource @Inject constructor(private val restEndpoint: RestEndpoint) {

    fun getRemoteRestaurantData(location: String, radius: Int?, type: String, key: String): Single<List<restaurantData>> {
        return restEndpoint.getRestaurants(location, radius, type, key)
            .map {
                it.results?.map {
                    item->restaurantData(
                        null, item.name, item.vicinity, item.geometry?.locationItem?.lat,
                        item.geometry?.locationItem?.lng
                    )
                }
            }
    }
}
