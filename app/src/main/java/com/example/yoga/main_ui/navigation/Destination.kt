package com.example.yoga.main_ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Asanas

@Serializable
data class Detail(val poseId: Int, val poseName: String)
