package com.android.recipe.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides

@Module
interface PresentationModule {
    companion object {
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth {
            return Firebase.auth
        }

        @Provides
        fun provideDatabaseReference(): DatabaseReference {
            return Firebase.database.reference
        }

        @Provides
        fun provideStorageReference(): StorageReference {
            return Firebase.storage.reference
        }
    }
}