package com.rijksmuseum.data.datasource.local

import android.content.SharedPreferences
import com.rijksmuseum.data.entity.CultureEntity
import javax.inject.Inject

class RijksmuseumLocalDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private val DEFAULT_CULTURE: String = CultureEntity.EN.value
    }

    fun getCulture(): String = sharedPreferences.getString("", DEFAULT_CULTURE) ?: DEFAULT_CULTURE

    fun updateCulture(culture: CultureEntity) {
        sharedPreferences.edit().putString("", culture.value).apply()
    }
}
