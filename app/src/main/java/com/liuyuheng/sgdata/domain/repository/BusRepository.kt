package com.liuyuheng.sgdata.domain.repository

import com.liuyuheng.sgdata.domain.entity.Bus

interface BusRepository {
    suspend fun getBuses(): List<Bus>
}