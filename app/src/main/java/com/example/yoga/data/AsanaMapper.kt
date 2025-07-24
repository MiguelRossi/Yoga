package com.example.yoga.data

import com.example.yoga.domain.model.Asana
import com.example.yoga.framework.network.AsanaDto
import javax.inject.Inject

class AsanaMapper @Inject constructor() {

    fun map(asanaDto: AsanaDto) = Asana(
        id = asanaDto.id,
        englishName = asanaDto.englishName.orEmpty(),
        sanskritNameAdapted = asanaDto.sanskritNameAdapted.orEmpty(),
        sanskritName = asanaDto.sanskritName.orEmpty(),
        translationName = asanaDto.translationName.orEmpty(),
        poseDescription = asanaDto.poseDescription.orEmpty(),
        poseBenefits = asanaDto.poseBenefits.orEmpty(),
        urlSvg = asanaDto.urlSvg.orEmpty(),
    )
}
