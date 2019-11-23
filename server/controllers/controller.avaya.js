const fetch = require("node-fetch");
const domain = 'https://api.zang.io/v2/';
const accoutSID = 'AC777c3e32bb54b6581d7040758af3f6f6';
const token = '1d2e3af77aca480e9c8f9508dc7faf9b';
const base64 = require('base-64');


exports.sendTextMessage = function(req, res){
    let uri = domain+'Accounts/'+accoutSID+'/SMS/Messages.json';
    fetch("https://api.zang.io/v2/Accounts/"+accoutSID+"/SMS/Messages.json", {
        body: "From=+17329430191&To="+req.body.To+"&Body="+req.body.Body,
        headers: {
            Authorization:"Basic " + base64.encode('AC777c3e32bb54b6581d7040758af3f6f6'+":"+"1d2e3af77aca480e9c8f9508dc7faf9b"),
            "Content-Type": "application/x-www-form-urlencoded"
        },
        method: "POST"
    }).then(respond=>{
        res.send(respond);
    }).catch(err=>{
        res.send(err);
    })

};
