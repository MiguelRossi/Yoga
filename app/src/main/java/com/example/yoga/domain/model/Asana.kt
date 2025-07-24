package com.example.yoga.domain.model

data class Asana(

    val id: Int,

    val englishName: String,
    val sanskritNameAdapted: String,
    val sanskritName: String,
    val translationName: String,

    val poseDescription: String,
    val poseBenefits: String,

    val urlSvg: String,
)
