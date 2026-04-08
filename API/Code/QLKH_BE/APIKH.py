import flask
import pyodbc
from flask_cors import CORS
connection_str = (
    "DRIVER={ODBC Driver 18 for SQL Server};"
    "SERVER=localhost,1433;"
    "DATABASE=BKCAD_KhachHang;"
    "UID=SA;"
    "PWD=Tuan446448;"
    "Encrypt=yes;"
    "TrustServerCertificate=yes;"
)

connection = pyodbc.connect(connection_str)

app = flask.Flask(__name__)
CORS(app, supports_credentials=True)

@app.route('/kh/getall', methods = ['GET'])
def getAllKH():
    try:
        cursor = connection.cursor() 
        cursor.execute("select * from tblQLKH")
        results = []
        keys = []
        for i in cursor.description:
            keys.append(i[0])
        for val in cursor.fetchall():
            results.append(dict(zip(keys, val)))
        resp = flask.jsonify(results)
        resp.status_code = 200
        return resp
    except Exception as e:
        return e

@app.route('/kh/getbyid/<id>', methods = ['GET'])
def getKHById(id):
    try:
        cursor = connection.cursor()
        cursor.execute("select * from tblQLKH where maKhach = ?", id)
        results = []
        keys = []
        for i in cursor.description:
            keys.append(i[0])
        for val in cursor.fetchall():
            results.append(dict(zip(keys, val)))
        resp = flask.jsonify(results)
        resp.status_code = 200
        return resp
    except Exception as e:
        print(e)

@app.route('/kh/add', methods = ['POST'])
def addKH():
    try:
        mk=flask.request.json.get("maKhach")
        tk = flask.request.json.get("tenKhach")
        dc = flask.request.json.get("diaChi")
        dt = flask.request.json.get("dienThoai")
        cursor = connection.cursor()
        sql = "insert into tblQLKH(Makhach,tenKhach, diaChi, dienThoai) values( ?, ?, ?, ?)"
        data = (mk,tk, dc, dt)
        cursor.execute(sql, data)
        connection.commit()
        resp = flask.jsonify({"mess": "thành công"})
        resp.status_code = 200
        return resp
    except Exception as e:
        print(e)

@app.route('/kh/update', methods = ['PUT'])
def updateKH():
    try:
        ma = flask.request.json.get("Makhach")
        tk = flask.request.json.get("tenKhach")
        dc = flask.request.json.get("diaChi")
        dt = flask.request.json.get("dienThoai")
        cursor = connection.cursor()
        sql = "update tblQLKH set tenKhach = ?, diaChi = ?, dienThoai = ? where Makhach = ?"
        data = (tk, dc, dt, ma)
        cursor.execute(sql, data)
        connection.commit()
        resp = flask.jsonify({"mess": "thành công"})
        resp.status_code = 200
        return resp
    except Exception as e:
        print(e)

@app.route('/kh/delete', methods = ['DELETE'])
def deleteKH():
    try:
        ma = flask.request.json.get("Makhach")
        cursor = connection.cursor()
        sql = "delete tblQLKH where Makhach = ?"
        data = (ma)
        cursor.execute(sql, data)
        connection.commit()
        resp = flask.jsonify({"mess": "thành công"})
        resp.status_code = 200
        return resp
    except Exception as e:
        print(e)

if __name__ == "__main__":
    # app.run(debug=True)
    app.run(host='0.0.0.0', port=9999, debug=True)