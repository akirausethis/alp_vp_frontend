package com.example.alp_vp.models

data class RegisterUserRequest(
    val username: String,
    val email: String,
    val password: String
)

data class UserResponse(
    val token: String?,
    val username: String
)

data class LoginUserRequest(
    val email: String,
    val password: String
)

fun toUserResponse(user: User): UserResponse {
    return UserResponse(
        token = user.token ?: "",
        username = user.username
    )
}

data class User(
    val token: String?,
    val username: String
)
