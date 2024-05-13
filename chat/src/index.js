const app = require('express')()
const http = require('http').createServer(app)
const WebSocket = require('ws');
const { createTable,insertTable, selectTable, dropTable } = require('./Controller/ChatController');

app.use(require('cors')({origin:"*"}))


createTable("chat",{
    id:"integer primary key autoincrement",
    username:"text",
    uuid_post:"text",
    content:"text"
}) 
/* insertTable("chat",{
    username:"Skyzzin",
    post_uuid:"Skyzzin222",
    content:"Message Enviada"
}) */

const wss = new WebSocket.Server({ server:http });

wss.on("connection",(ws)=>{
    
    ws.on("message",(msg)=>{
        const response = JSON.parse(msg)
        insertTable('chat',response)

        wss.clients.forEach((client) => {
            if (client.readyState === WebSocket.OPEN) {
                client.send(msg);
            }
        });
        
    })
 
})

app.get("/messages",(req,res)=>{
    selectTable("chat",(rows)=>{
        res.json(rows)
    }) 
})

app.delete("/messages",(req,res)=>{
    dropTable("chat")
    res.send("Menssagens Deletadas")
})




http.listen(3001,()=>{console.log("Server Is Running 3001")})