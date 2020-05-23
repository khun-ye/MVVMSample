package com.ytn.mvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ytn.mvvm.R
import com.ytn.mvvm.data.database.entity.restaurantData
import com.ytn.mvvm.util.GlideApp
import com.ytn.mvvm.view.delegate.ItemDelegate
import kotlinx.android.synthetic.main.restaurant_layoutitem.view.*

class restaurantAdapter(private val context: Context, private val data: List<restaurantData>,
                        private val listener: ItemDelegate.restaurantListener) :
    RecyclerView.Adapter<restaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_layoutitem, parent, false)
        return RestaurantViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) = holder.bindData(data[position], context)


    override fun getItemCount() = data.size

    inner class RestaurantViewHolder(itemView: View, listener: ItemDelegate.restaurantListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(data: restaurantData, context: Context) {
            with(itemView){
                GlideApp(context, restaurant_icon).loadDrawable(R.drawable.ic_restaurant_black_24dp)
                restaurant_name.text = data.resname
                restaurant_address.setText(data.resaddress)
            }

        }
    }
}
