package com.ytn.mvvm.data.repository

import com.ytn.mvvm.data.database.entity.restaurantData
import com.ytn.mvvm.data.datasource.restaurantLocalDatasource
import com.ytn.mvvm.data.datasource.restaurantRemoteDatasource
import javax.inject.Inject
import javax.inject.Singleton
import io.reactivex.Completable
import io.reactivex.Single

@Singleton
class restaurantRepository @Inject constructor(private val remote: restaurantRemoteDatasource,
                                               private val local: restaurantLocalDatasource) {

    fun getLocalRestaurantData(location: String, radius: Int?, type: String, key: String): Single<List<restaurantData>> {
        //will return the cached data if it's available. Otherwise, it will perform the network request,save data and then return the data
        return local.restaurantData.flatMap{
            if (it.isEmpty()) {
                 local.restaurantData.flatMap {
                    remote.getRemoteRestaurantData(location, radius, type, key).flatMap {
                            dataList-> saveLocalRestaurantData(dataList)
                        Single.just(dataList)
                    }
                }
            } else {
                return@flatMap local.restaurantData
            }
        }
    }

    private fun saveLocalRestaurantData(data: List<restaurantData>): Completable {
        return local.saveRestaurantData(data)
    }
}
