package com.ytn.mvvm.di.module

import com.ytn.mvvm.view.activity.MapsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMapActivity(): MapsActivity
}
