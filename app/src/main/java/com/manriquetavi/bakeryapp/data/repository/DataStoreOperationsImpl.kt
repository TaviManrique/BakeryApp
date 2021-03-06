package com.manriquetavi.bakeryapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.manriquetavi.bakeryapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "bakery_app")
class DataStoreOperationsImpl(context: Context): DataStoreOperations {

    private object PreferenceKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val imageProfileKey = stringPreferencesKey(name = "image_profile")
    }
    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore
            .data
            .catch { exception ->
                if(exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    override suspend fun saveImageProfile(imageProfile: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.imageProfileKey] = imageProfile
        }
    }

    override fun readImageProfile(): Flow<String> =
        dataStore
            .data
            .catch { exception ->
                if(exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val imageProfile = preferences[PreferenceKey.imageProfileKey] ?: ""
                imageProfile
            }

}