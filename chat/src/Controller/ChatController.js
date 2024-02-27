const mysql = require('mysql2/promise');

class Database {
    constructor(config) {
        this.config = config;
    }

    async connect() {
        try {
            this.connection = await mysql.createConnection(this.config);
            console.log('Conexão bem sucedida ao banco de dados!');
        } catch (error) {
            console.error('Erro ao conectar ao banco de dados:', error);
        }
    }

    async executeQuery(sql, values = []) {
        try {
            const [rows] = await this.connection.execute(sql, values);
            return rows;
        } catch (error) {
            console.error('Erro ao executar a consulta:', error);
            throw error;
        }
    }

    async createTable(table_name, fields) {
        try {
            const fieldList = Object.entries(fields).map(([name, type]) => `${name} ${type.toUpperCase()}`).join(', ');
            const sql = `CREATE TABLE IF NOT EXISTS ${table_name} (${fieldList})`;
            await this.executeQuery(sql);
            console.log(`Tabela ${table_name} criada com sucesso!`);
        } catch (error) {
            console.error('Erro ao criar tabela:', error);
            throw error;
        }
    }

    async insertTable(table_name, fields) {
        try {
            const fieldNames = Object.keys(fields).join(', ');
            const fieldValues = Object.values(fields);
            const placeholders = fieldValues.map(() => '?').join(', ');
            const sql = `INSERT INTO ${table_name} (${fieldNames}) VALUES (${placeholders})`;
            await this.executeQuery(sql, fieldValues);
            console.log(`Registro inserido na tabela ${table_name} com sucesso!`);
        } catch (error) {
            console.error('Erro ao inserir registro:', error);
            throw error;
        }
    }

    async selectTable(table_name) {
        try {
            const sql = `SELECT * FROM ${table_name}`;
            const rows = await this.executeQuery(sql);
            return rows;
        } catch (error) {
            console.error('Erro ao selecionar registros:', error);
            throw error;
        }
    }

    async dropTable(table_name) {
        try {
            const sql = `DROP TABLE IF EXISTS ${table_name}`;
            await this.executeQuery(sql);
            console.log(`Tabela ${table_name} removida com sucesso!`);
        } catch (error) {
            console.error('Erro ao excluir tabela:', error);
            throw error;
        }
    }

    async close() {
        try {
            await this.connection.end();
            console.log('Conexão fechada com sucesso!');
        } catch (error) {
            console.error('Erro ao fechar a conexão:', error);
        }
    }
}

module.exports = Database;
