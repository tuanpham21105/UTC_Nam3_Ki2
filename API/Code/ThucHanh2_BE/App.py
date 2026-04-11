import flask
import pyodbc
from flask_cors import CORS
connection_str = (
    "DRIVER={ODBC Driver 18 for SQL Server};"
    "SERVER=localhost,1433;"
    "DATABASE=DuLieu;"
    "UID=SA;"
    "PWD=Tuan446448;"
    "Encrypt=yes;"
    "TrustServerCertificate=yes;"
)

connection = pyodbc.connect(connection_str)

app = flask.Flask(__name__)
CORS(app, supports_credentials=True)

@app.route('/sp/getall', methods = ['GET'])
def getAllSp():
    try:
        cursor = connection.cursor() 
        cursor.execute("select * from tblSanPham")
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
    
@app.route('/sp/getbyname', methods=['GET'])
def getSpByName():
    keyword = flask.request.args.get("keyword")
    try:
        cursor = connection.cursor() 
        param = f"%{keyword}%"
        cursor.execute("select * from tblSanPham inner join tblChatLieu on tblSanPham.MaCL = tblChatLieu.MaCL where lower(tblSanPham.TenSP) like lower(?) or lower(tblChatLieu.TenCL) like lower(?)", param, param)
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
        return flask.jsonify(e)
    
@app.route('/sp/getleft', methods=['GET'])
def getSpLeft():
    try:
        cursor = connection.cursor() 
        cursor.execute("select * from tblSanPham where SoLuong > 0")
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
        return flask.jsonify(e)

@app.route('/sp/add', methods = ['POST'])
def addKH():
    try:
        msp = flask.request.json.get("MaSP")
        tsp = flask.request.json.get("TenSP")
        mcl = flask.request.json.get("MaCL")
        mt = flask.request.json.get("MoTa")
        gn = flask.request.json.get("GiaNhap")
        gb = flask.request.json.get("GiaBan")
        sl = flask.request.json.get("SoLuong")
        cursor = connection.cursor()
        sql = "insert into tblSanPham(MaSP, TenSP, MaCL, MoTa, GiaNhap, GiaBan, SoLuong) values( ?, ?, ?, ?, ?, ?, ?)"
        data = (msp, tsp, mcl, mt, gn, gb, sl)
        cursor.execute(sql, data)
        connection.commit()
        resp = flask.jsonify({"mess": "thành công"})
        resp.status_code = 200
        return resp
    except Exception as e:
        print(e)
        return flask.jsonify(e)
    
@app.route('/sp/update/<id>', methods = ['PUT'])
def updateKH(id):
    try:
        tsp = flask.request.json.get("TenSP")
        mcl = flask.request.json.get("MaCL")
        mt = flask.request.json.get("MoTa")
        gn = flask.request.json.get("GiaNhap")
        gb = flask.request.json.get("GiaBan")
        sl = flask.request.json.get("SoLuong")
        cursor = connection.cursor()
        sql = "update tblSanPham set TenSP = ?, MaCL = ?, MoTa = ?, GiaNhap = ?, GiaBan = ?, SoLuong = ? where MaSP = ?"
        data = (tsp, mcl, mt, gn, gb, sl, id)
        cursor.execute(sql, data)
        connection.commit()
        resp = flask.jsonify({"mess": "thành công"})
        resp.status_code = 200
        return resp
    except Exception as e:
        print(e)
        return flask.jsonify(e)

@app.route('/sp/delete/<id>', methods = ['DELETE'])
def deleteKH(id):
    try:
        cursor = connection.cursor()
        sql = "delete tblSanPham where MaSP = ?"
        data = (id)
        cursor.execute(sql, data)
        connection.commit()
        resp = flask.jsonify({"mess": "thành công"})
        resp.status_code = 200
        return resp
    except Exception as e:
        print(e)

if __name__ == "__main__":
    # app.run(debug=True)
    app.run(debug = True, port=8080)