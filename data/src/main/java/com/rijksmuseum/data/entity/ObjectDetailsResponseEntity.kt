package com.rijksmuseum.data.entity
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class ObjectDetailsResponseEntity(
    @Json(name = "artObject")
    val artObject: ArtObjectEntity,
    @Json(name = "elapsedMilliseconds")
    val elapsedMilliseconds: Int
)

@JsonClass(generateAdapter = true)
data class ArtObjectEntity(
    @Json(name = "acquisition")
    val acquisition: AcquisitionEntity?,
    @Json(name = "classification")
    val classification: ClassificationEntity?,
    @Json(name = "colors")
    val colors: List<ColorEntity>?,
    @Json(name = "colorsWithNormalization")
    val colorsWithNormalization: List<ColorsWithNormalization>?,
    @Json(name = "dating")
    val dating: DatingEntity?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "dimensions")
    val dimensions: List<DimensionEntity>?,
    @Json(name = "documentation")
    val documentation: List<String>?,
    @Json(name = "hasImage")
    val hasImage: Boolean,
    @Json(name = "historicalPersons")
    val historicalPersons: List<String>?,
    @Json(name = "id")
    val id: String,
    @Json(name = "label")
    val label: LabelEntity,
    @Json(name = "language")
    val language: String,
    @Json(name = "links")
    val links: ObjectLinksEntity?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "longTitle")
    val longTitle: String?,
    @Json(name = "materials")
    val materials: List<String>?,
    @Json(name = "normalized32Colors")
    val normalized32Colors: List<Normalized32ColorEntity>?,
    @Json(name = "normalizedColors")
    val normalizedColors: List<Normalized32ColorEntity>?,
    @Json(name = "objectCollection")
    val objectCollection: List<String>?,
    @Json(name = "objectNumber")
    val objectNumber: String,
    @Json(name = "objectTypes")
    val objectTypes: List<String>?,
    @Json(name = "physicalMedium")
    val physicalMedium: String?,
    @Json(name = "plaqueDescriptionDutch")
    val plaqueDescriptionDutch: String?,
    @Json(name = "plaqueDescriptionEnglish")
    val plaqueDescriptionEnglish: String?,
    @Json(name = "principalMaker")
    val principalMaker: String,
    @Json(name = "principalMakers")
    val principalMakers: List<PrincipalMakerEntity>?,
    @Json(name = "principalOrFirstMaker")
    val principalOrFirstMaker: String,
    @Json(name = "priref")
    val priref: String?,
    @Json(name = "productionPlaces")
    val productionPlaces: List<String>?,
    @Json(name = "scLabelLine")
    val scLabelLine: String?,
    @Json(name = "showImage")
    val showImage: Boolean,
    @Json(name = "subTitle")
    val subTitle: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "titles")
    val titles: List<String>?,
    @Json(name = "webImage")
    val webImage: ObjectImageEntity?
)

@JsonClass(generateAdapter = true)
data class AcquisitionEntity(
    @Json(name = "creditLine")
    val creditLine: String?,
    @Json(name = "date")
    val date: String?,
    @Json(name = "method")
    val method: String?
)

@JsonClass(generateAdapter = true)
data class ClassificationEntity(
    @Json(name = "iconClassIdentifier")
    val iconClassIdentifier: List<String>?
)

@JsonClass(generateAdapter = true)
data class ColorEntity(
    @Json(name = "hex")
    val hex: String?,
    @Json(name = "percentage")
    val percentage: Int?
)

@JsonClass(generateAdapter = true)
data class ColorsWithNormalization(
    @Json(name = "normalizedHex")
    val normalizedHex: String?,
    @Json(name = "originalHex")
    val originalHex: String?
)

@JsonClass(generateAdapter = true)
data class DatingEntity(
    @Json(name = "period")
    val period: Int?,
    @Json(name = "presentingDate")
    val presentingDate: String?,
    @Json(name = "sortingDate")
    val sortingDate: Int?,
    @Json(name = "yearEarly")
    val yearEarly: Int?,
    @Json(name = "yearLate")
    val yearLate: Int?
)

@JsonClass(generateAdapter = true)
data class DimensionEntity(
    @Json(name = "type")
    val type: String?,
    @Json(name = "unit")
    val unit: String?,
    @Json(name = "value")
    val value: String?
)

@JsonClass(generateAdapter = true)
data class LabelEntity(
    @Json(name = "date")
    val date: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "makerLine")
    val makerLine: String?,
    @Json(name = "notes")
    val notes: String?,
    @Json(name = "title")
    val title: String?
)

@JsonClass(generateAdapter = true)
data class ObjectLinksEntity(
    @Json(name = "search")
    val search: String?
)

@JsonClass(generateAdapter = true)
data class Normalized32ColorEntity(
    @Json(name = "hex")
    val hex: String?,
    @Json(name = "percentage")
    val percentage: Int?
)

@JsonClass(generateAdapter = true)
data class PrincipalMakerEntity(
    @Json(name = "dateOfBirth")
    val dateOfBirth: String?,
    @Json(name = "dateOfDeath")
    val dateOfDeath: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "nationality")
    val nationality: String?,
    @Json(name = "occupation")
    val occupation: List<String>?,
    @Json(name = "placeOfBirth")
    val placeOfBirth: String?,
    @Json(name = "placeOfDeath")
    val placeOfDeath: String?,
    @Json(name = "productionPlaces")
    val productionPlaces: List<String>?,
    @Json(name = "roles")
    val roles: List<String>?,
    @Json(name = "unFixedName")
    val unFixedName: String?
)
