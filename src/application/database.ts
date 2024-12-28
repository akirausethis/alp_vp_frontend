import { PrismaClient } from "@prisma/client"
import { logger } from "./logging"

export const prismaClient = new PrismaClient({
    log: [
        {
            emit: "event",
            level: "query"
        },
        {
            emit: "event",
            level: "warn"
        },
        {
            emit: "event",
            level: "info"
        },
        {
            emit: "event",
            level: "error"
        }
    ]
})
