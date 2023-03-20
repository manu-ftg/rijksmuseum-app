package com.rijksmuseum.data.di

import com.rijksmuseum.data.BuildConfig
import com.rijksmuseum.data.datasource.local.RijksmuseumLocalDatasource
import com.rijksmuseum.data.interceptors.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor?,
        requestInterceptor: RequestInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(requestInterceptor)
                loggingInterceptor?.let { addInterceptor(it) }
            }
            .build()
    }

    @Provides
    fun provideConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        converterFactory: MoshiConverterFactory,
        @BaseUrl baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor? {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else null
    }

    @Singleton
    @Provides
    fun provideRequestInterceptor(
        @ApiKey apiKey: String,
        localDatasource: RijksmuseumLocalDatasource
    ): RequestInterceptor {
        return RequestInterceptor(
            localDatasource = localDatasource,
            apiKey = apiKey
        )
    }
}
