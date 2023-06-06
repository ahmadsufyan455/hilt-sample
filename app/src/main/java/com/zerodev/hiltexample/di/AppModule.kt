package com.zerodev.hiltexample.di

import com.zerodev.hiltexample.network.ApiClient
import com.zerodev.hiltexample.repository.UserRepository
import com.zerodev.hiltexample.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiClient: ApiClient): UserRepository = UserRepository(apiClient)

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): ApiClient = retrofit
        .create(ApiClient::class.java)

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}