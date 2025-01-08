import { NextFunction, Response } from "express";
import {QuestionService} from "../service/questionservice";
import { UserRequest } from "../type/usertype";
import { QuestionCreateRequest, QuestionUpdateRequest } from "../model/questionmodel";

export class QuestionController {
    // Menampilkan semua pertanyaan
    static async getAllQuestions(
        req: UserRequest,
        res: Response,
        next: NextFunction
    ) {
        try {
            const response = await QuestionService.getAllQuestions();

            res.status(200).json({
                data: response,
            });
        } catch (error) {
            next(error);
        }
    }

    // Menampilkan satu pertanyaan berdasarkan ID
    static async getQuestion(
        req: UserRequest,
        res: Response,
        next: NextFunction
    ) {
        try {
            const questionId = Number(req.params.questionId); // Mendapatkan questionId dari parameter
            const response = await QuestionService.getQuestion(questionId); // Tidak perlu user, hanya mengambil question berdasarkan ID

            res.status(200).json({
                data: response,
            });
        } catch (error) {
            next(error);
        }
    }

    static async createQuestion(
        req: UserRequest,
        res: Response,              
        next: NextFunction
    ) {
        try {
            const request = req.body as QuestionCreateRequest; // Mengambil data body request untuk membuat question
            const response = await QuestionService.createQuestion(request); // Tidak perlu user, hanya membuat question

            res.status(201).json({
                message: "Question created successfully!", // Mengirimkan pesan sukses
                data: response, // Optional, jika ingin mengirim data question baru
            });
        } catch (error) {
            next(error);
        }
    }

    // Mengupdate data question
    static async updateQuestion(
        req: UserRequest,
        res: Response,
        next: NextFunction
    ) {
        try {
            const questionId = Number(req.params.questionId); // Mendapatkan questionId dari parameter
            const request = req.body as QuestionUpdateRequest; // Mengambil data body request untuk memperbarui question
            const response = await QuestionService.updateQuestion(questionId, request); // Tidak perlu user, hanya memperbarui question

            res.status(200).json({
                message: "Question updated successfully!", // Mengirimkan pesan sukses
                data: response, // Optional, jika ingin mengirim data question baru
            });
        } catch (error) {
            next(error);
        }
    }
}   