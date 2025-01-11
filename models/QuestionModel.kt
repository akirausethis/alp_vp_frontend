package com.example.alp_vp.models

data class GetAllQuestionResponse(
    val data: List<QuestionModel>
)

data class GetQuestionResponse(
    val data: QuestionModel
)

data class QuestionModel(
    val id: Int,
    val question: String,
    val surveyId: Int
)

fun toQuestionResponseList(questions: List<QuestionModel>): List<QuestionResponse> {
    return questions.map { question ->
        QuestionResponse(
            id = question.id,
            question = question.question,
            surveyId = question.surveyId
        )
    }
}

fun toQuestionResponse(question: QuestionModel): QuestionResponse {
    return QuestionResponse(
        id = question.id,
        question = question.question,
        surveyId = question.surveyId
    )
}

data class QuestionCreateRequest(
    val question: String,
    val surveyId: Int
)


data class QuestionUpdateRequest(
    val question: String,
    val surveyId: Int
)

data class QuestionResponse(
    val id: Int,
    val question: String,
    val surveyId: Int
)


data class Question(
    val id: Int,
    val question: String,
    val surveyId: Int
)
