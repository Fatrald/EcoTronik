import express from "express";
import {
  getTransaksi,
  getTransaksiByUserId,
  getTransaksiByStatus,
  createTransaksi,
  updateTransaksi,
} from "../controllers/Transaksi.js";

const router = express.Router();

router.get("/transaksi", getTransaksi);
router.get("/transaksi/:uuid", getTransaksiByUserId);
router.get("/transaksi/:uuid/:status", getTransaksiByStatus);
router.post("/transaksi", createTransaksi);
router.patch("/transaksi", updateTransaksi);

export default router;
