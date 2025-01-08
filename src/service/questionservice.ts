import { prismaClient } from "../application/database";
import { QuestionCreateRequest, toQuestionResponse, toQuestionResponseList, QuestionResponse, QuestionUpdateRequest } from "../model/questionmodel";
import { Validation } from "../validation/validation";
import { QuestionValidation } from "../validation/questionvalidation";
import { ResponseError } from "../error/responseerror";
import { logger } from "../application/logging";
import { Question } from "@prisma/client";

export class QuestionService {
    // Mengambil semua pertanyaan
    static async getAllQuestions(): Promise<QuestionResponse[]> {
        try {
            const questions = await prismaClient.question.findMany();

            return toQuestionResponseList(questions);
        } catch (error) {
            logger.error("Error fetching questions", error);
            throw new ResponseError(500, "Internal Server Error");
        }
    }

    // Mengambil satu pertanyaan berdasarkan question_id
    static async getQuestion(question_id: number): Promise<QuestionResponse> {
        try {
            const question = await this.checkQuestionIsEmpty(question_id);
            return toQuestionResponse(question);
        } catch (error) {
            logger.error(`Error fetching question with id ${question_id}`, error);
            throw new ResponseError(500, "Internal Server Error");
        }
    }

    // Membuat pertanyaan baru
    static async createQuestion(req: QuestionCreateRequest): Promise<string> {
        try {
            // Validasi request question
            const questionRequest = Validation.validate(QuestionValidation.CREATE, req);

            const question = await prismaClient.question.create({
                data: {
                    question: questionRequest.question,
                    survey_id: questionRequest.survey_id, // Asumsikan setiap pertanyaan berhubungan dengan survey
                },
            });

            logger.info(`Question created with id ${question.id}`);
            return "Question created successfully!";
        } catch (error) {
            logger.error("Error creating question", error);
            throw new ResponseError(500, "Internal Server Error");
        }
    }

    // Mengecek apakah pertanyaan ada berdasarkan question_id
    static async checkQuestionIsEmpty(question_id: number): Promise<Question> {
        try {
            const question = await prismaClient.question.findUnique({
                where: {
                    id: question_id,
                },
            });

            if (!question) {
                throw new ResponseError(404, "Question not found");
            }

            return question;
        } catch (error) {
            logger.error(`Error checking question with id ${question_id}`, error);
            throw new ResponseError(500, "Internal Server Error");
        }
    }

    static async updateQuestion(
        question_id: number,
        req: QuestionUpdateRequest
    ): Promise<string> {
        try {
            const { question, survey_id } = req;

            // Mengecek apakah pertanyaan dengan question_id ada
            const existingQuestion = await prismaClient.question.findUnique({
                where: {
                    id: question_id,
                },
            });

            if (!existingQuestion) {
                throw new ResponseError(404, "Question not found");
            }

            // Memperbarui pertanyaan
            const updatedQuestion = await prismaClient.question.update({
                where: {
                    id: question_id,
                },
                data: {
                    question, // Update question text
                    survey_id, // Update survey_id jika diperlukan
                },
            });

            return `Question updated successfully with ID: ${updatedQuestion.id}`;
        } catch (error) {
            throw new ResponseError(500, "Error updating question");
        }
    }

    
}
