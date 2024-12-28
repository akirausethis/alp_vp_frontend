import { NextFunction, Request, Response } from "express"
import { ZodError } from "zod"
import { ResponseError } from "../error/responseerror"

// Every error respond from this API will go through this error middleware
// This middleware will check whether there is an error or not, if an error exists, then return error message
export const errorMiddleware = async (
    error: Error,
    req: Request,
    res: Response,
    next: NextFunction
) => {
    if (error instanceof ZodError) {
        res.status(400).json({
            errors: `Validation error: ${JSON.stringify(error.message)}`,
        })
    } else if (error instanceof ResponseError) {
        res.status(error.status).json({
            errors: error.message,
        })
    } else {
        res.status(500).json({
            errors: error.message,
        })
    }
}
