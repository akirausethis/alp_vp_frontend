import { Response, NextFunction } from "express";
import { FeedbackService } from "../service/feedbackservice";
import { UserRequest } from "../type/usertype";
import { FeedbackCreateRequest } from "../model/feedbackmodel";

export class FeedbackController {
  // Mengambil semua feedback
  static async getAllFeedback(req: UserRequest, res: Response, next: NextFunction) {
    try {
      const response = await FeedbackService.getAllFeedback();

      res.status(200).json({
        data: response,
      });
    } catch (error) {
      next(error);
    }
  }

  // Mengambil feedback berdasarkan ID
  static async getFeedback(req: UserRequest, res: Response, next: NextFunction) {
    try {
      const feedback_id = Number(req.params.feedbackId);
      const response = await FeedbackService.getFeedbackById(feedback_id);

      res.status(200).json({
        data: response,
      });
    } catch (error) {
      next(error);
    }
  }

  // Membuat feedback baru
  static async createFeedback(req: UserRequest, res: Response, next: NextFunction) {
    try {
      const request = req.body as FeedbackCreateRequest;
      const response = await FeedbackService.createFeedback(request);

      res.status(201).json({
        message: response,
      });
    } catch (error) {
      next(error);
    }
  }
}
