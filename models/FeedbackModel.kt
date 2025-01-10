package com.example.alp_vp.models

// Model untuk response feedback
data class FeedbackResponse(
    val id: Int,
    val feedback: String,
    val userId: Int,
    val questionId: Int
)

// Fungsi untuk mengonversi daftar feedback menjadi response
fun toFeedbackResponseList(feedbackList: List<Feedback>): List<FeedbackResponse> {
    return feedbackList.map { feedback ->
        FeedbackResponse(
            id = feedback.id,
            feedback = feedback.feedback,
            userId = feedback.userId,
            questionId = feedback.questionId
        )
    }
}

// Fungsi untuk mengonversi satu feedback menjadi response
fun toFeedbackResponse(feedback: Feedback): FeedbackResponse {
    return FeedbackResponse(
        id = feedback.id,
        feedback = feedback.feedback,
        userId = feedback.userId,
        questionId = feedback.questionId
    )
}

// Model untuk request membuat feedback baru
data class FeedbackCreateRequest(
    val feedback: String,
    val userId: Int,  // ID dari user yang memberikan feedback
    val questionId: Int // ID dari pertanyaan terkait feedback
)

// Mock model untuk representasi data Feedback
data class Feedback(
    val id: Int,
    val feedback: String,
    val userId: Int,
    val questionId: Int
)
