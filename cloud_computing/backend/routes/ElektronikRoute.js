import express from "express";
import {
  getElektronik,
  getElektronikById,
  createElektronik,
  deleteElektronik,
} from "../controllers/Elektronik.js";
import { verifyUser, adminOnly } from "../middleware/AuthUser.js";
import { verifyWaste } from "../middleware/WasteVerify.js";

const router = express.Router();

router.get("/elektronik", getElektronik);
router.get("/elektronik/:id", getElektronikById);
router.post("/elektronik", createElektronik);
router.delete("/elektronik/:id", deleteElektronik);

export default router;
