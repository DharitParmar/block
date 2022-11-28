package com.example.employeedirectory.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Singleton
    @IoDispatcher
    @Provides
    fun providesCoroutineIODispatcher() : CoroutineDispatcher = Dispatchers.IO
}