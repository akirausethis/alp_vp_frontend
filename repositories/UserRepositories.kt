package com.example.alp_vp.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.alp_vp.models.GeneralModel
import com.example.alp_vp.services.UserAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Call

interface UserRepository {
    val currentUserToken: Flow<String?>
    val currentUsername: Flow<String?>

    fun logout(token: String): Call<GeneralModel>

    suspend fun saveUserToken(token: String)

    suspend fun saveUsername(username: String)
}

class NetworkUserRepository(
    private val userDataStore: DataStore<Preferences>,
    private val userAPIService: UserAPIService
) : UserRepository {
    private companion object {
        val USER_TOKEN = stringPreferencesKey("token")
        val USERNAME = stringPreferencesKey("username")
    }

    override val currentUserToken: Flow<String?> = userDataStore.data.map { preferences ->
        preferences[USER_TOKEN]
    }

    override val currentUsername: Flow<String?> = userDataStore.data.map { preferences ->
        preferences[USERNAME]
    }

    override suspend fun saveUserToken(token: String) {
        userDataStore.edit { preferences ->
            preferences[USER_TOKEN] = token
        }
    }

    override suspend fun saveUsername(username: String) {
        userDataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    override fun logout(token: String): Call<GeneralModel> {
        return userAPIService.logout(token)
    }
}
