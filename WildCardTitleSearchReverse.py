

import MySQLdb
def printReverseStr(orig):
	stack = []
	res = ''
	for c in orig:
		if(c != ' '):
			stack.append(c)
		else:
			while len(stack) != 0:
				res+=stack.pop()
			res+=c
        print(res)

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
		printReverseStr(row[0])
except:
   	print("error executing sql")

db.close()

