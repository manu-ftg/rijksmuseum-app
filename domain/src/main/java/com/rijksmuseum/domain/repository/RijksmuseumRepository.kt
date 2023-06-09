package com.rijksmuseum.domain.repository

import com.rijksmuseum.domain.model.CultureModel
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import kotlinx.coroutines.flow.Flow

interface RijksmuseumRepository {
    fun getObjects(pageNumber: Int = 0): Flow<List<ObjectModel>>

    fun getObjectDetails(objectNumber: String): Flow<ObjectDetailsModel>

    fun setCulture(culture: CultureModel): Flow<Boolean>
}
