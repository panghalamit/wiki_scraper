
import MySQLdb

db = MySQLdb.connect(host= "localhost",
                  user="root",
                  passwd="vanwilder",
                  db="wiki")


num = raw_input("insert article num between 1 and 10000:\n")
if(int(num) < 1):
	exit()
if(int(num) > 10000):
	exit()

try:
	cur = db.cursor()
	cur.execute("SELECT Title, Body  From articles Where Id=%s",num)
	for i in range(cur.rowcount):
		row = cur.fetchone()
		print "Title:\n %s\n" % row[0]
		print "Body:\n %s\n" % row[1]
except:
   	print "error executing sql"

db.close()

