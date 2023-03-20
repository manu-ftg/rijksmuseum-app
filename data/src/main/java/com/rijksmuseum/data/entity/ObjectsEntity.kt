package com.rijksmuseum.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ObjectsEntity(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "countFacets")
    val countFacets: CountFacetsEntity? = null,
    @Json(name = "elapsedMilliseconds")
    val elapsedMilliseconds: Int = 0,
    @Json(name = "artObjects")
    val artObjects: List<ObjectEntity>
)

@JsonClass(generateAdapter = true)
data class CountFacetsEntity(
    @Json(name = "hasimage")
    val hasImage: Int? = 0,
    @Json(name = "ondisplay")
    val onDisplay: Int? = 0
)

@JsonClass(generateAdapter = true)
data class ObjectEntity(
    @Json(name = "hasImage")
    val hasImage: Boolean = false,
    @Json(name = "headerImage")
    val headerImage: ObjectImageEntity? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "links")
    val links: LinksEntity? = null,
    @Json(name = "longTitle")
    val longTitle: String? = null,
    @Json(name = "objectNumber")
    val objectNumber: String? = null,
    @Json(name = "permitDownload")
    val permitDownload: Boolean = false,
    @Json(name = "principalOrFirstMaker")
    val principalOrFirstMaker: String? = null,
    @Json(name = "productionPlaces")
    val productionPlaces: List<String>? = null,
    @Json(name = "showImage")
    val showImage: Boolean = false,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "webImage")
    val webImage: ObjectImageEntity? = null,
)

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

@JsonClass(generateAdapter = true)
data class LinksEntity(
    @Json(name = "self")
    val self: String? = null,
    @Json(name = "web")
    val web: String? = null
)
