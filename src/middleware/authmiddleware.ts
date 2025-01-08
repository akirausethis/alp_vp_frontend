// middlewares/authMiddleware.ts
import { Response, NextFunction } from "express";
import { prismaClient } from "../application/database";
import { UserRequest } from "../type/usertype";

export const authMiddleware = async (
    req: UserRequest,
    res: Response,
    next: NextFunction
) => {
    try {
        // Ambil token dari header request
        const token = req.get("X-API-TOKEN");

        // Cek apakah token tersedia
        if (token) {
            // Cari user berdasarkan token
            const user = await prismaClient.user.findFirst({
                where: {
                    token: token,
                },
            });

            // Jika user ditemukan, lanjutkan ke middleware berikutnya
            if (user) {
                req.user = user; // Menambahkan user ke dalam request
                return next(); // Lanjutkan ke middleware/controller selanjutnya
            }
        }

        // Jika tidak ada token atau user tidak ditemukan
        res.status(401).json({
            errors: "Unauthorized",
        }).end();
    } catch (error) {
        // Menangani error pada saat pengecekan token atau akses database
        console.error(error);
        res.status(500).json({
            errors: "Internal Server Error",
        }).end();
    }
};
