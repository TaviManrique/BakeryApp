package com.manriquetavi.bakeryapp.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.manriquetavi.bakeryapp.data.repository.FirebaseAuthSourceImpl
import com.manriquetavi.bakeryapp.domain.repository.FirebaseAuthSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Firebase
    //Auth
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuthSource = FirebaseAuthSourceImpl(auth = Firebase.auth)
}