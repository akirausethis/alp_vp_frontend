import { Panitia, User } from "@prisma/client";
import {
    PanitiaCreateRequest,
    PanitiaResponse,
    PanitiaUpdateRequest,
    toPanitiaResponse,
    toPanitiaResponseList,
} from "../model/panitiamodel";
import { prismaClient } from "../application/database";
import { Validation } from "../validation/validation";
import { PanitiaValidation } from "../validation/panitiavalidation";
import { ResponseError } from "../error/responseerror";
import { logger } from "../application/logging";

export class PanitiaService {
    static async getAllPanitia(user: User): Promise<PanitiaResponse[]> {
        const panitia = await prismaClient.panitia.findMany({
            where: {
                user_id: user.id,
            },
        });

        return toPanitiaResponseList(panitia);
    }

    static async getPanitia(user: User, panitia_id: number): Promise<PanitiaResponse> {
        const panitia = await this.checkPanitiaIsEmpty(user.id, panitia_id);

        return toPanitiaResponse(panitia);
    }

    static async createPanitia(
        user: User,
        req: PanitiaCreateRequest
    ): Promise<string> {
        // validate request
        const panitiaRequest = Validation.validate(PanitiaValidation.CREATE, req);

        const panitia = await prismaClient.panitia.create({
            data: {
                organisasi: panitiaRequest.organisasi,
                title: panitiaRequest.title,
                description: panitiaRequest.description,
                start_date: panitiaRequest.start_date,
                poster: panitiaRequest.poster,
                user_id: user.id,
            },
        });

        return "Panitia created successfully!";
    }

    static async checkPanitiaIsEmpty(
        user_id: number,
        panitia_id: number
    ): Promise<Panitia> {
        const panitia = await prismaClient.panitia.findUnique({
            where: {
                id: panitia_id,
                user_id: user_id,
            },
        });

        if (!panitia) {
            throw new ResponseError(400, "Panitia not found!");
        }

        return panitia;
    }

    static async updatePanitia(
        user: User,
        req: PanitiaUpdateRequest
    ): Promise<string> {
        const panitiaValidation = Validation.validate(PanitiaValidation.UPDATE, req);

        await this.checkPanitiaIsEmpty(user.id, panitiaValidation.id);

        const panitiaUpdate = await prismaClient.panitia.update({
            where: {
                id: panitiaValidation.id,
                user_id: user.id,
            },
            data: panitiaValidation,
        });

        logger.info("UPDATE RESULT: " + panitiaUpdate);

        return "Panitia updated successfully!";
    }

    static async deletePanitia(user: User, panitia_id: number): Promise<string> {
        await this.checkPanitiaIsEmpty(user.id, panitia_id);

        await prismaClient.panitia.delete({
            where: {
                user_id: user.id,
                id: panitia_id,
            },
        });

        return "Panitia deleted successfully!";
    }
}
