package com.liuyuheng.sgdata.core.domain.usecases

import com.liuyuheng.sgdata.core.domain.repositories.MetadataRepository
import javax.inject.Inject

class GetMetadataUseCase @Inject constructor(
    private val metadataRepository: MetadataRepository
) {

    suspend operator fun invoke() = metadataRepository.getCarparkInfoLastUpdated()
}