package com.ytn.mvvm.di.component

import com.ytn.mvvm.base.BaseApplication
import com.ytn.mvvm.di.module.ActivityModule
import com.ytn.mvvm.di.module.ContextModule
import com.ytn.mvvm.di.module.RepositoryModule
import com.ytn.mvvm.di.module.ViewModelModule
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ActivityModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ContextModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): ApplicationComponent
    }
}
