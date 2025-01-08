import { NextFunction, Response } from "express";
import { SurveyService } from "../service/surveyservice";
import { UserRequest } from "../type/usertype";
import { SurveyCreateRequest } from "../model/surveymodel";

export class SurveyController {
  // Menampilkan semua survey
  static async getAllSurvey(
    req: UserRequest, 
    res: Response, 
    next: NextFunction
  ) {
    try {
      const response = await SurveyService.getAllSurvey(); // Tidak perlu user, hanya mengambil semua survey

      res.status(200).json({
        data: response,
      });
    } catch (error) {
      next(error);
    }
  }

  // Menampilkan satu survey berdasarkan ID
  static async getSurvey(req: UserRequest, res: Response, next: NextFunction) {
    try {
      const surveyId = Number(req.params.surveyId); // Mendapatkan surveyId dari parameter
      const response = await SurveyService.getSurvey(surveyId); // Tidak perlu user, hanya mengambil survey berdasarkan ID

      res.status(200).json({
        data: response,
      });
    } catch (error) {
      next(error);
    }
  }

  // Membuat survey baru
  static async createSurvey(
    req: UserRequest,
    res: Response, 
    next: NextFunction
  ) {
    try {
      const request = req.body as SurveyCreateRequest; // Mengambil data body request untuk membuat survey
      const response = await SurveyService.createSurvey(request); // Tidak perlu user, hanya membuat survey

      res.status(201).json({
        message: "Survey created successfully!", // Mengirimkan pesan sukses
        data: response, // Optional, jika ingin mengirim data survey baru
      });
    } catch (error) {
      next(error);
    }
  }
}
