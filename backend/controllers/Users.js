import Users from "../models/UsersModels.js";
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
    const response = await Users.findOne({
      attributes: ["uuid", "nama", "email", "role", "jml_point"],
      where: {
        uuid: req.params.id,
      },
    });
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
  const {
    nama,
    email,
    password,
    confPassword,
    alamat,
    profile_image,
    no_telp,
    role,
  } = req.body;
  let hashPassword;
  if (password === "" || password === null) {
    hashPassword = user.password;
  } else {
    hashPassword = await argon2.hash(password);
  }
  if (password !== confPassword)
    return res
      .status(400)
      .json({ msg: "Password dan Confirm password tidak cocok" });
  try {
    await Users.update(
      {
        nama: nama,
        email: email,
        password: hashPassword,
        alamat: alamat,
        profile_image: profile_image,
        no_telp: no_telp,
        role: role,
        jml_point: 0,
      },
      {
        where: {
          id: user.id,
        },
      }
    );
    res.status(200).json({ msg: "Berhasil Update" });
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
