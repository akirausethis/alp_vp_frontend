import { PrismaClient } from "@prisma/client"
import { PanitiaCreateRequest, PanitiaUpdateRequest, toPanitiaResponse, toPanitiaResponseList } from '../model/panitiamodel'

const prisma = new PrismaClient()

export class PanitiaService {
  // Mengambil Panitia Berdasarkan ID
  static async getPanitia(id: number) {
    const panitia = await prisma.panitia.findUnique({
      where: {
        id: id
      }
    })
    return panitia ? toPanitiaResponse(panitia) : null  // Jika tidak ditemukan, kembalikan null
  }

  // Menambah Panitia
  static async addPanitia(createRequest: PanitiaCreateRequest) {
    const newPanitia = await prisma.panitia.create({
      data: {
        organisasi: createRequest.organisasi,
        title: createRequest.title,
        description: createRequest.description,
        start_date: createRequest.start_date,
        poster: createRequest.poster,
        user_id: createRequest.user_id,
      }
    })
    return toPanitiaResponse(newPanitia)
  }

  // Mengambil Semua Panitia
  static async getAllPanitia() {
    const allPanitia = await prisma.panitia.findMany()
    return toPanitiaResponseList(allPanitia)
  }

  // Mengupdate Panitia
  static async updatePanitia(updateRequest: PanitiaUpdateRequest) {
    const updatedPanitia = await prisma.panitia.update({
      where: {
        id: updateRequest.id
      },
      data: {
        organisasi: updateRequest.organisasi,
        title: updateRequest.title,
        description: updateRequest.description,
        start_date: updateRequest.start_date,
        poster: updateRequest.poster,
        user_id: updateRequest.user_id,
      }
    })
    return toPanitiaResponse(updatedPanitia)
  }

  // Menghapus Panitia
  static async deletePanitia(id: number) {
    const deletedPanitia = await prisma.panitia.delete({
      where: {
        id: id
      }
    })
    return deletedPanitia
  }
}
