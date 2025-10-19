package com.liuyuheng.sgdata.domain.usecase

import com.liuyuheng.sgdata.domain.entity.Bus
import com.liuyuheng.sgdata.domain.repository.BusRepository

class GetBusesUseCase(private val busRepository: BusRepository) {
    suspend fun execute(): List<Bus> {
        return busRepository.getBuses()
    }
}