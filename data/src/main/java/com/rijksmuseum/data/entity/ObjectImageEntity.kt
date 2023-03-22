package com.rijksmuseum.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ObjectImageEntity(
    @Json(name = "guid")
    val guid: String? = null,
    @Json(name = "height")
    val height: Int = 0,
    @Json(name = "offsetPercentageX")
    val offsetPercentageX: Int = 0,
    @Json(name = "offsetPercentageY")
    val offsetPercentageY: Int = 0,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "width")
    val width: Int = 0
)
