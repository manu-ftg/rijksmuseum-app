package com.rijksmuseum.domain.usecase

import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.model.PageDataModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import javax.inject.Inject

class GetObjectsListPageUseCase @Inject constructor(
    private val repository: RijksmuseumRepository,
) {
    suspend fun execute(page: Int? = null): PageDataModel<ObjectModel> {
        return repository.getObjects(page)
    }
}
