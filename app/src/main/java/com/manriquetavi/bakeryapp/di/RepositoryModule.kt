package com.manriquetavi.bakeryapp.di

import android.content.Context
import com.manriquetavi.bakeryapp.data.repository.DataStoreOperationsImpl
import com.manriquetavi.bakeryapp.data.repository.Repository
import com.manriquetavi.bakeryapp.domain.repository.DataStoreOperations
import com.manriquetavi.bakeryapp.domain.use_cases.UseCases
import com.manriquetavi.bakeryapp.domain.use_cases.get_user.GetUser
import com.manriquetavi.bakeryapp.domain.use_cases.is_user_authenticated.IsUserAuthenticated
import com.manriquetavi.bakeryapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.sign_in_credential.SignInWithCredential
import com.manriquetavi.bakeryapp.domain.use_cases.sign_in_email_password.SignInWithEmailAndPassword
import com.manriquetavi.bakeryapp.domain.use_cases.sign_out.SignOut
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations = DataStoreOperationsImpl(context = context)

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            signInWithEmailAndPassword = SignInWithEmailAndPassword(repository),
            signInWithCredential = SignInWithCredential(repository),
            signOut = SignOut(repository),
            isUserAuthenticated = IsUserAuthenticated(repository),
            getUser = GetUser(repository)
        )
    }

}