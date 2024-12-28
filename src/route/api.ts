import express from "express"
import { authMiddleware } from "../middleware/authmiddleware"
import { UserController } from "../controller/usercontroller"
import { PanitiaController } from "../controller/panitiacontroller"

export const apiRouter = express.Router()
apiRouter.use(authMiddleware)

//user router
apiRouter.post("/api/logout", UserController.logout)

//panitia router
apiRouter.get("/api/panitia", PanitiaController.getAllPanitia)
apiRouter.get("/api/panitia/:panitiaId(\\d+)", PanitiaController.getPanitia)
apiRouter.post("/api/panitia", PanitiaController.createPanitia)
apiRouter.put("/api/panitia/:panitiaId(\\d+)", PanitiaController.updatePanitia)
apiRouter.delete("/api/panitia/:panitiaId(\\d+)", PanitiaController.deletePanitia)