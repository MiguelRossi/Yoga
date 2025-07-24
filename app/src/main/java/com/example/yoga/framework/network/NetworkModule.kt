package com.example.yoga.framework.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://yoga-api-nzy4.onrender.com/v1/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val interceptor
        get() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client
        get() = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): YogaService =
        retrofit.create(YogaService::class.java)
}
