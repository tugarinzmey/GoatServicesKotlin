import mongoose from "mongoose";

const Client = new mongoose.Schema({
    firstName: {type: String, required: true},
    lastName: {type: String, required: true},
    email: {type: String, required: true},
    phone: {type: String, required: true},
    status: {type: String, required: true}
})

export default mongoose.model("Client", Client)