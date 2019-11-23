const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let AssociateShema = new Schema({
    associateUser:String,
    name:String,
    relationship:String,
    email:String,
    address:String,
});

module.exports = mongoose.module('AssociateSchema', AssociateShema);