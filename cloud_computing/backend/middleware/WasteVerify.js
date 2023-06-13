import Waste from "../models/ElektronikModels.js";

export const verifyWaste = async (req, res, next) => {
  const waste = await Waste.findOne({
    where: {
      jenis_elektronik: req.body.jenis_elektronik,
    },
  });
  if (waste) return res.status(200).json({ msg: "Sampah sudah ada" });
  next();
};
