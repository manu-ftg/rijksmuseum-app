package com.rijksmuseum.data.datasource.remote

import com.rijksmuseum.data.entity.ObjectsEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RijksmuseumRemoteDatasource {

    @GET("collection?s=artist")
    suspend fun getObjects(
        @Query("p") pageNumber: Int = 0,
        @Query("ps") pageSize: Int = 10,
    ): ObjectsEntity
}
