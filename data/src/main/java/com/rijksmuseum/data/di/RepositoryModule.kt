package com.rijksmuseum.data.di

import com.rijksmuseum.data.datasource.remote.RijksmuseumRemoteDatasource
import com.rijksmuseum.data.repository.RijksmuseumRepositoryImpl
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRijksmuseumRepository(
        remoteDataSource: RijksmuseumRemoteDatasource,
    ): RijksmuseumRepository {
        return RijksmuseumRepositoryImpl(
            remoteDatasource = remoteDataSource
        )
    }
}
