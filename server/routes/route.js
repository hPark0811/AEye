const express = require('express');
const controller = require('../controllers/controller');
const associateController = require('../controllers/controller.associate');
const userController = require('../controllers/controller.user');
const avayaController = require("../controllers/controller.avaya");

const router = express.Router();

router.use(function(req, res, next){
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
    next();
});

router.route('/test').get(controller.test);
router.route('/user/:email').get(userController.findUserByEmail);
router.route('/user').post(userController.addUser);
router.route('/user/:id').delete(userController.deleteUser);

router.route('/associate/:userId').get(associateController.findAssociatesByUserId);
router.route('/associate').post(associateController.addAssociate);
router.route('/associate/:id').delete(associateController.deleteAssociate);

router.route('/sendTextMessage').post(avayaController.sendTextMessage);
router.route('/sendCallMessage').post(avayaController.sendCallMessage);

router.route('/sendTextMessageToAll').post(avayaController.sendTextMessageToAll);
router.route('/sendCallMessageToAll').post(avayaController.sendCallMessageToAll);

module.exports = router;
