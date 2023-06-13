import { Sequelize } from "sequelize";

const db = new Sequelize('auth_db','root','ecotron123',{
    host: "34.101.159.13",
    dialect: "mysql"
});

export default db;