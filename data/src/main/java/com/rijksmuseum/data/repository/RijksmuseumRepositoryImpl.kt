package com.rijksmuseum.data.repository

import com.rijksmuseum.data.datasource.local.RijksmuseumLocalDatasource
import com.rijksmuseum.data.datasource.remote.RijksmuseumRemoteDatasource
import com.rijksmuseum.data.mapper.toDomain
import com.rijksmuseum.domain.model.AppEventsModel
import com.rijksmuseum.domain.model.CultureModel
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RijksmuseumRepositoryImpl(
    private val remoteDatasource: RijksmuseumRemoteDatasource,
    private val localDatasource: RijksmuseumLocalDatasource
): RijksmuseumRepository {

    private val _appEvents: MutableSharedFlow<AppEventsModel> = MutableSharedFlow()
    val appEvents = _appEvents.asSharedFlow()

    override fun getObjects(pageNumber: Int): Flow<List<ObjectModel>> {
        return flow {
            val objectsResponse = remoteDatasource.getObjects(pageNumber = pageNumber)
            emit(
                objectsResponse.artObjects.mapNotNull { it.toDomain() }
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getObjectDetails(objectNumber: String): Flow<ObjectDetailsModel> {
        return flow {
            val objectDetailsResponse = remoteDatasource.getObjectDetails(objectNumber = objectNumber)
            emit(
                objectDetailsResponse.artObject.toDomain()
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun setCulture(culture: CultureModel): Flow<Boolean> {
        return flow {
            localDatasource.updateCulture(culture).let {
                _appEvents.emit(AppEventsModel.CultureChanged(culture))
                emit(it)
            }
        }.flowOn(Dispatchers.IO)
    }
}
