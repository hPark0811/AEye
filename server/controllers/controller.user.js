const User = require('../models/model.user');

exports.addUser = function(req, res){
    if (isNaN(req.body.phone)){
        res.send('Incorrect!');
    }
    let user = new User({
        email: req.body.email,
        name: req.body.name,
        password: req.body.password,
        phone:req.body.phone,
        address: req.body.address,
        postalCode: req.body.postalCode,
        city: req.body.city,
        province: req.body.province,
        country: req.body.country
    });
    user.save((err)=>{
        if(err) res.send(err);
    });
    res.send('User added');
};

exports.findUserByEmail = function(req, res){
    User.find({email:req.params.email}, (err, users)=>{
        if(err)
            res.send(err);
        res.json(users);
    });
}

exports.deleteUser = function(req, res){
    User.findByIdAndRemove(req.params.id, (err, todo)=>{
        if(err){
            res.send(err);
        }
        res.send("User delete");
    })
};