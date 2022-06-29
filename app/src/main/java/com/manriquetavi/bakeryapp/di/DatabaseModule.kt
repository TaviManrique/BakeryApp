package com.manriquetavi.bakeryapp.di

import android.content.Context
import androidx.room.Room
import com.manriquetavi.bakeryapp.data.local.BakeryDatabase
import com.manriquetavi.bakeryapp.data.repository.LocalDataSourceImpl
import com.manriquetavi.bakeryapp.domain.repository.LocalDataSource
import com.manriquetavi.bakeryapp.util.Constants.BAKERY_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BakeryDatabase = Room.databaseBuilder(
        context,
        BakeryDatabase::class.java,
        BAKERY_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        bakeryDatabase: BakeryDatabase
    ): LocalDataSource = LocalDataSourceImpl(bakeryDatabase = bakeryDatabase)
}