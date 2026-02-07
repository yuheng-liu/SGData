package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.repository.CarparkInfoRepository
import javax.inject.Inject

class GetCarparkInfoDatasetUseCase @Inject constructor(
    private val carparkInfoRepository: CarparkInfoRepository
) {
    operator fun invoke() = carparkInfoRepository.getCarparkInfoDataset()
}