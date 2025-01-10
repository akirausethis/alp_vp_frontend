package com.example.alp_vp.models

// Request model for user registration
data class RegisterUserRequest(
    val username: String,
    val email: String,
    val password: String
)

// Response model for user data
data class UserResponse(
    val token: String?,
    val username: String
)

// Request model for user login
data class LoginUserRequest(
    val email: String,
    val password: String
)

// Mapping function to convert a backend User object into a UserResponse
fun toUserResponse(user: User): UserResponse {
    return UserResponse(
        token = user.token ?: "",
        username = user.username
    )
}

// Assuming a User class exists (mock representation of backend model)
data class User(
    val token: String?,
    val username: String
)
