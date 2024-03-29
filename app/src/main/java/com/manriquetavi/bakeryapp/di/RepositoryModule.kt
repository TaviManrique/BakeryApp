package com.manriquetavi.bakeryapp.di

import android.content.Context
import com.manriquetavi.bakeryapp.data.repository.*
import com.manriquetavi.bakeryapp.domain.repository.DataStoreOperations
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.UseCasesAuthentication
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.get_auth_state.GetAuthState
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.is_user_authenticated.IsUserAuthenticated
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page.save_onboarding.SaveOnBoardingUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_in_credential.SignInWithCredential
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_in_email_password.SignInWithEmailAndPassword
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_out.SignOut
import com.manriquetavi.bakeryapp.domain.use_cases.authentication.sign_up.SignUp
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.image_profile.ReadImageProfileUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.image_profile.SaveImageProfileUseCase
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.UseCasesFirestore
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_categories.GetAllCategories
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_foods.GetAllFoods
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_foods_selected_category.GetAllFoodsSelectedCategory
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_promotions.GetAllPromotions
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_recommendations.GetRecommendations
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_selected_food.GetSelectedFood
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.search_foods.SearchFoods
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.user.GetUserDetails
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.UseCasesLocalDataSource
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.delete_all_foodscart.DeleteAllFoodsCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.delete_foodcart.DeleteFoodCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.get_all_foodscart.GetAllFoodsCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.increase_quantity.IncreaseQuantityFoodCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.insert_foodcart.InsertFoodCart
import com.manriquetavi.bakeryapp.domain.use_cases.local_data_source.minus_quantity.MinusQuantityFoodCart
import com.manriquetavi.bakeryapp.domain.use_cases.data_store.on_boarding_page.UseCasesDataStore
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders.AddOrder
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders.GetAllOrderByUser
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders.GetSelectedOrder
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
    fun provideUseCasesDataStore(repositoryDataStore: RepositoryDataStore): UseCasesDataStore {
        return UseCasesDataStore(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repositoryDataStore),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repositoryDataStore),
            saveImageProfileUseCase = SaveImageProfileUseCase(repositoryDataStore),
            readImageProfileUseCase = ReadImageProfileUseCase(repositoryDataStore)
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
            getRecommendations = GetRecommendations(repositoryFirestore),
            getAllFoodsSelectedCategory = GetAllFoodsSelectedCategory(repositoryFirestore),
            getAllFoods = GetAllFoods(repositoryFirestore),
            addOrder = AddOrder(repositoryFirestore),
            getAllOrderByUser = GetAllOrderByUser(repositoryFirestore),
            getSelectedOrder = GetSelectedOrder(repositoryFirestore)
        )
    }

    @Provides
    @Singleton
    fun provideUseCasesLocalDataSources(repositoryLocalDataSource: RepositoryLocalDataSource): UseCasesLocalDataSource {
        return UseCasesLocalDataSource(
            getAllFoodsCart = GetAllFoodsCart(repositoryLocalDataSource),
            insertFoodCart = InsertFoodCart(repositoryLocalDataSource),
            deleteAllFoodsCart = DeleteAllFoodsCart(repositoryLocalDataSource),
            deleteFoodCart = DeleteFoodCart(repositoryLocalDataSource),
            increaseQuantityFoodCart = IncreaseQuantityFoodCart(repositoryLocalDataSource),
            minusQuantityFoodCart = MinusQuantityFoodCart(repositoryLocalDataSource)
        )
    }

}