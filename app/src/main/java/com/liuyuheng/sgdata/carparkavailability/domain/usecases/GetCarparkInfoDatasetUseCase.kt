package com.liuyuheng.sgdata.carparkavailability.domain.usecases

import com.liuyuheng.sgdata.carparkavailability.domain.repositories.CarparkAvailabilityRepository
import javax.inject.Inject

class GetCarparkInfoDatasetUseCase @Inject constructor(
    private val carparkAvailabilityRepository: CarparkAvailabilityRepository
) {
    operator fun invoke() = carparkAvailabilityRepository.getCarparkInfoDataset()
}