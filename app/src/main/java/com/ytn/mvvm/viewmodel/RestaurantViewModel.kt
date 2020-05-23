package com.ytn.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ytn.mvvm.data.database.entity.restaurantData
import com.ytn.mvvm.data.repository.restaurantRepository
import javax.inject.Inject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RestaurantViewModel @Inject constructor(private val repository: restaurantRepository) : ViewModel() {
    private var disposable: CompositeDisposable? = null

    var restaurantData = MutableLiveData<List<restaurantData>>()
    var loading = MutableLiveData<Boolean>()

    init {
        disposable = CompositeDisposable()
    }

    fun fetchData(location: String, radius: Int?, type: String, key: String) {
        loading.postValue(true)
        disposable!!.add(
            repository.getLocalRestaurantData(location, radius, type, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<List<restaurantData>>() {
                    override fun onSuccess(data: List<restaurantData>) {
                        loading.postValue(false)
                        restaurantData.postValue(data)
                    }

                    override fun onError(e: Throwable) {
                        loading.postValue(false)

                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}
