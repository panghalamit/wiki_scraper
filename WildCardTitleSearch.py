
import MySQLdb

db = MySQLdb.connect(host= "localhost",
                  user="root",
                  passwd="vanwilder",
                  db="wiki")


keyword = raw_input("Input keywords to search similar titles\n")

try:
	cur = db.cursor()
	cur.execute("SELECT Title  From articles Where Title Like %s",keyword+"%")
	for i in range(cur.rowcount):
		row = cur.fetchone()
		print row[0]
except:
   	print "error executing sql"

db.close()

