package com.manriquetavi.bakeryapp.di

import android.content.Context
import com.manriquetavi.bakeryapp.data.repository.DataStoreOperationsImpl
import com.manriquetavi.bakeryapp.data.repository.RepositoryAuthentication
import com.manriquetavi.bakeryapp.data.repository.RepositoryFirestore
import com.manriquetavi.bakeryapp.data.repository.RepositoryOnBoardingPage
import com.manriquetavi.bakeryapp.domain.repository.DataStoreOperations
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.get_auth_state.GetAuthState
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.is_user_authenticated.IsUserAuthenticated
import com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.save_onboarding.SaveOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_in_credential.SignInWithCredential
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_in_email_password.SignInWithEmailAndPassword
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_out.SignOut
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_up.SignUp
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_categories.GetAllCategories
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_promotions.GetAllPromotions
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_recommendations.GetRecommendations
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_selected_food.GetSelectedFood
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.search_foods.SearchFoods
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.user.GetUserDetails
import com.manriquetavi.bakeryapp.domain.use_cases.on_boarding_page.UseCasesOnBoardingPage
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
    fun provideUseCasesAuthentication(repositoryAuthentication: RepositoryAuthentication): UseCasesAuthentication {
        return UseCasesAuthentication(
            signInWithEmailAndPassword = SignInWithEmailAndPassword(repositoryAuthentication),
            signInWithCredential = SignInWithCredential(repositoryAuthentication),
            signOut = SignOut(repositoryAuthentication),
            isUserAuthenticated = IsUserAuthenticated(repositoryAuthentication),
            getAuthState = GetAuthState(repositoryAuthentication),
            signUp = SignUp(repositoryAuthentication)
        )
    }

    @Provides
    @Singleton
    fun provideUseCasesOnBoardingPage(repositoryOnBoardingPage: RepositoryOnBoardingPage): UseCasesOnBoardingPage {
        return UseCasesOnBoardingPage(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repositoryOnBoardingPage),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repositoryOnBoardingPage)
        )
    }

    @Provides
    @Singleton
    fun provideUseCasesFirestore(repositoryFirestore: RepositoryFirestore): UseCasesFirestore {
        return UseCasesFirestore(
            getUserDetails = GetUserDetails(repositoryFirestore),
            searchFoods = SearchFoods(repositoryFirestore),
            getSelectedFood = GetSelectedFood(repositoryFirestore),
            getAllCategories = GetAllCategories(repositoryFirestore),
            getAllPromotions = GetAllPromotions(repositoryFirestore),
            getRecommendations = GetRecommendations(repositoryFirestore)
        )
    }

}