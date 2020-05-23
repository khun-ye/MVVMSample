package com.ytn.mvvm.view.delegate

import com.ytn.mvvm.data.database.entity.restaurantData

object ItemDelegate {
    interface restaurantListener {
        fun selectrestaurnt(data: restaurantData)
    }
}
