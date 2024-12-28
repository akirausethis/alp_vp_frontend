import { Panitia } from "@prisma/client"

// Response yang akan dikirimkan
export interface PanitiaResponse {
    id: number
    organisasi: string
    title: string
    description: string
    start_date: string
    poster: string

    user_id: number
}

// Fungsi untuk mengonversi Panitia menjadi PanitiaResponse
export function toPanitiaResponseList(prismaPanitia: Panitia[]): PanitiaResponse[] {
    const result = prismaPanitia.map((panitia) => {
        return {
            id: panitia.id,
            organisasi: panitia.organisasi,
            title: panitia.title,
            description: panitia.description,
            start_date: panitia.start_date,
            poster: panitia.poster || "",  // Jika poster bisa null, beri nilai default
            user_id: panitia.user_id
        }
    })

    return result
}

export function toPanitiaResponse(prismaPanitia: Panitia): PanitiaResponse {
    return {
        id: prismaPanitia.id,
        organisasi: prismaPanitia.organisasi,
        title: prismaPanitia.title,
        description: prismaPanitia.description,
        start_date: prismaPanitia.start_date,
        poster: prismaPanitia.poster || "",  // Jika poster bisa null, beri nilai default
        user_id: prismaPanitia.user_id
    }
}

// Interface untuk request create dan update Panitia
export interface PanitiaCreateRequest {
    organisasi: string
    title: string
    description: string
    start_date: string
    poster?: string  // poster bisa null
    status: string
    user_id: number
}

export interface PanitiaUpdateRequest {
    id: number
    organisasi: string
    title: string
    description: string
    start_date: string
    poster?: string  // poster bisa null
    status: string
    user_id: number
}
