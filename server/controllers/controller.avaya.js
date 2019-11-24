const fetch = require("node-fetch");
const domain = 'https://api.zang.io/v2/';
const accountSID = 'AC777c3e32bb54b6581d7040758af3f6f6';
const token = '1d2e3af77aca480e9c8f9508dc7faf9b';
const base64 = require('base-64');
const fs = require("fs");

exports.sendTextMessage = function(req, res){
    let uri = domain+'Accounts/'+accountSID+'/SMS/Messages.json';
    fetch(uri, {
        body: "From=+17329430191&To="+req.body.To+"&Body="+req.body.Body,
        headers: {
            Authorization:"Basic " + base64.encode('AC777c3e32bb54b6581d7040758af3f6f6'+":"+"1d2e3af77aca480e9c8f9508dc7faf9b"),
            "Content-Type": "application/x-www-form-urlencoded"},
        method: "POST"
    }).then(respond=>{
        res.send(respond);
    }).catch(err=>{
        res.send(err);
    })
};

exports.sendCallMessage = function(req, res){
    let uri = domain+"Accounts/"+accountSID+"/Calls";
    fetch(uri, {
        body: "From=17329430191&To="+req.body.To+"&Url=https://cloud.zang.io/data/inboundxml/10387fbbbd248a3558cfef68104d65d6ff4406bd?text="+String(req.body.Text),
        headers: {
            Authorization: "Basic " +base64.encode(accountSID+":"+token),
            "Content-Type": "application/x-www-form-urlencoded"            
        },
        method: "POST"}).then((response)=>{
            res.send(response);}).catch((err)=>{res.send(err);}
    );
}


