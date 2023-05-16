import { Router } from "express";
import ClientController from "../controllers/clientController.js";

const clientRouter = new Router();
const clientController = new ClientController();


clientRouter.post('', (req, res) => clientController.create(req, res));
clientRouter.get('', (req, res) => clientController.getAll(req, res));
clientRouter.get('/:id', (req, res) => clientController.getOne(req, res));
clientRouter.put('', (req, res) => clientController.update(req, res));
clientRouter.delete('/:id', (req, res) => clientController.delete(req, res));

export default clientRouter;