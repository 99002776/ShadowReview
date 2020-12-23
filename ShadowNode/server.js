const {Pool} = require("pg")
const express = require ("express")
const app = express();
app.use(express.json())
app.use(express.static("images")); 
const pool = new Pool({
    "user": "postgres",
    "password" : "password",
    "host" : "localhost",
    "port" : 5432,
    "database" : "Boat"
})

 let hid;
app.get("/", (req, res) => res.sendFile(`${__dirname}/main.html`))
app.get("/display", (req, res) => res.sendFile(`${__dirname}/index.html`))
app.get("/eventdisplay", (req, res) => res.sendFile(`${__dirname}/BoatEvent.html`))


app.get("/gethid/:hid", async (req, res) => {
    // const rows =await readTodos();
    // res.setHeader("content-type", "application/json")
    hid =  req.params.hid;
    console.log(hid)
    // res.send(JSON.stringify(rows))
})  

app.get("/event", async (req, res) => {
    const rows =await readTodosEvent();
    // res.setHeader("content-type", "application/json")
    res.send(JSON.stringify(rows))
})    


app.get("/todos", async (req, res) => {
    const rows =await readTodos();
    // res.setHeader("content-type", "application/json")
    res.send(JSON.stringify(rows))
})    


 
app.listen(8080, () => console.log("Web server is listening.. on port 8080"))
 
start()
 
async function start() {
    await connect();
   
}
 
async function connect() {
    try {
        await pool.connect(); 
    }
    catch(e) {
        console.error(`Failed to connect ${e}`)
    }
}
 
async function readTodos() {
    try {
       
    const results = await pool.query(`SELECT boatlocation.hin,boatlocation.latitude,boatlocation.longitude,boatlocation.heading,boatdynamics.engid,boatdynamics.engine_revolutions,boatdynamics.engine_temperature,boatdynamics.fuel_rate,boatdynamics.timestamp FROM boatlocation INNER JOIN boatdynamics ON boatdynamics.timestamp = boatlocation.timestamp and boatlocation.hin = '${hid}' `);
    //const results = await pool.query(`SELECT boatlocation.hin,boatlocation.latitude,boatlocation.longitude,boatlocation.heading,boatdynamics.engid,boatdynamics.engine_revolutions,boatdynamics.engine_temperature,boatdynamics.fuel_rate,boatdynamics.timestamp FROM boatlocation INNER JOIN boatdynamics ON boatdynamics.timestamp = boatlocation.timestamp`);
    return results.rows;
    }
    catch(e){
        console.log(e)
        return [];
    }
}

async function readTodosEvent() {
    try {
       
    const results = await pool.query(`SELECT * from boatevent where hin='${hid}'`);
    //const results = await pool.query(`SELECT boatlocation.hin,boatlocation.latitude,boatlocation.longitude,boatlocation.heading,boatdynamics.engid,boatdynamics.engine_revolutions,boatdynamics.engine_temperature,boatdynamics.fuel_rate,boatdynamics.timestamp FROM boatlocation INNER JOIN boatdynamics ON boatdynamics.timestamp = boatlocation.timestamp`);
    return results.rows;
    }
    catch(e){
        console.log(e)
        return [];
    }
}
 



 
