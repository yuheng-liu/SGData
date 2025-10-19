package com.liuyuheng.sgdata.data.repository

import com.liuyuheng.sgdata.domain.entity.Bus
import com.liuyuheng.sgdata.domain.repository.BusRepository
import kotlinx.coroutines.delay

class BusRepositoryImpl : BusRepository {
    override suspend fun getBuses(): List<Bus> {
        // Simulate a network delay
        delay(1500)
        return listOf(
            Bus(id = "1", number = "10"),
            Bus(id = "2", number = "24"),
            Bus(id = "3", number = "67"),
            Bus(id = "4", number = "196"),
            Bus(id = "5", number = "961")
        )
    }
}
