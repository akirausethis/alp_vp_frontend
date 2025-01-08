import { prismaClient } from "../application/database";
import { FeedbackCreateRequest, FeedbackResponse, toFeedbackResponse, toFeedbackResponseList } from "../model/feedbackmodel";
import { ResponseError } from "../error/responseerror";

export class FeedbackService {
  // Mengambil semua feedback
  static async getAllFeedback(): Promise< FeedbackResponse[]> {
    try {
      const feedbacks = await prismaClient.feedback.findMany();

      return toFeedbackResponseList(feedbacks);
    } catch (error) {
      throw new ResponseError(500, "Internal Server Error");
    }
  }

  // Mengambil feedback berdasarkan ID
  static async getFeedbackById(feedback_id: number): Promise<FeedbackResponse> {
    try {
      const feedback = await prismaClient.feedback.findUnique({
        where: {
          id: feedback_id,
        },
      });

      if (!feedback) {
        throw new ResponseError(404, "Feedback not found");
      }

      return toFeedbackResponse(feedback);
    } catch (error) {
      throw new ResponseError(500, "Internal Server Error");
    }
  }

  // Membuat feedback baru
  static async createFeedback(req: FeedbackCreateRequest): Promise<string> {
    try {
      const { feedback, user_id, question_id } = req;

      const newFeedback = await prismaClient.feedback.create({
        data: {
          feedback,
          user_id, // Relasi ke user
          question_id, // Relasi ke question
        },
      });

      return `Feedback created successfully with ID: ${newFeedback.id}`;
    } catch (error) {
      throw new ResponseError(500, "Internal Server Error");
    }
  }
}
