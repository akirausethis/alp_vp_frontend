import  {Survey } from "@prisma/client"

export interface SurveyResponse {
    id: number
    title: string
    start_date: string
    end_date: string
}

// Fungsi untuk mengonversi Survey menjadi SurveyResponse
export function toSurveyResponseList(prismaSurvey: Survey[]): SurveyResponse[] {
    const result = prismaSurvey.map((survey) => {
        return {
            id: survey.id,
            title: survey.title,
            start_date: survey.start_date,
            end_date: survey.end_date,
        }
    })

    return result
}

export function toSurveyResponse(prismaSurvey: Survey): SurveyResponse {
    return {
        id: prismaSurvey.id,
        title: prismaSurvey.title,
        start_date: prismaSurvey.start_date,
        end_date: prismaSurvey.end_date,
    }
}

export interface SurveyCreateRequest {
    title: string
    start_date: string
    end_date: string
}