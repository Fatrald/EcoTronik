import Transaksi from "../models/TransaksiModels.js";
import Elektronik from "../models/ElektronikModels.js";
import Users from "../models/UsersModels.js";

export const getTransaksi = async (req, res) => {
  try {
    const transaksi = await Transaksi.findAll();
    res.status(200).json(transaksi);
  } catch (error) {
    res.status(500).json({ error: "internal server error" });
  }
};

export const getTransaksiByUserId = async (req, res) => {
  const user = await Users.findOne({
    where: { uuid: req.params.uuid },
  });
  try {
    const transaksi = await Transaksi.findAll({
      where: {
        userId: user.id,
      },
    });
    res.status(200).json(transaksi);
  } catch (error) {
    res.status(500).json({ error: "internal server error" });
  }
};

export const getTransaksiByStatus = async (req, res) => {
  try {
    const transaksi = await Transaksi.findAll({
      where: {
        status: req.params.status,
      },
    });
    res.status(200).json(transaksi);
  } catch (error) {
    res.status(500).json({ error: "internal server error" });
  }
};

export const createTransaksi = async (req, res) => {
  const { status, jmlh, uuid, uuid_elect } = req.body;
  try {
    //identify user
    const user = await Users.findOne({
      where: { uuid: uuid },
    });

    const elektronik = await Elektronik.findOne({
      where: { uuid_elect: uuid_elect },
    });
    await Transaksi.create({
      status: status,
      jmlh: jmlh,
      userId: user.id,
      elektronikId: elektronik.id,
    });
    res.status(201).json({ msg: "Berhasil Melakukan Transaksi" });
  } catch (error) {
    console.log(error);
    res.status(500).json({ msg: error.message });
  }
};

export const updateTransaksi = async (req, res) => {
  const { uuid, status } = req.body;
  const transaksi = await Transaksi.findOne({
    where: {
      uuid_trx: uuid,
    },
  });
  if (!transaksi)
    return res.status(404).json({ msg: "Transaksi Tidak Dapat Ditemukan" });
  try {
    await Transaksi.update(
      {
        status: status,
      },
      {
        where: {
          uuid_trx: uuid,
        },
      }
    );
  } catch (error) {
    console.log(error);
    res.status(500).json({ msg: error.message });
  }
};
