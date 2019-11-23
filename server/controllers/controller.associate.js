const mongoose = require("mongoose");
const Associate = require('../models/model.associate');

exports.findAssociatesByUserId = function(req, res){
    Associate.find({associateUser:req.params.userId}, (err, associates)=>{
        if(err)
            res.send(err);
        res.json(associates);
    });
};

exports.addAssociate = function(req, res){
    let associate = new Associate({
        associateUser: req.body.associateUser,
        name: req.body.name,
        relationship: req.body.relationship,
        email: req.body.email,
        phone: req.body.phone
    });
    
    associate.save((err)=>{
        if(err)
            res.send(err);
        res.send("New Associate added");
    })
};

exports.deleteAssociate = function(req, res){
    Associate.findByIdAndRemove(req.params.id, (err, todo)=>{
        if(err)
            res.send(err);
        res.send("Deleted successfully")
    });
}