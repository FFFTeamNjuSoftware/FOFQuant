from WindPy import *
import MySQLdb
import datetime
from MySQLdb import IntegrityError

w.start()
name = "netWorth"
now = datetime.datetime.now().strftime("%Y-%m-%d")
db = MySQLdb.connect("localhost", 'root', "123456", "fofquant", charset='utf8')
cursor = db.cursor()
cursor.execute("select indexCode from index_info")
for line in cursor.fetchall():
    code = line[0]
    cursor = db.cursor()
    cursor.execute("select max(date) from index_price where code='%s';" % code)
    date = cursor.fetchall()[0][0]
    data = w.wsd(code + ".SH", "close", date, now, "Fill=Previous")
    b = len(data.Fields)
    c = len(data.Data[0])
    d = data.Data
    cursor = db.cursor()
    sql = "insert into index_price(date,code,close,daily_rise) VALUES ('%s','%s',%s,%s);"
    for i in xrange(c):
        if i == 0:
            rise = 0
        else:
            rise = (d[0][i] - d[0][i - 1]) / d[0][i - 1] * 100
        print sql % (str(data.Times[i])[:10], '000001', d[0][i], rise)
        try:
            cursor.execute(sql % (str(data.Times[i])[:10], '000001', d[0][i], rise))
        except IntegrityError, e:
            print e
db.commit()
