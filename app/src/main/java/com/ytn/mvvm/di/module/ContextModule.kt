package com.ytn.mvvm.di.module

import android.content.Context
import com.ytn.mvvm.base.BaseApplication
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ContextModule {
    @Binds
    internal abstract fun provideContext(application: BaseApplication): Context
}
