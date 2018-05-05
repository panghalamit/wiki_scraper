from lxml import html
import requests
import re
import MySQLdb

conn = MySQLdb.connect(host= "localhost",
                  user="root",
                  passwd="vanwilder",
                  db="wiki")
x = conn.cursor()


for i in range(10000):
        lang = 'en'
	if i>7000:
		lang = 'ja'
	page = requests.get('http://'+lang+'.wikipedia.org/wiki/Special:Random')

	url  = page.url
	tree = html.fromstring(page.content)

	title = tree.xpath('//title/text()')
	title = title[0].split(' - ')[0].strip()

	link_page = url.split('wiki/')[1]
	#print title
	#print link_page
	#print ref
        #print isinstance(title, unicode)
        title = title.encode('utf-8')
	wiki_text = requests.get('http://'+lang+'.wikipedia.org/w/index.php?title='+link_page+'&action=raw')
	raw = unicode(wiki_text.content, 'utf-8')
        #print isinstance(raw, unicode)
        #print isinstance(url, unicode)
	url = url.encode('utf-8')

	body = raw.split('==')[0].strip()
	#print isinstance(body, unicode)
	body = body.encode('utf-8')
	ref_patt1 = re.compile('<ref.*>.*</ref>')
	ref_patt2 = re.compile('<ref.*/>')
	reflist = ref_patt1.findall(raw) + ref_patt2.findall(raw)
	refs= ''.join(reflist)
	refs =  refs.encode('utf-8')
	try:
   		x.execute("""INSERT INTO articles (Title, Body, `References`, URL) VALUES (%s,%s,%s, %s)""",(title ,body, refs, url))
   		conn.commit()
	except:
   		print "error inserting"
   		conn.rollback()

conn.close()

