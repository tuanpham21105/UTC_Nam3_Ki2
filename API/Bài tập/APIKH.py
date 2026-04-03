import flask
import pyodbc

connection_str = (
    "DRIVER={ODBC Driver 18 for SQL Server};"
    "SERVER=localhost,1433;"
    "DATABASE=your_db;"
    "UID=your_user;"
    "PWD=your_password;"
    "TrustServerCertificate=yes;"
)

connection = pyodbc.connect(connection_str)

app = flask.Flask(__name__)

@app.route('/kh/getall', methods = ['GET'])
def getAllKH():
    try:
        cursor = connection.cursor() 
        cursor.execute("select *from tblKhach")
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

@app.route('/kh/getbyid/<id>', methods = ['GET'])
def getKHById(id):
    try:
        cursor = connection.cursor()
        cursor.execute("select *from tblKhach where MaKhach = ?", id)
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
        mk=flask.request.json.get("Makhach")
        tk = flask.request.json.get("TenKhach")
        dc = flask.request.json.get("DiaChi")
        dt = flask.request.json.get("DienThoai")
        cursor = connection.cursor()
        sql = "insert into tblKhach(Makhach,TenKhach, DiaChi, DienThoai) values( ?, ?, ?, ?)"
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
        tk = flask.request.json.get("TenKhach")
        dc = flask.request.json.get("DiaChi")
        dt = flask.request.json.get("DienThoai")
        cursor = connection.cursor()
        sql = "update tblKhach set TenKhach = ?, DiaChi = ?, DienThoai = ? where Makhach = ?"
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
        sql = "delete tblKhach where Makhach = ?"
        data = (ma)
        cursor.execute(sql, data)
        connection.commit()
        resp = flask.jsonify({"mess": "thành công"})
        resp.status_code = 200
        return resp
    except Exception as e:
        print(e)

if __name__ == "__main__":
    app.run(debug=True)