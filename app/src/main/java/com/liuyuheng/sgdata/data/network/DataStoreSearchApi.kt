package com.liuyuheng.sgdata.data.network

import com.liuyuheng.sgdata.data.model.CarparkInfoDto
import com.liuyuheng.sgdata.data.network.response.DataStoreSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query


private const val HDB_CARPARK_INFO_DATASET_ID = "d_23f946fa557947f93a8043bbef41dd09"

private const val QUERY_RESOURCE_ID = "resource_id"
private const val QUERY_OFFSET = "offset"

interface DataStoreSearchApi {

    @GET(".")
    suspend fun getCarparkInfoDataset(
        @Query(value = QUERY_RESOURCE_ID) resourceId: String = HDB_CARPARK_INFO_DATASET_ID,
        @Query(value = QUERY_OFFSET) offset: Int? = null
    ): DataStoreSearchResponse<CarparkInfoDto>
}