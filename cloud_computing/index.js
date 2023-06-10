import express from "express";
import cors from "cors";
import session from "express-session";
import dotenv from "dotenv";
import db from "./config/database.js";
import SequelizeStore from "connect-session-sequelize";
import UserRoute from "./routes/UserRoute.js";
import TransaksiRoute from "./routes/TransaksiRoute.js";
import ElektronikRoute from "./routes/ElektronikRoute.js";
import AuthRoute from "./routes/AuthRoute.js";

dotenv.config();

const app = express();

//(async()=>{
  //  await db.sync()
//})(); 

const sessionStore = SequelizeStore(session.Store);

const store = new sessionStore({
    db: db
});

app.use(session({
    secret: process.env.SESS_SECRET,
    resave: false,
    saveUninitialized: true,
    cookie:{
        secure: 'auto'
    }
}));

app.use(cors({
    credentials: true,
    origin:''
}));

app.use(express.json());
app.use(UserRoute);
app.use(TransaksiRoute);
app.use(ElektronikRoute);
app.use(AuthRoute);

app.listen(process.env.APP_PORT, ()=>{
    console.log('Server Running...');
});