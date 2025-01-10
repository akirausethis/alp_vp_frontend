package com.example.alp_vp.models

// Model untuk respon pertanyaan
data class QuestionResponse(
    val id: Int,
    val question: String
)

// Fungsi untuk mengonversi daftar Question menjadi daftar QuestionResponse
fun toQuestionResponseList(questions: List<Question>): List<QuestionResponse> {
    return questions.map { question ->
        QuestionResponse(
            id = question.id,
            question = question.question
        )
    }
}

// Fungsi untuk mengonversi Question tunggal menjadi QuestionResponse
fun toQuestionResponse(question: Question): QuestionResponse {
    return QuestionResponse(
        id = question.id,
        question = question.question
    )
}

// Model untuk membuat pertanyaan baru
data class QuestionCreateRequest(
    val question: String,
    val surveyId: Int
)

// Model untuk memperbarui pertanyaan
data class QuestionUpdateRequest(
    val question: String,
    val surveyId: Int
)

// Representasi data Question (mock model yang cocok dengan backend)
data class Question(
    val id: Int,
    val question: String,
    val surveyId: Int
)
