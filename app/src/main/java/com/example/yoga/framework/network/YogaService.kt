package com.example.yoga.framework.network

import retrofit2.http.GET
import retrofit2.http.Query

interface YogaService {

    @GET("poses")
    suspend fun fetchPoses(): List<AsanaDto>

    @GET("poses")
    suspend fun fetchPose(@Query("id") id: Int): AsanaDto
}
