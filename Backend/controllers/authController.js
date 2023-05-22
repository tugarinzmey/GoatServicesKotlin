import User from "../models/User.js";
import bcrypt from "bcryptjs";
import { validationResult } from "express-validator";
import jwt from "jsonwebtoken"
import config from "../config.js"

const generateAccessToken = (id, username) => {
    const payload = {
        id,
        username
    }
    return jwt.sign(payload, config.secret, { expiresIn: "24h" })
}

export default class AuthController {
    async registration(req, res) {
        try {
            const validationErrors = validationResult(req);
            if (!validationErrors.isEmpty()) {
                return res.status(400).json({ message: "Registration failed", validationErrors })
            };
            const { username, password } = req.body;
            const candidate = await User.findOne({ username });
            if (candidate) {
                return res.status(400).json({ message: "User with this username is already exists" })
            };
            const hashedPassword = bcrypt.hashSync(password, 7);
            const user = new User({
                username,
                password: hashedPassword
            });
            await user.save();
            return res.status(200).json({ message: "Success!" });
        }
        catch (e) {
            console.log(e);
            res.status(400).json({ message: "Registration error" });
        }
    }

    async login(req, res) {
        try {
            const { username, password } = req.body;
            const user = await User.findOne({ username });
            if (!user) {
                return res.status(400).json({ message: `User ${username} is not found` })
            };
            const passwordIsValid = bcrypt.compareSync(password, user.password);
            if (!passwordIsValid) {
                return res.status(400).json({ message: "Wrong password!" })
            }
            const token = generateAccessToken(user._id, user.username);
            return res.status(200).json({ token });
        }
        catch (e) {
            console.log(e);
            res.status(400).json({ message: "Login error" });
        }
    }

    async getUsers(req, res) {
        try {
            const users = await User.find()
            res.json(users)
        }
        catch {

        }
    }
}