package com.rijksmuseum.domain.model

sealed class AppEventsModel {

    data class CultureChanged(val culture: CultureModel): AppEventsModel()
}
