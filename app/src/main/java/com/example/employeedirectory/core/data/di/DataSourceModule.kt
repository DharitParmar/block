package com.example.employeedirectory.core.data.di

import com.example.employeedirectory.BuildConfig
import com.example.employeedirectory.core.data.employee.EmployeeDataSource
import com.example.employeedirectory.core.data.employee.EmployeeNetworkDataSource
import com.example.employeedirectory.core.data.employee.RetrofitEmployeeNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class NetworkEmployeeDataSource

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @NetworkEmployeeDataSource
    @Provides
    fun providesEmployeeNetworkDataSource(
        retrofitEmployeeNetworkApi: RetrofitEmployeeNetworkApi
    ): EmployeeDataSource = EmployeeNetworkDataSource(retrofitEmployeeNetworkApi)
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkApiModule {

    @Singleton
    @Provides
    fun provideEmployeeNetworkApi(retrofit: Retrofit): RetrofitEmployeeNetworkApi {
        return retrofit
            .create(RetrofitEmployeeNetworkApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://s3.amazonaws.com/sq-mobile-interview/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

}