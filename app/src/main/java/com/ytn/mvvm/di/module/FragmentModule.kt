package com.ytn.mvvm.di.module

import com.ytn.mvvm.view.fragment.ShowMapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun providemapFragment(): ShowMapFragment
}
