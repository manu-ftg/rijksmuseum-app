package com.rijksmuseum.di

import android.content.Context
import android.content.SharedPreferences
import com.rijksmuseum.BuildConfig
import com.rijksmuseum.data.di.ApiKey
import com.rijksmuseum.data.di.BaseUrl
import com.rijksmuseum.presentation.util.DefaultDispatcherProvider
import com.rijksmuseum.presentation.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val SHARED_PREFERENCES_NAME = "SharedPreferences"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}
