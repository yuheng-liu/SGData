package com.liuyuheng.sgdata.data.repository

import com.liuyuheng.sgdata.data.network.DataStoreSearchApi
import com.liuyuheng.sgdata.domain.repository.CarparkInfoRepository
import javax.inject.Inject

class CarparkInfoRepositoryImpl @Inject constructor(
    private val carparkInfoApi: DataStoreSearchApi
) : CarparkInfoRepository {

    override suspend fun getCarparkInfoDataset() {
        safeApiCall {
            val carparkInfoResponse = carparkInfoApi.getCarparkInfoDataset()
            carparkInfoResponse
        }
    }
}