import { Sequelize } from "sequelize";
import db from "../config/database.js";
import Users from "./UsersModels.js";
import Elektronik from "./ElektronikModels.js";

const { DataTypes } = Sequelize;

const Transaksi = db.define(
  "tb_trx",
  {
    uuid_trx: {
      type: DataTypes.STRING,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      validate: {
        notEmpty: true,
      },
    },
    status: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        notEmpty: true,
      },
    },
    jmlh: {
      type: DataTypes.INTEGER,
      allowNull: false,
      validate: {
        notEmpty: true,
      },
    },
    userId: {
      type: DataTypes.INTEGER,
      allowNull: false,
      validate: {
        notEmpty: true,
      },
    },
    elektronikId: {
      type: DataTypes.INTEGER,
      allowNull: false,
      validate: {
        notEmpty: true,
      },
    },
  },
  {
    freezeTableName: true,
  }
);

Users.hasMany(Transaksi);
Transaksi.belongsTo(Users, { foreignKey: "userId" });

Elektronik.hasMany(Transaksi);
Transaksi.belongsTo(Elektronik, { foreignKey: "elektronikId" });

export default Transaksi;
