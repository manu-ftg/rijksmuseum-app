package com.rijksmuseum.data.datasource.local

import android.content.SharedPreferences
import com.rijksmuseum.data.entity.ObjectEntity
import com.rijksmuseum.domain.model.CultureModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RijksmuseumLocalDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getCulture(): String = sharedPreferences.getString(CULTURE_KEY, DEFAULT_CULTURE) ?: DEFAULT_CULTURE

    fun updateCulture(culture: CultureModel): Boolean {
        val cultureHasChanged = getCulture() != culture.value
        sharedPreferences.edit().putString(CULTURE_KEY, culture.value).apply()
        return cultureHasChanged
    }

    companion object {
        private val DEFAULT_CULTURE: String = CultureModel.EN.value
        private const val CULTURE_KEY = "CULTURE_KEY"
    }
}
