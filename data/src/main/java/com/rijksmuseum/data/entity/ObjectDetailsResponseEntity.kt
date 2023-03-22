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
    val acquisition: AcquisitionEntity? = null,
    @Json(name = "classification")
    val classification: ClassificationEntity? = null,
    @Json(name = "colors")
    val colors: List<ColorEntity>? = null,
    @Json(name = "colorsWithNormalization")
    val colorsWithNormalization: List<ColorsWithNormalization>? = null,
    @Json(name = "dating")
    val dating: DatingEntity? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "dimensions")
    val dimensions: List<DimensionEntity>? = null,
    @Json(name = "documentation")
    val documentation: List<String>? = null,
    @Json(name = "hasImage")
    val hasImage: Boolean = false,
    @Json(name = "historicalPersons")
    val historicalPersons: List<String>? = null,
    @Json(name = "id")
    val id: String,
    @Json(name = "label")
    val label: LabelEntity? = null,
    @Json(name = "language")
    val language: String,
    @Json(name = "links")
    val links: ObjectLinksEntity? = null,
    @Json(name = "location")
    val location: String? = null,
    @Json(name = "longTitle")
    val longTitle: String? = null,
    @Json(name = "materials")
    val materials: List<String>? = null,
    @Json(name = "normalized32Colors")
    val normalized32Colors: List<Normalized32ColorEntity>? = null,
    @Json(name = "normalizedColors")
    val normalizedColors: List<Normalized32ColorEntity>? = null,
    @Json(name = "objectCollection")
    val objectCollection: List<String>? = null,
    @Json(name = "objectNumber")
    val objectNumber: String,
    @Json(name = "objectTypes")
    val objectTypes: List<String>? = null,
    @Json(name = "physicalMedium")
    val physicalMedium: String? = null,
    @Json(name = "plaqueDescriptionDutch")
    val plaqueDescriptionDutch: String? = null,
    @Json(name = "plaqueDescriptionEnglish")
    val plaqueDescriptionEnglish: String? = null,
    @Json(name = "principalMaker")
    val principalMaker: String,
    @Json(name = "principalMakers")
    val principalMakers: List<PrincipalMakerEntity>? = null,
    @Json(name = "principalOrFirstMaker")
    val principalOrFirstMaker: String,
    @Json(name = "priref")
    val priref: String? = null,
    @Json(name = "productionPlaces")
    val productionPlaces: List<String>? = null,
    @Json(name = "scLabelLine")
    val scLabelLine: String? = null,
    @Json(name = "showImage")
    val showImage: Boolean = false,
    @Json(name = "subTitle")
    val subTitle: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "titles")
    val titles: List<String>? = null,
    @Json(name = "webImage")
    val webImage: ObjectImageEntity? = null
)

@JsonClass(generateAdapter = true)
data class AcquisitionEntity(
    @Json(name = "creditLine")
    val creditLine: String? = null,
    @Json(name = "date")
    val date: String? = null,
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
    val hex: String? = null,
    @Json(name = "percentage")
    val percentage: Int?
)

@JsonClass(generateAdapter = true)
data class ColorsWithNormalization(
    @Json(name = "normalizedHex")
    val normalizedHex: String? = null,
    @Json(name = "originalHex")
    val originalHex: String?
)

@JsonClass(generateAdapter = true)
data class DatingEntity(
    @Json(name = "period")
    val period: Int? = null,
    @Json(name = "presentingDate")
    val presentingDate: String? = null,
    @Json(name = "sortingDate")
    val sortingDate: Int? = null,
    @Json(name = "yearEarly")
    val yearEarly: Int? = null,
    @Json(name = "yearLate")
    val yearLate: Int?
)

@JsonClass(generateAdapter = true)
data class DimensionEntity(
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "unit")
    val unit: String? = null,
    @Json(name = "value")
    val value: String?
)

@JsonClass(generateAdapter = true)
data class LabelEntity(
    @Json(name = "date")
    val date: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "makerLine")
    val makerLine: String? = null,
    @Json(name = "notes")
    val notes: String? = null,
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
    val hex: String? = null,
    @Json(name = "percentage")
    val percentage: Int?
)

@JsonClass(generateAdapter = true)
data class PrincipalMakerEntity(
    @Json(name = "dateOfBirth")
    val dateOfBirth: String? = null,
    @Json(name = "dateOfDeath")
    val dateOfDeath: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "nationality")
    val nationality: String? = null,
    @Json(name = "occupation")
    val occupation: List<String>? = null,
    @Json(name = "placeOfBirth")
    val placeOfBirth: String? = null,
    @Json(name = "placeOfDeath")
    val placeOfDeath: String? = null,
    @Json(name = "productionPlaces")
    val productionPlaces: List<String>? = null,
    @Json(name = "roles")
    val roles: List<String>? = null,
    @Json(name = "unFixedName")
    val unFixedName: String?
)
