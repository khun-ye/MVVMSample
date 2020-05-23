package com.ytn.mvvm.di.module

import android.content.Context

import androidx.room.Room
import com.ytn.mvvm.data.database.db.yatharDatabase
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDb(context: Context): yatharDatabase {
        return Room.databaseBuilder(context, yatharDatabase::class.java, yatharDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: yatharDatabase) = db.restaurantDao()
}
