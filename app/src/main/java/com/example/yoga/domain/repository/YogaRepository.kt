package com.example.yoga.domain.repository

import com.example.yoga.domain.model.Asana

interface YogaRepository {
    suspend fun fetchPoses(): List<Asana>
    suspend fun fetchPose(poseId: Int): Asana
}
