package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.repository.CarparkInfoRepository
import javax.inject.Inject

class UpdateCarparkInfoDatasetUseCase @Inject constructor(
    private val carparkInfoRepository: CarparkInfoRepository,
) {
    suspend operator fun invoke() = carparkInfoRepository.updateCarparkInfoDataset()
}