package com.example.alp_vp.models

// Response untuk getAllFeedback
data class GetAllFeedbackResponse(
    val data: List<FeedbackModel>
)

data class GetFeedbackResponse(
    val data: FeedbackModel
)

data class FeedbackModel(
    val id: Long,  // Changed 'Int' to 'Long' for consistency
    val feedback: String,
    val userId: Long,  // Changed 'Int' to 'Long' for consistency
    val questionId: Long // Changed 'Int' to 'Long' for consistency
)

// Model untuk respon feedback
data class FeedbackResponse(
    val id: Long, // Changed 'Int' to 'Long' for consistency
    val feedback: String,
    val userId: Long,  // Changed 'Int' to 'Long' for consistency
    val questionId: Long // Changed 'Int' to 'Long' for consistency
)

// Fungsi untuk mengonversi daftar feedback menjadi daftar FeedbackResponse
fun toFeedbackResponseList(feedbackList: List<FeedbackModel>): List<FeedbackResponse> {
    return feedbackList.map { feedback ->
        FeedbackResponse(
            id = feedback.id,
            feedback = feedback.feedback,
            userId = feedback.userId,
            questionId = feedback.questionId
        )
    }
}

// Fungsi untuk mengonversi Feedback tunggal menjadi FeedbackResponse
fun toFeedbackResponse(feedback: FeedbackModel): FeedbackResponse {
    return FeedbackResponse(
        id = feedback.id,
        feedback = feedback.feedback,
        userId = feedback.userId,
        questionId = feedback.questionId
    )
}

// Model untuk membuat feedback baru
data class FeedbackCreateRequest(
    val feedback: String,
    val userId: Long,  // Changed 'Int' to 'Long' for consistency
    val questionId: Long // Changed 'Int' to 'Long' for consistency
)

// Representasi data Feedback (mock model yang cocok dengan backend)
data class Feedback(
    val id: Long, // Changed 'Int' to 'Long' for consistency
    val feedback: String,
    val userId: Long, // Changed 'Int' to 'Long' for consistency
    val questionId: Long // Changed 'Int' to 'Long' for consistency
)
