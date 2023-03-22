package com.rijksmuseum.data.di

import com.rijksmuseum.data.datasource.remote.RijksmuseumRemoteDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    fun provideRijksmuseumRemoteDatasource(
        retrofit: Retrofit
    ): RijksmuseumRemoteDatasource {
        return retrofit.create(RijksmuseumRemoteDatasource::class.java)
    }
}
