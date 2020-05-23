package com.ytn.mvvm.view.activity

import android.Manifest
import android.os.Bundle
import android.util.Log
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.ytn.mvvm.R
import com.ytn.mvvm.base.BaseActivity
import com.ytn.mvvm.view.fragment.ShowMapFragment

class MapsActivity : BaseActivity() {

    override fun layoutRes(): Int {
        return R.layout.activity_maps
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
    }

    private fun requestPermission() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    //check if all permissions are granted
                    if (multiplePermissionsReport.areAllPermissionsGranted()) {
                        supportFragmentManager.beginTransaction().replace(R.id.frame_map, ShowMapFragment()).commit()
                    }
                    //check for permanent denial of any permissions
                    if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied) {

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: List<PermissionRequest>,
                    permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }
            })
            .withErrorListener { dexterError -> Log.d("dexter", dexterError.toString()) }
            .onSameThread()
            .check()
    }

}
