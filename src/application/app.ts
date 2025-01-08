import express from "express"
import { publicRouter } from "../route/public"
import { errorMiddleware } from "../middleware/errormiddleware"
import { apiRouter } from "../route/api"

const app = express()
app.use(express.json())
app.use(publicRouter)
app.use(apiRouter)
app.use(errorMiddleware)

export default app
