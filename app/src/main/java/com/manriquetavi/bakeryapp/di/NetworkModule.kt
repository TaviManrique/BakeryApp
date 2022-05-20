package com.manriquetavi.bakeryapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.manriquetavi.bakeryapp.data.repository.FirebaseAuthSourceImpl
import com.manriquetavi.bakeryapp.data.repository.FirestoreDataSourceImpl
import com.manriquetavi.bakeryapp.domain.repository.FirebaseAuthSource
import com.manriquetavi.bakeryapp.domain.repository.FirestoreDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthRepositoryImpl(auth: FirebaseAuth, firestore: FirebaseFirestore): FirebaseAuthSource = FirebaseAuthSourceImpl(auth = auth, firestore = firestore)

    @Provides
    @Singleton
    fun provideFirebaseFirestoreRepositoryImpl(firestore: FirebaseFirestore): FirestoreDataSource = FirestoreDataSourceImpl(firestore = firestore)

}