package com.example.yoga.data

import com.example.yoga.domain.model.Asana
import com.example.yoga.domain.repository.YogaRepository
import com.example.yoga.framework.network.YogaService
import javax.inject.Inject

class YogaRepositoryImpl @Inject constructor(
    private val yogaService: YogaService,
    private val asanaMapper: AsanaMapper
) : YogaRepository {

    override suspend fun fetchPoses(): List<Asana> =
        yogaService.fetchPoses().map { asanaDto -> asanaMapper.map(asanaDto) }

    override suspend fun fetchPose(poseId: Int) =
        asanaMapper.map(asanaDto = yogaService.fetchPose(poseId))
}
