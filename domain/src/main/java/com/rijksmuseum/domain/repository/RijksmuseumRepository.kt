package com.rijksmuseum.domain.repository

import com.rijksmuseum.domain.model.ObjectModel
import kotlinx.coroutines.flow.Flow

interface RijksmuseumRepository {
    fun getObjects(pageNumber: Int = 0): Flow<List<ObjectModel>>
}
