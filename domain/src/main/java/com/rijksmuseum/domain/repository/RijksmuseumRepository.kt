package com.rijksmuseum.domain.repository

import com.rijksmuseum.domain.model.CultureModel
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.model.PageDataModel
import kotlinx.coroutines.flow.Flow

interface RijksmuseumRepository {

    suspend fun getObjects(page: Int?): PageDataModel<ObjectModel>

    suspend fun getObjectDetails(objectNumber: String): Result<ObjectDetailsModel>

    fun setCulture(culture: CultureModel): Flow<Boolean>
}
