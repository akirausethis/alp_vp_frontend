import { NextFunction, Response } from "express"
import { PanitiaService } from "../service/panitiaservice"
import { UserRequest } from "../type/usertype"
import { PanitiaCreateRequest, PanitiaUpdateRequest } from "../model/panitiamodel"

export class PanitiaController {
  // Menampilkan semua panitia
  static async getAllPanitia(
    req: UserRequest,
    res: Response,
    next: NextFunction
  ) {
    try {
      const response = await PanitiaService.getAllPanitia()

      res.status(200).json({
        data: response,
      })
    } catch (error) {
      next(error)
    }
  }

  // Menampilkan satu panitia berdasarkan ID
  static async getPanitia(
    req: UserRequest,
    res: Response,
    next: NextFunction
  ) {
    try {
      const response = await PanitiaService.getPanitia(Number(req.params.panitiaId))

      res.status(200).json({
        data: response,
      })
    } catch (error) {
      next(error)
    }
  }

  // Membuat panitia baru
  static async createPanitia(
    req: UserRequest,
    res: Response,
    next: NextFunction
  ) {
    try {
      const request = req.body as PanitiaCreateRequest
      const response = await PanitiaService.addPanitia(request)

      res.status(201).json({
        data: response,
      })
    } catch (error) {
      next(error)
    }
  }

  // Mengupdate data panitia
  static async updatePanitia(
    req: UserRequest,
    res: Response,
    next: NextFunction
  ) {
    try {
      const request = req.body as PanitiaUpdateRequest
      request.id = Number(req.params.panitiaId)
      const response = await PanitiaService.updatePanitia(request)

      res.status(200).json({
        data: response,
      })
    } catch (error) {
      next(error)
    }
  }

  // Menghapus panitia
  static async deletePanitia(
    req: UserRequest,
    res: Response,
    next: NextFunction
  ) {
    try {
      const response = await PanitiaService.deletePanitia(Number(req.params.panitiaId))

      res.status(200).json({
        data: response,
      })
    } catch (error) {
      next(error)
    }
  }
}
