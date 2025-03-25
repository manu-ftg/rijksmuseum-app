package com.rijksmuseum.data.repository

import com.rijksmuseum.data.datasource.local.RijksmuseumLocalDatasource
import com.rijksmuseum.data.datasource.remote.RijksmuseumRemoteDatasource
import com.rijksmuseum.data.mapper.toDomain
import com.rijksmuseum.data.pagination.PaginatedData
import com.rijksmuseum.domain.model.AppEventsModel
import com.rijksmuseum.domain.model.CultureModel
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.model.PageDataModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RijksmuseumRepositoryImpl(
    private val remoteDatasource: RijksmuseumRemoteDatasource,
    private val localDatasource: RijksmuseumLocalDatasource
): RijksmuseumRepository {

    private val _appEvents: MutableSharedFlow<AppEventsModel> = MutableSharedFlow()
    val appEvents = _appEvents.asSharedFlow()

    override suspend fun getObjects(page: Int?): PageDataModel<ObjectModel> {
        return rijksmuseumPaginatedData.getPageData(page)
    }

    override suspend fun getObjectDetails(objectNumber: String): Result<ObjectDetailsModel> {
        return withContext(Dispatchers.IO) {
            try {
                val objectDetailsResponse = remoteDatasource.getObjectDetails(objectNumber = objectNumber)
                Result.success(objectDetailsResponse.artObject.toDomain())
            } catch (error: Throwable) {
                Result.failure(error)
            }
        }
    }

    override fun setCulture(culture: CultureModel): Flow<Boolean> {
        return flow {
            localDatasource.updateCulture(culture).let {
                _appEvents.emit(AppEventsModel.CultureChanged(culture))
                emit(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    private val rijksmuseumPaginatedData = PaginatedData<ObjectModel>(
        startPage = RIJKSMUSEUM_OBJECTS_INITIAL_PAGE,
        pageSize = RIJKSMUSEUM_OBJECTS_PAGE_SIZE,
        maxItems = RIJKSMUSEUM_MAX_OBJECTS,
        fetchPage = { page, pageSize ->
            remoteDatasource.getObjects(
                pageNumber = page,
                pageSize = pageSize
            ).artObjects.mapNotNull { it.toDomain() }
        }
    )

    companion object {
        private const val RIJKSMUSEUM_OBJECTS_INITIAL_PAGE: Int = 1
        private const val RIJKSMUSEUM_OBJECTS_PAGE_SIZE: Int = 10
        private const val RIJKSMUSEUM_MAX_OBJECTS: Int = 10000
    }
}
