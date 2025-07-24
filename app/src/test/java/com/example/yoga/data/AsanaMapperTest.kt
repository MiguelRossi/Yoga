package com.example.yoga.data

import com.example.yoga.domain.model.Asana
import com.example.yoga.framework.network.AsanaDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AsanaMapperTest {

    private lateinit var mapper: AsanaMapper

    @Before
    fun setUp() {
        mapper = AsanaMapper()
    }

    @Test
    fun `GIVEN AsanaDto with all data WHEN mapping THEN return Asana with all data`() {
        val asanaDto = AsanaDto(
            id = 1234,
            englishName = "englishName",
            sanskritNameAdapted = "sanskritNameAdapted",
            sanskritName = "sanskritName",
            translationName = "translationName",
            poseDescription = "poseDescription",
            poseBenefits = "poseBenefits",
            urlSvg = "urlSvg",
        )

        val actual = mapper.map(asanaDto)

        val expected = Asana(
            id = asanaDto.id,
            englishName = asanaDto.englishName!!,
            sanskritNameAdapted = asanaDto.sanskritNameAdapted!!,
            sanskritName = asanaDto.sanskritName!!,
            translationName = asanaDto.translationName!!,
            poseDescription = asanaDto.poseDescription!!,
            poseBenefits = asanaDto.poseBenefits!!,
            urlSvg = asanaDto.urlSvg!!,
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN AsanaDto with nulls WHEN mapping THEN return Asana with empty and default values`() {
        val asanaDto = AsanaDto(
            id = 123,
            englishName = null,
            sanskritNameAdapted = null,
            sanskritName = null,
            translationName = null,
            poseDescription = null,
            poseBenefits = null,
            urlSvg = null,
        )

        val actual = mapper.map(asanaDto)

        val expected = Asana(
            id = asanaDto.id,
            englishName = "",
            sanskritNameAdapted = "",
            sanskritName = "",
            translationName = "",
            poseDescription = "",
            poseBenefits = "",
            urlSvg = ""
        )
        assertEquals(expected, actual)
    }
}
