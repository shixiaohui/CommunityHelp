import tornado.ioloop
import tornado.web
import tornado.httpserver
import os,json
class QuitaidHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>QuitaidHandler</p><form action='/api/quitaid' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		#content =self.request.body
		content = '{"username":"oo11o","eventid":5}'
		j = json.loads(content)
		uid = self.application.dbapi.getUserByUserName(j['username'])['id']
		if(self.application.dbapi.getEventByEventId(j['eventid'])['state'] == 1):
			print "current had been end,you can not quit"
			self.write("{'state':3}")
			return

		if(self.application.dbapi.checkifUseraddHelper(uid,j['eventid']) is None):
			print "user " + j['username'] +" do not add the aid first"
			self.write("{'state':2}")
			return
		self.application.dbapi.deleteHelperbyUidEid(uid,j['eventid'])
		print "quit success"
		self.write("{'state':1}") 
		return
