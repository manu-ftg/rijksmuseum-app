package com.rijksmuseum.data.datasource.local

import android.content.SharedPreferences
import com.rijksmuseum.domain.model.CultureModel
import javax.inject.Inject

class RijksmuseumLocalDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private val DEFAULT_CULTURE: String = CultureModel.EN.value
    }

    fun getCulture(): String = sharedPreferences.getString("", DEFAULT_CULTURE) ?: DEFAULT_CULTURE

    fun updateCulture(culture: CultureModel): Boolean {
        val cultureHasChanged = getCulture() != culture.value
        sharedPreferences.edit().putString("", culture.value).apply()
        return cultureHasChanged
    }
}
