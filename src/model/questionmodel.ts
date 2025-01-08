import  { Question } from "@prisma/client"

export interface QuestionResponse {
    id: number
    question: string
}

// Fungsi untuk mengonversi Survey menjadi SurveyResponse
export function toQuestionResponseList(prismaQuestion: Question[]): QuestionResponse[] {
    const result = prismaQuestion.map((question) => {
        return {
            id: question.id,
            question: question.question,
        }
    })

    return result
}

export function toQuestionResponse(prismaQuestion: Question): QuestionResponse {
    return {
    id: prismaQuestion.id,
    question: prismaQuestion.question,
    }
}

export interface QuestionCreateRequest {
   question: string
   survey_id: number
}

export interface QuestionUpdateRequest {
   question: string
   survey_id: number
}