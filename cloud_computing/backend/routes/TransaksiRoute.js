import express from "express";
import {
  getTransaksi,
  getTransaksiByUserId,
  getTransaksiByStatus,
  createTransaksi,
  updateTransaksi,
} from "../controllers/Transaksi.js";
import { verifyUser, adminOnly } from "../middleware/AuthUser.js";

const router = express.Router();

router.get("/transaksi", verifyUser, getTransaksi);
router.get("/transaksi/:uuid", verifyUser, getTransaksiByUserId);
router.get("/transaksi/:status", verifyUser, adminOnly, getTransaksiByStatus);
router.post("/transaksi", verifyUser, createTransaksi);
router.patch("/transaksi", verifyUser, adminOnly, updateTransaksi);

export default router;
