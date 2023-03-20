package com.rijksmuseum.domain.usecase

import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetObjectsListUseCase @Inject constructor(
    private val repository: RijksmuseumRepository,
) {
    fun execute(page: Int): Flow<List<ObjectModel>> {
        return repository.getObjects(page)
    }
}
