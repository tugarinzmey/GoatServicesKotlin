import express from "express";
import mongoose from "mongoose";
import clientRouter from "./routers/clientRouter.js";
import authRouter from "./routers/authRouter.js";

const PORT = 3000;
const DB_URL = 'mongodb+srv://user:root@kotlinfullstack.dqk5ihb.mongodb.net/?retryWrites=true&w=majority'
const app = express()

app.use(express.json())
app.use("/clients", clientRouter);
app.use("/auth", authRouter)

async function startApp() {
    try {
        await mongoose.connect(DB_URL,
            { useUnifiedTopology: true, useNewUrlParser: true });
        app.listen(PORT, () => {
            console.log("started on port " + PORT)
        })
    }
    catch (e) {
        console.log(e)
    }
}

startApp()