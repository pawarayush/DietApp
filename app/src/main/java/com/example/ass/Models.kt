package com.example.ass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val data: Data,
    val message: String
)

@Serializable
data class Data(
    @SerialName("section_1") val section1: Section1,
    @SerialName("section_2") val section2: Section2,
    @SerialName("section_3") val section3: Section3,
    @SerialName("section_4") val section4: Section4
)

@Serializable
data class Section1(
    @SerialName("plan_name") val planName: String,
    val progress: String
)

@Serializable
data class Section2(
    val accuracy: String,
    @SerialName("workout_duration") val workoutDuration: String,
    val reps: Int,
    @SerialName("calories_burned") val caloriesBurned: Int
)

@Serializable
data class Section3(
    val plan_1: Plan,
    val plan_2: Plan
)

@Serializable
data class Plan(
    @SerialName("plan_name") val planName: String,
    val difficulty: String
)

@Serializable
data class Section4(
    val category_1: Category,
    val category_2: Category
)

@Serializable
data class Category(
    @SerialName("category_name") val categoryName: String,
    @SerialName("no_of_exercises") val noOfExercises: String
)