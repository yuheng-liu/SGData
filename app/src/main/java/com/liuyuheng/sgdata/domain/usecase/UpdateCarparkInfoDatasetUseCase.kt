package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.repository.CarparkInfoRepository
import javax.inject.Inject

class UpdateCarparkInfoDatasetUseCase @Inject constructor(
    private val carparkInfoRepository: CarparkInfoRepository,
) {
    suspend fun updateCarparkInfoDataset() = carparkInfoRepository.updateCarparkInfoDataset()

    suspend fun ensureDatabaseUpToDate() = carparkInfoRepository.ensureDatabaseUpToDate()
}