const express = require('express');
const http = require('http');
const WebSocket = require('ws');
const Database = require('./Controller/ChatController');

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

app.use(express.json());
app.use(require('cors')({ origin: '*' }));

const dbConfig = {
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'database'
};

const database = new Database(dbConfig);

async function initializeDatabase() {
    try {
        await database.connect();
        await database.createTable("chat_table", {
            id: "INTEGER PRIMARY KEY AUTO_INCREMENT",
            username: "TEXT",
            uuid_post: "TEXT",
            content: "TEXT"
        });
        console.log('Tabela "chat" criada com sucesso!');
    } catch (error) {
        console.error('Erro ao criar tabela "chat":', error);
    }
}

wss.on("connection", (ws) => {
    ws.on("message", async (msg) => {
        try {
            const response = JSON.parse(msg);
            await database.insertTable('chat_table', response);

            wss.clients.forEach((client) => {
                if (client.readyState === WebSocket.OPEN) {
                    client.send(msg);
                }
            });
        } catch (error) {
            console.error('Erro ao processar a mensagem recebida:', error);
        }
    });
});

app.get("/messages", async (req, res) => {
    try {
        const rows = await database.selectTable("chat_table");
        res.json(rows);
    } catch (error) {
        console.error('Erro ao selecionar registros da tabela "chat":', error);
        res.status(500).json({ error: 'Erro interno do servidor' });
    }
});
 
app.delete("/messages", async (req, res) => {
    try {
        await database.dropTable("chat_table");
        res.send("Mensagens Deletadas");
    } catch (error) {
        console.error('Erro ao excluir tabela "chat_table":', error);
        res.status(500).send('Erro interno do servidor');
    }
});

const PORT = process.env.PORT || 3001;

server.listen(PORT, async () => {
    console.log(`Servidor rodando na porta ${PORT}`);
    await initializeDatabase(); 
});
