import { Router } from "express";
import ClientController from "../controllers/clientController.js";
import authMiddleware from "../middleware/authMiddleware.js";

const clientRouter = new Router();
const clientController = new ClientController();


clientRouter.post('', authMiddleware, clientController.create);
clientRouter.get('', authMiddleware, clientController.getAll);
clientRouter.get('/:id', authMiddleware, clientController.getOne);
clientRouter.put('', authMiddleware, clientController.update);
clientRouter.delete('/:id', authMiddleware, clientController.delete);

export default clientRouter