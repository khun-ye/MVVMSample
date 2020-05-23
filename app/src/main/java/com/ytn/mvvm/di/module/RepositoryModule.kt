package com.ytn.mvvm.di.module

import com.ytn.mvvm.data.api.RestEndpoint
import com.ytn.mvvm.data.database.db.yatharDatabase
import com.ytn.mvvm.data.datasource.restaurantLocalDatasource
import com.ytn.mvvm.data.datasource.restaurantRemoteDatasource
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module(includes = [NetworkModule::class, DbModule::class])
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(restEndpoint: RestEndpoint) = restaurantRemoteDatasource(restEndpoint)

    @Singleton
    @Provides
    fun provideLocalDataSource(yatharDatabase: yatharDatabase) = restaurantLocalDatasource(yatharDatabase)
}
