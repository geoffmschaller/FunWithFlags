package com.example.funwithflags.helpers

import com.example.funwithflags.data.Countries

data class Question(
		val id: Int,
		val image: Int,
		val correctAnswer: Countries
)