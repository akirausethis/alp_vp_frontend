import { Survey } from "@prisma/client";
import {
    toSurveyResponseList,
    toSurveyResponse,
    SurveyCreateRequest,
    SurveyResponse,
} from "../model/surveymodel";
import { prismaClient } from "../application/database";
import { Validation } from "../validation/validation";
import { SurveyValidation } from "../validation/surveyvalidation";
import { ResponseError } from "../error/responseerror";
import { logger } from "../application/logging";

export class SurveyService {
    // Mengambil semua survey
    static async getAllSurvey(): Promise<SurveyResponse[]> {
        try {
            const surveys = await prismaClient.survey.findMany();

            return toSurveyResponseList(surveys);
        } catch (error) {
            logger.error("Error fetching surveys", error);
            throw new ResponseError(500, "Internal Server Error");
        }
    }

    // Mengambil survey berdasarkan survey_id
    static async getSurvey(survey_id: number): Promise<SurveyResponse> {
        try {
            const survey = await this.checkSurveyIsEmpty(survey_id);
            return toSurveyResponse(survey);
        } catch (error) {
            logger.error(`Error fetching survey with id ${survey_id}`, error);
            throw new ResponseError(500, "Internal Server Error");
        }
    }

    // Membuat survey baru
    static async createSurvey(
        req: SurveyCreateRequest
    ): Promise<string> {
        try {
            // Validasi request survey
            const surveyRequest = Validation.validate(SurveyValidation.CREATE, req);

            const survey = await prismaClient.survey.create({
                data: {
                    title: surveyRequest.title,
                    start_date: surveyRequest.start_date,
                    end_date: surveyRequest.end_date,
                },
            });

            logger.info(`Survey created with id ${survey.id}`);
            return "Survey created successfully!";
        } catch (error) {
            logger.error("Error creating survey", error);
            throw new ResponseError(500, "Internal Server Error");
        }
    }

    // Mengecek apakah survey ada berdasarkan survey_id
    static async checkSurveyIsEmpty(
        survey_id: number
    ): Promise<Survey> {
        try {
            const survey = await prismaClient.survey.findUnique({
                where: {
                    id: survey_id,
                },
            });

            if (!survey) {
                throw new ResponseError(404, "Survey not found");
            }

            return survey;
        } catch (error) {
            logger.error(`Error checking survey with id ${survey_id}`, error);
            throw new ResponseError(500, "Internal Server Error");
        }
    }
}
