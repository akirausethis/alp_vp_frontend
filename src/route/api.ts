import express from "express"
import { authMiddleware } from "../middleware/authmiddleware"
import { UserController } from "../controller/usercontroller"
import { PanitiaController } from "../controller/panitiacontroller"
import { SurveyController } from "../controller/surveycontroller"
import { QuestionController } from "../controller/questioncontroller"
import { FeedbackController } from "../controller/feedbackcontroller"

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

//survey router
apiRouter.get("/api/survey", SurveyController.getAllSurvey)
apiRouter.get("/api/survey/:surveyId(\\d+)", SurveyController.getSurvey)
apiRouter.post("/api/survey", SurveyController.createSurvey)

//question router
apiRouter.get("/api/question", QuestionController.getAllQuestions)
apiRouter.get("/api/question/:questionId(\\d+)", QuestionController.getQuestion)
apiRouter.post("/api/question", QuestionController.createQuestion)
apiRouter.put("/api/question/:questionId(\\d+)", QuestionController.updateQuestion)

//feedback router
apiRouter.get("/api/feedback", FeedbackController.getAllFeedback)
apiRouter.get("/api/feedback/:feedbackId(\\d+)", FeedbackController.getFeedback)    
apiRouter.post("/api/feedback", FeedbackController.createFeedback)