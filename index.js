const mysql = require("mysql");
xlsx = require("xlsx");

const db = mysql.createConnection({
  host: "127.0.0.1",
  user: "root",
  password: "",
  database: "exceltomysql",
});

let workbook = xlsx.readFile("DataToUpload.xlsx");
let worksheet = workbook.Sheets[workbook.SheetNames[0]];
let range = xlsx.utils.decode_range(worksheet["!ref"]);
db.connect((err) => {
  if (err) {
    console.log("Not connected");
    return;
  }
  console.log("Connected");
});

for (let row = range.s.r; row <= range.e.r; row++) {
  let data = [];

  for (let col = range.s.c; col <= range.e.c; col++) {
    let cell = worksheet[xlsx.utils.encode_cell({ r: row, c: col })];
    data.push(cell.v);
  }
  console.log(data);
  let sql =
    "INSERT INTO `StudentInfo` (`RollNo`,`name`, `branch`, `subject`) VALUES(?,?,?,?)";
  db.query(sql, data, (err, results, fields) => {
    if (err) {
      return console.error("error" + err.message);
    }
    console.log("User ID: " + results.insertId);
  });
}
db.end();
