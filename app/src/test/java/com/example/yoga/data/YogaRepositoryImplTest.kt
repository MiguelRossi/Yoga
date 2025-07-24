package com.example.yoga.data

import com.example.yoga.domain.model.Asana
import com.example.yoga.domain.repository.YogaRepository
import com.example.yoga.framework.network.AsanaDto
import com.example.yoga.framework.network.YogaService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class YogaRepositoryImplTest {

    private lateinit var repository: YogaRepository

    private val yogaService = mockk<YogaService>()
    private val asanaMapper = mockk<AsanaMapper>()

    private val fakeAsanaDto = AsanaDto(
        id = 123,
        englishName = "englishName",
        sanskritNameAdapted = "sanskritNameAdapted",
        sanskritName = "sanskritName",
        translationName = "translationName",
        poseDescription = "poseDescription",
        poseBenefits = "poseBenefits",
        urlSvg = "urlSvg",
    )
    private val fakeAsana = Asana(
        id = 123,
        englishName = "englishName",
        sanskritNameAdapted = "sanskritNameAdapted",
        sanskritName = "sanskritName",
        translationName = "translationName",
        poseDescription = "poseDescription",
        poseBenefits = "poseBenefits",
        urlSvg = "urlSvg",
    )

    @Before
    fun setUp() {
        repository = YogaRepositoryImpl(
            yogaService,
            asanaMapper,
        )
    }

    @Test
    fun `GIVEN an empty list of DTOs WHEN fetchPoses THEN return an empty list`() = runTest {
        coEvery { yogaService.fetchPoses() } returns emptyList()

        val actual = repository.fetchPoses()

        assertEquals(emptyList<Asana>(), actual)
    }

    @Test
    fun `GIVEN an list of DTOs WHEN fetchPoses THEN return a list of Asanas`() = runTest {
        coEvery { yogaService.fetchPoses() } returns listOf(fakeAsanaDto)
        every { asanaMapper.map(asanaDto = fakeAsanaDto) } returns fakeAsana

        val actual = repository.fetchPoses()

        assertEquals(listOf(fakeAsana), actual)
    }

    @Test
    fun `GIVEN a DTO with all the fields WHEN fetchPose(id) THEN return mapped Asana`() = runTest {
        val poseId = 123
        coEvery { yogaService.fetchPose(poseId) } returns fakeAsanaDto
        every { asanaMapper.map(fakeAsanaDto) } returns fakeAsana

        val actual = repository.fetchPose(poseId)

        assertEquals(fakeAsana, actual)
    }
}
