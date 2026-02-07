package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.repository.MetadataRepository
import javax.inject.Inject

class GetMetadataUseCase @Inject constructor(
    private val metadataRepository: MetadataRepository
) {

    suspend operator fun invoke() = metadataRepository.getCarparkInfoLastUpdated()
}