const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let AssociateShema = new Schema({
    associateUser:String,
    name:String,
    relationship:String,
    email:String,
    phone: Number,
    address:String
});

module.exports = mongoose.model('AssociateSchema', AssociateShema);