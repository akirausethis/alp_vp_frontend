import { z, ZodType } from "zod"

export class QuestionValidation {
  static readonly CREATE: ZodType = z.object({
    question : z.string().min(1).max(100),
  })

  static readonly UPDATE: ZodType = z.object({
    question : z.string().min(1).max(100),
  })

}