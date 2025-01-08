import { z, ZodType } from "zod"

export class PanitiaValidation {
  static readonly CREATE: ZodType = z.object({
    organisasi: z.string().min(1).max(100),
    title: z.string().min(1).max(100),
    description: z.string().min(1).max(300),
    start_date: z.string().min(1).max(100),
    poster: z.string().max(100).optional(),  // Poster bisa kosong
  })

  static readonly UPDATE: ZodType = z.object({
    id: z.number().positive(),  // ID harus positif
    organisasi: z.string().min(1).max(100),
    title: z.string().min(1).max(100),
    description: z.string().min(1).max(300),
    start_date: z.string().min(1).max(100),
    poster: z.string().max(100).optional(),  // Poster bisa kosong
  })
}
