import Users from "../models/UsersModels.js";
import Transaksi from "../models/TransaksiModels.js";
import argon2, { hash } from "argon2";

export const getUsers = async (req, res) => {
  try {
    const response = await Users.findAll({
      attributes: ["uuid", "nama", "email", "role"],
    });
    res.status(200).json(response);
  } catch (error) {
    res.status(500).json({ msg: error.message });
  }
};

export const getUserById = async (req, res) => {
  try {
    const user = await Users.findOne({
      attributes: [
        "id",
        "uuid",
        "nama",
        "alamat",
        "no_telp",
        "email",
        "role",
        "jml_point",
      ],
      where: {
        uuid: req.params.id,
      },
    });
    const transaction = await Transaksi.findAndCountAll({
      where: {
        userId: user.id,
      },
    });
    const response = {
      uuid: user.uuid,
      nama: user.nama,
      alamat: user.alamat,
      no_telp: user.no_telp,
      email: user.email,
      role: user.role,
      jml_point: user.jml_point,
      transaksi: transaction,
    };
    res.status(200).json(response);
  } catch (error) {
    res.status(500).json({ msg: error.message });
  }
};

export const createUser = async (req, res) => {
  const { nama, email, password, confPassword } = req.body;
  if (password !== confPassword)
    return res
      .status(400)
      .json({ msg: "Password dan Konfirmasi Password tidak cocok" });
  const hashPassword = await argon2.hash(password);
  try {
    await Users.create({
      nama: nama,
      email: email,
      password: hashPassword,
      role: "user",
      jml_point: 0,
    });
    res.status(201).json({ msg: "Registrasi Berhasil" });
  } catch (error) {
    res.status(400).json({ msg: error.message });
  }
};

export const updateUser = async (req, res) => {
  const user = await Users.findOne({
    where: {
      uuid: req.params.id,
    },
  });
  if (!user) return res.status(404).json({ msg: "User tidak ditemukan" });
  const { nama, alamat, email, profile_image, no_telp, jml_point } = req.body;
  try {
    if (email == user.email) {
      await Users.update(
        {
          nama: nama,
          alamat: alamat,
          // email: email,
          profile_image: profile_image,
          no_telp: no_telp,
          jml_point: jml_point,
        },
        {
          where: {
            id: user.id,
          },
        }
      );
      res.status(200).json({ msg: "Berhasil Update" });
    } else {
      await Users.update(
        {
          nama: nama,
          alamat: alamat,
          email: email,
          profile_image: profile_image,
          no_telp: no_telp,
          jml_point: jml_point,
        },
        {
          where: {
            id: user.id,
          },
        }
      );
      res.status(200).json({ msg: "Berhasil Update" });
    }
  } catch (error) {
    res.status(400).json({ msg: error.message });
  }
};

export const deleteUser = async (req, res) => {
  const user = await Users.findOne({
    where: {
      uuid: req.params.id,
    },
  });
  if (!user) return res.status(404).json({ msg: "User tidak ditemukan" });
  try {
    await Users.destroy({
      where: {
        id: user.id,
      },
    });
    res.status(200).json({ msg: "User Berhasil Terhapus" });
  } catch (error) {
    res.status(400).json({ msg: error.message });
  }
};
