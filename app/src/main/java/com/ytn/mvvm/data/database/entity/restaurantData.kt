package com.ytn.mvvm.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_restaurant")
class restaurantData(
    @field:PrimaryKey(autoGenerate = true)
    var resid: Int?, var resname: String?,
    var resaddress: String?,
    var reslat: Double?,
    var reslang: Double?
)
