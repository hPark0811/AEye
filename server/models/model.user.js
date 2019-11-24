const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let UserShema = new Schema({
    email:String,
    password:String,
    name:String,
    phone:Number,
    address:String,
    postalCode:String,
    city:String,
    province:String,
    country:String
});

module.exports = mongoose.model('User', UserShema);