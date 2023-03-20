package com.rijksmuseum.data.repository

import com.rijksmuseum.data.datasource.remote.RijksmuseumRemoteDatasource
import com.rijksmuseum.data.mapper.DomainMapper.toDomain
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RijksmseumRepositoryImpl(
    private val remoteDatasource: RijksmuseumRemoteDatasource
): RijksmuseumRepository {

    override fun getObjects(pageNumber: Int): Flow<List<ObjectModel>> {
        return flow {
            val objectsResponse = remoteDatasource.getObjects(pageNumber = pageNumber)
            emit(
                objectsResponse.artObjects.mapNotNull { it.toDomain() }
            )
        }.flowOn(Dispatchers.IO)
    }
}
