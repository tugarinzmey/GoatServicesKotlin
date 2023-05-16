import ClientService from "../services/ClientService.js";
import Client from "../models/Client.js"

export default class ClientController {
    async create(req, res) {
        try {
            const client = await ClientService.create(req.body);
            res.status(200).json(client);
        } catch (e) {
            res.status(500).json(e)
        }
    }

    async getAll(req, res) {
        try {
            const clients = await ClientService.getAll();
            return res.status(200).json(clients);
        }
        catch (e) {
            res.status(500).json(e)
        }
    }

    async getOne(req, res) {
        try {
            const client = await ClientService.getOne(req.params.id);
            return res.status(200).json(client)
        }
        catch (e) {
            res.status(500).json(e)
        }
    }

    async update(req, res) {
        try {
            const updated = await ClientService.update(req.body);
            return res.json(updated);
        }
        catch (error) {
            res.status(500).json(error.message)
        }
    }

    async delete(req, res) {
        try {
            const deleted = await ClientService.delete(req.params.id)
            return res.json(deleted);
        }
        catch (error) {
            res.status(500).json(error.message)
        }
    }
}