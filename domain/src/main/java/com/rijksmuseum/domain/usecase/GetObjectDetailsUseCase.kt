package com.rijksmuseum.domain.usecase

import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import javax.inject.Inject

class GetObjectDetailsUseCase @Inject constructor(
    private val repository: RijksmuseumRepository,
) {
    suspend fun execute(objectNumber: String): Result<ObjectDetailsModel> {
        return repository.getObjectDetails(objectNumber)
    }
}
