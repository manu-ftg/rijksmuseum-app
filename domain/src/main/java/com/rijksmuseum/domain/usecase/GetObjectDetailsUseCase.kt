package com.rijksmuseum.domain.usecase

import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetObjectDetailsUseCase @Inject constructor(
    private val repository: RijksmuseumRepository,
) {
    fun execute(objectNumber: String): Flow<ObjectDetailsModel> {
        return repository.getObjectDetails(objectNumber)
    }
}
