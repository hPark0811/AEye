const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let UserShema = new Schema({
    email:String,
    password:String,
    name:String,
    address:String,
    postalCode:String,
    city:String,
    province:String,
    country:String
});

module.exports = mongoose.module('User', UserShema);