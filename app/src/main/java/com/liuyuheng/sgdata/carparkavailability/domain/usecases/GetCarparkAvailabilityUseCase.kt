package com.liuyuheng.sgdata.carparkavailability.domain.usecases

import com.liuyuheng.sgdata.carparkavailability.domain.repositories.CarparkAvailabilityRepository
import javax.inject.Inject

class GetCarparkAvailabilityUseCase @Inject constructor(
    private val carparkAvailabilityRepository: CarparkAvailabilityRepository
) {
    fun getCarparkAvailabilityStream() = carparkAvailabilityRepository.carparkAvailabilityDataStream

    suspend fun fetchCarparkAvailability() = carparkAvailabilityRepository.fetchCarparkAvailability()
}