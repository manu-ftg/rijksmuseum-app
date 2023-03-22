package com.rijksmuseum.domain.usecase

import com.rijksmuseum.domain.model.CultureModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetCultureUseCase @Inject constructor(
    private val repository: RijksmuseumRepository,
) {
    fun execute(culture: CultureModel): Flow<Boolean> {
        return repository.setCulture(culture)
    }
}
