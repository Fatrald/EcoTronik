import Elektronik from "../models/ElektronikModels.js";

export const getElektronik = async(req, res) => {
    try {
        const response = await Elektronik.findAll({
                attributes: ['uuid_elect','jenis_elektronik','point']
            });
            res.status(200).json(response);
        } catch (error) {
            res.status(500).json({msg: error.message});
        } 
}

export const getElektronikById = async(req, res) => {
    try {
        const elektronik = await Elektronik.findOne({
            where:{
                uuid_elect: req.params.id
            }
        });
        if(!elektronik)return res.status(404).json({msg: "Jenis Sampah Tidak Ditemukan"});
        let response;
        if(req.role === "admin","user") {
            response = await Elektronik.findOne({
                attributes: ['uuid_elect','jenis_elektronik','point'],
                where:{
                    id: elektronik.id
                }
            });
        }
        res.status(200).json(response);
    } catch (error) {
        res.status(500).json({msg: error.message});
    } 
}

export const createElektronik = async(req, res) => {
    const { jenis_elektronik, point } = req.body;
    try {
        await Elektronik.create({
            jenis_elektronik: jenis_elektronik,
            point: point
        });
        res.status(201).json({msg: "Jenis Sampah Berhasil Ditambahkan"});
    } catch (error) {
        res.status(500).json({msg: error.message});
    } 
}

export const deleteElektronik = async(req, res) => {
    try {
        const elektronik = await Elektronik.findOne({
            where:{
                uuid_elect: req.params.id
            }
        });
        if(!elektronik) return res.status(404).json({msg: "Jenis Sampah Tidak Ditemukan"});
        const { jenis_elektronik, point } = req.body;
        if(req.role === "admin") {
            await Elektronik.destroy({
                where:{
                    id: elektronik.id
                }
            });
        }else{
            if(req.role !== "admin") return res.status(403).json({msg: "Akses Terlarang"});
        }
        res.status(200).json({msg: "Jenis sampah sudah terhapus"});
    } catch (error) {
        res.status(500).json({msg: error.message});
    }
}