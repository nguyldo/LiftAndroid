package com.nguyendo.lift.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nguyendo.lift.data.repo.AuthRepo
import com.nguyendo.lift.data.repo.AuthRepoImpl
import com.nguyendo.lift.data.repo.UserRemoteRepo
import com.nguyendo.lift.data.repo.UserRemoteRepoImpl
import com.nguyendo.lift.data.repo.WorkoutsRemoteRepo
import com.nguyendo.lift.data.repo.WorkoutsRemoteRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {
    @Provides
    fun provideAuthRepo(): AuthRepo = AuthRepoImpl(provideFirebaseAuth())

    @Provides
    fun provideUserRemoteRepo(): UserRemoteRepo = UserRemoteRepoImpl(provideFirebaseFirestore())

    @Provides
    fun provideWorkoutsRemoteRepo(): WorkoutsRemoteRepo = WorkoutsRemoteRepoImpl(
        provideFirebaseFirestore(),
    )

    @Provides
    fun provideCreateWorkoutService(): CreateWorkoutService = CreateWorkoutServiceImpl()

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}