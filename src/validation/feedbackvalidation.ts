import { z, ZodType } from "zod"

export class FeedbackValidation {
  static readonly CREATE: ZodType = z.object({
   feedback: z.string().min(1).max(100),
  })
}