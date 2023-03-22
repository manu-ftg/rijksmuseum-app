package com.rijksmuseum.presentation.mapper

import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import org.junit.Assert.assertEquals
import org.junit.Test

class ViewMapperTest {
    @Test
    fun objectMappedToDisplayCorrectly() {
        val objectModel = getObjectModel()
        val objectDisplay = getObjectDisplay()
        assertEquals(objectDisplay, objectModel.toViewData())
    }

    @Test
    fun objectsMapMappedToItemsListCorrectly() {
        val objectsMap = buildObjectItemsList(listOf(), getObjectsList())
        val objectDisplaysList = getObjectDisplaysList()
        assertEquals(objectDisplaysList, objectsMap.toList())
    }

    private fun getObjectModel() = ObjectModel(
        id = "id",
        imageUrl = "url",
        objectNumber = "objectNumber",
        title = "title",
        artist = "artist"
    )

    private fun getObjectDisplay() = ObjectItemViewData.ObjectItem(
        id = "id",
        title = "title",
        artist = "artist",
        objectNumber = "objectNumber",
        imageUrl = "url"
    )

    private fun getObjectsList() = listOf(
        ObjectModel("id1", "url", "number1", "title", "artistA"),
        ObjectModel("id2", "url", "number2", "title", "artistA"),
        ObjectModel("id3", "url", "number3", "title", "artistB")
    )


    private fun getObjectDisplaysList() = listOf(
        ObjectItemViewData.HeaderItem("artistA", "id1"),
        ObjectItemViewData.ObjectItem("id1", "title", "artistA", "number1", "url"),
        ObjectItemViewData.ObjectItem("id2", "title", "artistA", "number2", "url"),
        ObjectItemViewData.HeaderItem("artistB", "id3"),
        ObjectItemViewData.ObjectItem("id3", "title", "artistB", "number3", "url"),
        ObjectItemViewData.LoaderItem
    )
}
