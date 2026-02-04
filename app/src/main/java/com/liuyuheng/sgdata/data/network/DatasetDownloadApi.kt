package com.liuyuheng.sgdata.data.network

import com.liuyuheng.sgdata.data.model.DatasetDownloadDto
import com.liuyuheng.sgdata.data.model.DatasetPollDownloadDto
import com.liuyuheng.sgdata.data.network.response.BaseResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

private const val HDB_CARPARK_INFO_DATASET_ID = "d_23f946fa557947f93a8043bbef41dd09"

private const val PATH_DATASET_ID = "datasetId"

interface DatasetDownloadApi {

    @GET("{$PATH_DATASET_ID}/initiate-download")
    suspend fun initiateCarparkInfoDownload(
        @Path(PATH_DATASET_ID) datasetId: String = HDB_CARPARK_INFO_DATASET_ID,
    ): BaseResponse<DatasetDownloadDto>

    // Online docs mentioned need to use this after download is initiated, but it seems to work without it
    // keeping it for now
    @GET("{$PATH_DATASET_ID}/poll-download")
    suspend fun pollCarparkInfoDownload(
        @Path(PATH_DATASET_ID) datasetId: String = HDB_CARPARK_INFO_DATASET_ID,
    ): BaseResponse<DatasetPollDownloadDto>

    @GET
    suspend fun downloadFromUrl(
        @Url url: String
    ): Response<ResponseBody>
}