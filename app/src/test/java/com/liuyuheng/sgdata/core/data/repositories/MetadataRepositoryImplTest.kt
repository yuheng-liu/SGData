package com.liuyuheng.sgdata.core.data.repositories

import com.liuyuheng.sgdata.core.data.models.dao.MetadataDao
import com.liuyuheng.sgdata.core.data.models.dao.MetadataEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class MetadataRepositoryImplTest {

    private val metadataDao: MetadataDao = mockk()
    private lateinit var repository: MetadataRepositoryImpl

    @Before
    fun setUp() {
        repository = MetadataRepositoryImpl(metadataDao)
    }

    @Test
    fun `getCarparkInfoLastUpdated returns parsed LocalDateTime if value exists`() = runTest {
        val testDateString = "2026-06-25T12:00:00"
        val expectedDate = LocalDateTime.of(2026, 6, 25, 12, 0)

        coEvery { metadataDao.getValue(MetadataRepositoryImpl.CARPARK_INFO_LAST_UPDATED) } returns testDateString

        val result = repository.getCarparkInfoLastUpdated()

        assertEquals(expectedDate, result)
        coVerify(exactly = 1) { metadataDao.getValue(MetadataRepositoryImpl.CARPARK_INFO_LAST_UPDATED) }
    }

    @Test
    fun `getCarparkInfoLastUpdated returns null if value does not exist`() = runTest {
        coEvery { metadataDao.getValue(MetadataRepositoryImpl.CARPARK_INFO_LAST_UPDATED) } returns null

        val result = repository.getCarparkInfoLastUpdated()

        assertNull(result)
        coVerify(exactly = 1) { metadataDao.getValue(MetadataRepositoryImpl.CARPARK_INFO_LAST_UPDATED) }
    }

    @Test
    fun `addMetaData inserts MetadataEntity into dao`() = runTest {
        val key = "some_key"
        val value = "some_value"
        val expectedEntity = MetadataEntity(key = key, value = value)

        coEvery { metadataDao.insert(any()) } returns Unit

        repository.addMetaData(key, value)

        coVerify(exactly = 1) { metadataDao.insert(expectedEntity) }
    }
}
