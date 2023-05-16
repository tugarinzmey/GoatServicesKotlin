import Client from "../models/Client.js";

class ClientService {
    async create(client) {
        const createdClient = await Client.create(client);
        return createdClient;
    }

    async getAll() {
        const clients = await Client.find();
        return clients
    }

    async getOne(id) {
        if (!id) {
            throw new Error("There is no id provided")
        }
        const client = await Client.findById(id);
        return client;
    }

    async update(client) {
        if (!client._id) {
            throw new Error("There is no id provided")
        }
        const updated = await Client.findByIdAndUpdate(client._id, client, { new: true });
        return updated;
    }

    async delete(id) {
        if (!id) {
            throw new Error("There is no id provided")
        }
        const deleted = await Client.findByIdAndDelete(id);
        return deleted;
    }
}

export default new ClientService();