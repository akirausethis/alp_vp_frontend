import { feedback } from "@prisma/client";

// Interface untuk response feedback
export interface FeedbackResponse {
  id: number;
  feedback: string;
  user_id: number;
  question_id: number;
}

// Fungsi untuk mengonversi list feedback menjadi response
export function toFeedbackResponseList(prismaFeedback: feedback[]): FeedbackResponse[] {
  return prismaFeedback.map((feedback) => {
    return {
      id: feedback.id,
      feedback: feedback.feedback,
      user_id: feedback.user_id,
      question_id: feedback.question_id,
    };
  });
}

// Fungsi untuk mengonversi satu feedback menjadi response
export function toFeedbackResponse(prismaFeedback: feedback): FeedbackResponse {
  return {
    id: prismaFeedback.id,
    feedback: prismaFeedback.feedback,
    user_id: prismaFeedback.user_id,
    question_id: prismaFeedback.question_id,
  };
}

// Interface untuk request membuat feedback baru
export interface FeedbackCreateRequest {
  feedback: string;
  user_id: number;  // ID dari user yang memberikan feedback
  question_id: number; // ID dari pertanyaan terkait feedback
}
