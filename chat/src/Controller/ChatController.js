const sqlite3 = require('sqlite3')
const db = new sqlite3.Database("./db.sqlite") 

const createTable = (table_name,filds)=>{
    let fieldsList = []
    Object.entries(filds).forEach((e)=>{
        let response = `${e[0] + ' '+ e[1].toUpperCase()}`
        fieldsList.push(response )  
    })
    const sql = `CREATE TABLE IF NOT EXISTS ${table_name}(
        ${fieldsList}
    )`

    db.exec(sql,(err)=>{
        if(err){
            console.log(err) 
        }
    })
}

const insertTable = (table_name,fields)=>{
    let fieldsList = []
    let valuesList = []
    Object.entries(fields).forEach((e)=>{
        fieldsList.push(`'${e[0]}'`)
        valuesList.push(`'${e[1]}'`) 
    })

    let sql = `INSERT INTO ${table_name}(${fieldsList}) Values(${valuesList})`
    console.log(sql)
    db.exec(sql,(err)=>{
        if(err){
            console.log(err)
        }
    })
}

const selectTable = (table_name,cb)=>{
    const sql = `SELECT * FROM ${table_name}`
    
    db.all(sql,(err,rows)=>{
        if(err){
            console.log(err)
        }
        cb(rows)
    })
}


const dropTable = (table_name)=>{
    db.exec(`DROP TABLE ${table_name}`)
}

module.exports = {createTable,insertTable,selectTable,dropTable}
