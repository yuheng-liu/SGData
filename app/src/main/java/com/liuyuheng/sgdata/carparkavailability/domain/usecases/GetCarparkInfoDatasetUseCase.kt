package com.liuyuheng.sgdata.carparkavailability.domain.usecases

import com.liuyuheng.sgdata.carparkavailability.domain.repositories.CarparkInfoRepository
import javax.inject.Inject

class GetCarparkInfoDatasetUseCase @Inject constructor(
    private val carparkInfoRepository: CarparkInfoRepository
) {
    operator fun invoke() = carparkInfoRepository.getCarparkInfoDataset()
}