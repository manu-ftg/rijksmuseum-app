package com.rijksmuseum.data.datasource.remote

import com.rijksmuseum.data.entity.ObjectDetailsResponseEntity
import com.rijksmuseum.data.entity.ObjectsResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RijksmuseumRemoteDatasource {

    @GET("collection?s=artist")
    suspend fun getObjects(
        @Query("p") pageNumber: Int = 0,
        @Query("ps") pageSize: Int = 10,
    ): ObjectsResponseEntity

    @GET("collection/{objectNumber}")
    suspend fun getObjectDetails(
        @Path("objectNumber") objectNumber: String
    ): ObjectDetailsResponseEntity
}
