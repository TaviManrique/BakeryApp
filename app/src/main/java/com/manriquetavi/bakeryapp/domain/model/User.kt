package com.manriquetavi.bakeryapp.domain.model

data class User(
    var uid: String = "",
    var username: String = "",
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val addresses: List<String> = listOf()
)
