package com.rijksmuseum.data.pagination

import com.rijksmuseum.domain.model.PageDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class PaginatedData<T>(
    val startPage: Int,
    val pageSize: Int,
    val maxItems: Int,
    val fetchPage: suspend (page: Int, pageSize: Int) -> List<T>
) {
    suspend fun getPageData(page: Int? = null): PageDataModel<T> {
        val pageNumber = page ?: startPage
        return if (pageNumber * pageSize > maxItems) {
            PageDataModel.EndOfData
        } else {
            withContext(Dispatchers.IO) {
                try {
                    PageDataModel.NewData(pageNumber, fetchPage(pageNumber, pageSize))
                } catch (error: Throwable) {
                    PageDataModel.Error(error)
                }
            }
        }
    }
}
