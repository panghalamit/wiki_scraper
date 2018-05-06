
import MySQLdb
import pymongo
from pymongo import MongoClient

mclient = MongoClient('localhost', 27017)
mdb = mclient.wiki
mcoll = mdb.articles
db = MySQLdb.connect(host= "localhost",
                  user="root",
                  passwd="vanwilder",
                  db="wiki")


for j in range(1000):
	try:
		cur = db.cursor()
		cur.execute("SELECT *  From articles Where Id=%s",j+400)
		for i in range(cur.rowcount):
			row = cur.fetchone()
			doc = {"Id": row[0], "Title": row[1], "Body":row[2], "Ref":row[3], "URL": row[4]}
			mcoll.insert_one(doc)
	except:
   			print "error executing sql"

db.close()

