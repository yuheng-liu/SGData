package com.liuyuheng.sgdata.carparkavailability.domain.usecases

import com.liuyuheng.sgdata.carparkavailability.domain.repositories.CarparkInfoRepository
import javax.inject.Inject

class UpdateCarparkInfoDatasetUseCase @Inject constructor(
    private val carparkInfoRepository: CarparkInfoRepository,
) {
    suspend fun updateCarparkInfoDataset() = carparkInfoRepository.updateCarparkInfoDataset()

    suspend fun ensureDatabaseUpToDate() = carparkInfoRepository.ensureDatabaseUpToDate()
}