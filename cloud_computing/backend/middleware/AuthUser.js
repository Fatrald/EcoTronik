import User from "../models/UsersModels.js";

export const verifyUser = async (req, res, next) => {
  if (!req.session.userId) {
    return res.status(401).json({ msg: "Harap Login Ke akun Anda" });
  }
  const user = await User.findOne({
    where: {
      uuid: req.session.userId,
    },
  });
  if (!user) return res.status(404).json({ msg: "User tidak ditemukan" });
  req.userId = user.id;
  req.role = user.role;
  next();
};

export const verifyEmail = async (req, res, next) => {
  const user = await User.findOne({
    where: {
      email: req.body.email,
    },
  });
  if (user) return res.status(200).json({ msg: "Email sudah ada" });
  next();
};

export const adminOnly = async (req, res, next) => {
  const user = await User.findOne({
    where: {
      uuid: req.session.userId,
    },
  });
  if (!user) return res.status(404).json({ msg: "User tidak ditemukan" });
  if (user.role !== "admin")
    return res.status(403).json({ msg: "Akses Ditolak" });
  next();
};

export const userOnly = async (req, res, next) => {
  const admin = await User.findOne({
    where: {
      uuid: req.session.userId,
    },
  });
  if (!admin) return res.status(404).json({ msg: "Admin tidak ditemukan" });
  if (admin.role !== "user")
    return res.status(403).json({ msg: "Akses Ditolak" });
  next();
};
