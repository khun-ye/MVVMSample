package com.ytn.mvvm.view.fragment

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ytn.mvvm.R
import com.ytn.mvvm.base.BaseFragment
import com.ytn.mvvm.data.database.entity.restaurantData
import com.ytn.mvvm.util.DefaultItemDecorator
import com.ytn.mvvm.util.factory.ViewModelFactory
import com.ytn.mvvm.view.adapter.restaurantAdapter
import com.ytn.mvvm.view.delegate.ItemDelegate
import com.ytn.mvvm.viewmodel.RestaurantViewModel
import kotlinx.android.synthetic.main.fragment_map.*

import javax.inject.Inject

class ShowMapFragment : BaseFragment(), OnMapReadyCallback, ItemDelegate.restaurantListener {
    private var mMap: GoogleMap? = null
    private var client: FusedLocationProviderClient? = null
    private var currentLocation: Location? = null

    //declaring viewmodel and injecting viewmodel factory
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: RestaurantViewModel

    //information for calling google map nearby api
    private val apiKey = "AIzaSyBiO5KPIFr5LB9AjL1UH9CTCwhg5jiGEWs"
    private val type = "restaurant"
    private val radius = 3220//about 2 miles
    private var adapter: restaurantAdapter? = null


    override fun layoutRes(): Int {
        return R.layout.fragment_map
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        client = LocationServices.getFusedLocationProviderClient(baseActivity!!)
        viewModel = ViewModelProviders.of(baseActivity!!, viewModelFactory).get(RestaurantViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoolbar.title = "Nearby Restaurant"
        restoolbar.setTitleTextColor(Color.WHITE)
        baseActivity!!.setSupportActionBar(restoolbar)
        fetchLocation()
    }

    //Get current Location via google fused client
    private fun fetchLocation() {
        client!!.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                val mapFragment = this.childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.isMyLocationEnabled = true
        if (currentLocation != null) {
            val latLng = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            val location =
                currentLocation!!.latitude.toString() + ", " + currentLocation!!.longitude
            viewModel.fetchData(location, radius, type, apiKey)
        }
        //Observe restaurant Local Data
        viewModel.restaurantData.observe(this, Observer{
            adapter = restaurantAdapter(baseActivity!!, it, this)
            recycler_map.addItemDecoration(DefaultItemDecorator(50, 80))
            recycler_map.layoutManager = LinearLayoutManager(baseActivity!!, LinearLayoutManager.HORIZONTAL, false)
            recycler_map.adapter = adapter
            it.forEach { restaurantData ->
                val latLng = LatLng(restaurantData.reslat!!, restaurantData.reslang!!)
                val markerOptions = MarkerOptions().position(latLng).title(restaurantData.resname)
                mMap!!.addMarker(markerOptions)
            }
        })

        viewModel.loading.observe(baseActivity!!, Observer{
            if (!it) {
                res_progress!!.visibility = View.GONE
            }
        })
    }

    override fun selectrestaurnt(data: restaurantData) {
        val latLng = LatLng(data.reslat!!, data.reslang!!)
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        val options = MarkerOptions().position(latLng).title(data.resname)
            .snippet(data.resaddress)
        mMap!!.addMarker(options).showInfoWindow()
    }
}
