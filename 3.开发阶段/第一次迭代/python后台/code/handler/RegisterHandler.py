import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class RegisterHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>RegisterHandler</p><form action='/api/register' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		#content = self.get_argument("content")
		content = '{"username": "test3","password": "test3","kind": 1, "cardid":"test3" ,"realname":"realtest3","sex":1,"age":41, "address":"iii","illness":"hijiiii"}'
		j = json.loads(content)
		if(self.application.dbapi.getUserByUserName(j['username']) is not None):
			self.write("{'state':1}")
			print "username exist"
			return
		if(self.application.dbapi.getInfoBycardid(j['cardid']) is not None):
			self.write("{'state':2}")
			print "cardid exist"
			return
		
		self.application.dbapi.register(j)
		self.write("{'state':3}")
		print("Register")
		return
