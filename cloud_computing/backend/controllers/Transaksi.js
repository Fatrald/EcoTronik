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
    const result = [];
    const user = await Users.findOne({
      where: { uuid: req.params.uuid },
    });
    const transaksi = await Transaksi.findAll({
      where: {
        userId: user.id,
        status: req.params.status,
      },
    });
    for (const data of transaksi) {
      const elektronik = await Elektronik.findOne({
        where: {
          id: data.elektronikId,
        },
      });
      const point = elektronik.point * data.jmlh;

      result.push({
        uuid: data.uuid_trx,
        status: data.status,
        createdAt: data.createdAt,
        jenis_elektronik: elektronik.jenis_elektronik,
        point: point,
        jmlh: data.jmlh,
      });
    }
    res.status(200).json(result);
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

    // const point = user.jml_point;
    // const add_point = elektronik.point * jmlh;
    // const new_point = point + add_point;
    // await user.update(
    //   {
    //     jml_point: new_point,
    //   },
    //   {
    //     where: {
    //       id: user.id,
    //     },
    //   }
    // );
    res.status(201).json({ msg: "Data Berhasil Ditambahkan" });
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
    res.status(200).json({ msg: "Berhasil Update" });
  } catch (error) {
    console.log(error);
    res.status(500).json({ msg: error.message });
  }
};
