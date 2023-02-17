package com.example.restaurantapp.dependecyInjection

import com.example.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // OkHttpClient: It provides a singleton instance of OkHttpClient.
    // It sets a timeout of 20 seconds for both connect and read operations.
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit: It provides a singleton instance of Retrofit by building it with a base URL, an instance of OkHttpClient,
    // and a GsonConverterFactory for serialization/deserialization of network calls.
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // ApiService: It provides a singleton instance of the ApiService interface.
    // It is created by calling retrofit.create().
    // The ApiService interface defines the API endpoints that the application uses.
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}