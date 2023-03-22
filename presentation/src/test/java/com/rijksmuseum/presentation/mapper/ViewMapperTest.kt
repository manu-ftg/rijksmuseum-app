package com.rijksmuseum.presentation.mapper

import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.presentation.display.ObjectItemDisplay
import org.junit.Assert.assertEquals
import org.junit.Test

class ViewMapperTest {
    @Test
    fun objectMappedToDisplayCorrectly() {
        val objectModel = getObjectModel()
        val objectDisplay = getObjectDisplay()
        assertEquals(objectDisplay, objectModel.toDisplay())
    }

    @Test
    fun objectsMapMappedToItemsListCorrectly() {
        val objectsMap = getObjectsMap()
        val objectDisplaysList = getObjectDisplaysList()
        assertEquals(objectDisplaysList, objectsMap.toList())
    }

    private fun getObjectModel() = ObjectModel(
        id = "id",
        imageUrl = "url",
        longTitle = "longTitle",
        objectNumber = "objectNumber",
        title = "title",
        artist = "artist"
    )

    private fun getObjectDisplay() = ObjectItemDisplay.ObjectItem(
        id = "id",
        title = "title",
        artist = "artist",
        objectNumber = "objectNumber",
        imageUrl = "url"
    )

    private fun getObjectsMap() = mapOf(
        "artistA" to listOf(
            ObjectModel("id1", "url", "long", "number1", "title", "artistA"),
            ObjectModel("id2", "url", "long", "number2", "title", "artistA")
        ),
        "artistB" to listOf(
            ObjectModel("id3", "url", "long", "number3", "title", "artistB")
        )
    )

    private fun getObjectDisplaysList() = listOf(
        ObjectItemDisplay.HeaderItem("artistA"),
        ObjectItemDisplay.ObjectItem("id1", "title", "artistA", "number1", "url"),
        ObjectItemDisplay.ObjectItem("id2", "title", "artistA", "number2", "url"),
        ObjectItemDisplay.HeaderItem("artistB"),
        ObjectItemDisplay.ObjectItem("id3", "title", "artistB", "number3", "url"),
        ObjectItemDisplay.LoaderItem
    )
}
