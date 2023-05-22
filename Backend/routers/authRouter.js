import { Router } from "express";
import AuthController from "../controllers/authController.js";
import { check } from "express-validator";
import authMiddleware from "../middleware/authMiddleware.js";

const authRouter = new Router();
const authController = new AuthController();

authRouter.post('/registration', [
    check("username", "Username is required!").notEmpty(),
    check("password", "Password must be between 6 and 50 characters").isLength({min: 6, max: 50})

] ,authController.registration);
authRouter.post('/login', authController.login);
authRouter.get('/users', authMiddleware, authController.getUsers);

export default authRouter;