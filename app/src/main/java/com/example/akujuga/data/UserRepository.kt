package com.example.akujuga.data

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import com.example.akujuga.R
import com.example.akujuga.data.remote.response.AlphabetResponse
import com.example.akujuga.data.remote.response.DictionaryResponse
import com.example.akujuga.data.remote.response.NumberResponse
import com.example.akujuga.data.remote.response.PredictAlphabetResponse
import com.example.akujuga.data.remote.response.PredictNumberResponse
import com.example.akujuga.data.remote.retrofit.ApiService
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserRepository private constructor(
    private val auth: FirebaseAuth,
    private val apiService: ApiService
) {

    suspend fun getNumber(): NumberResponse? {
        return try {
            withContext(Dispatchers.IO) {
                apiService.getNumbers()
            }
        } catch (e: Exception) {
            Log.e("Number", "Error fetching Data", e)
            null
        }
    }

    suspend fun getAlphabet(): AlphabetResponse? {
        return try {
            withContext(Dispatchers.IO) {
                apiService.getAlphabets()
            }
        } catch (e: Exception) {
            Log.e("Alphabet", "Error fetching Data", e)
            null
        }
    }

    suspend fun classifyImageAlphabet(imageFile: File): PredictAlphabetResponse? {
        return try {
            val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageFile)
            val body = MultipartBody.Part.createFormData("file", imageFile.name, requestFile)
            apiService.classifyImageAlphabet(body)
        } catch (e: Exception) {
            Log.e(TAG, "Error uploading image", e)
            null
        }
    }

    suspend fun classifyImageNumber(imageFile: File): PredictNumberResponse? {
        return try {
            val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageFile)
            val body = MultipartBody.Part.createFormData("file", imageFile.name, requestBody)
            apiService.classifyImageNumber(body)
        } catch (e: Exception) {
            Log.e(TAG, "Error uploading image", e)
            null
        }
    }

    suspend fun getDictionary(): DictionaryResponse? {
        return try {
            withContext(Dispatchers.IO) {
                apiService.getDictionary()
            }
        } catch (e: Exception) {
            Log.e("Dictionary", "Error fetching Data", e)
            null
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    suspend fun signInAnonymously(context: Context): FirebaseUser? {
        return try {
            val authResult = auth.signInAnonymously().await()
            authResult.user
        } catch (e: Exception) {
            Log.w(TAG, "signInAnonymously:failure", e)
            Toast.makeText(context, "Guest Login Failed.", Toast.LENGTH_SHORT).show()
            null
        }
    }

    suspend fun signInWithGoogle(context: Context): FirebaseUser? {
        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return try {
            val result = credentialManager.getCredential(context, request)
            handleGoogleSignIn(result)
        } catch (e: GetCredentialException) {
            Log.d(TAG, e.message.toString())
            null
        }
    }

    private suspend fun handleGoogleSignIn(result: GetCredentialResponse): FirebaseUser? {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        return firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                } else {
                    Log.e(TAG, "Unexpected type of credential")
                }
            }
            else -> {
                Log.e(TAG, "Unexpected type of credential")
            }
        }
        return null
    }

    private suspend fun firebaseAuthWithGoogle(idToken: String): FirebaseUser? {
        return try {
            val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            authResult.user
        } catch (e: Exception) {
            Log.w(TAG, "signInWithCredential:failure", e)
            null
        }
    }

    fun logout() {
        auth.signOut()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            auth: FirebaseAuth,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(auth, apiService)
            }.also { instance = it }
    }
}
