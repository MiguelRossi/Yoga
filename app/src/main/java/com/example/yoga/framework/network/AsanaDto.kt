package com.example.yoga.framework.network

import com.squareup.moshi.Json

data class AsanaDto(

    val id: Int,

    @param:Json(name = "english_name")
    val englishName: String?,
    @param:Json(name = "sanskrit_name_adapted")
    val sanskritNameAdapted: String?,
    @param:Json(name = "sanskrit_name")
    val sanskritName: String?,
    @param:Json(name = "translation_name")
    val translationName: String?,

    @param:Json(name = "pose_description")
    val poseDescription: String?,
    @param:Json(name = "pose_benefits")
    val poseBenefits: String?,

    @param:Json(name = "url_svg")
    val urlSvg: String?,
)
