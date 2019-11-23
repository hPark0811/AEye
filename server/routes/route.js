const express = require('express');
const controller = require('../controllers/controller');
const router = express.Router();

router.use(function(req, res, next){
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
    next();
});

router.route('/test').get(controller.test);

module.exports = router;
