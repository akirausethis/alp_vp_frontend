import { z, ZodType } from "zod"

export class SurveyValidation {
  static readonly CREATE: ZodType = z.object({
    title: z.string().min(1).max(100),
    start_date: z.string().min(1).max(100),
    end_date: z.string().min(1).max(100),
  })
}