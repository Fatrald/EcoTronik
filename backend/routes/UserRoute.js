import express from "express";
import {
  getUsers,
  getUserById,
  createUser,
  updateUser,
  deleteUser,
} from "../controllers/Users.js";
import { verifyEmail, verifyUser, adminOnly } from "../middleware/AuthUser.js";

const router = express.Router();

router.get("/users", getUsers);
router.get("/users/:id", getUserById);
router.post("/users", verifyEmail, createUser);
router.patch("/users/:id", verifyEmail, updateUser);
router.delete("/users/:id", adminOnly, deleteUser);

export default router;
