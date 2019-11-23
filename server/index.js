const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
const router = require('./routes/route');
const server = express();

const uri = "mongodb+srv://jsong336:hackwestern@cluster0-esxnz.mongodb.net/test?retryWrites=true&w=majority";


mongoose.connect(uri, {useNewUrlParser:true, useUnifiedTopology:true});
console.log('Connected to the database (mongoose)');

server.use(bodyParser.json());
server.use(bodyParser.urlencoded({extended:false}));
server.use('/', router);

var port = process.env.PORT || 8080;

server.listen(port, ()=>{
    console.log("Server running on port "+port);
});

