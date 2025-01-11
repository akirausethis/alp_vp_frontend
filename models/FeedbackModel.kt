package com.example.alp_vp.models

data class GetAllFeedbackResponse(
    val data: List<FeedbackModel>
)

data class GetFeedbackResponse(
    val data: FeedbackModel
)

data class FeedbackModel(
    val id: Long,
    val feedback: String,
    val userId: Long,
    val questionId: Long
)


data class FeedbackResponse(
    val id: Long,
    val feedback: String,
    val userId: Long,
    val questionId: Long
)

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

fun toFeedbackResponse(feedback: FeedbackModel): FeedbackResponse {
    return FeedbackResponse(
        id = feedback.id,
        feedback = feedback.feedback,
        userId = feedback.userId,
        questionId = feedback.questionId
    )
}


data class FeedbackCreateRequest(
    val feedback: String,
    val userId: Long,
    val questionId: Long
)


data class Feedback(
    val id: Long,
    val feedback: String,
    val userId: Long,
    val questionId: Long
)
