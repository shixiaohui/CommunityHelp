import tornado.ioloop
import tornado.web
import tornado.httpserver
import json
class LogoutHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>LogoutHandler</p><form action='/api/logout' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		#content =self.request.body
		content = '{"username":"11oo"}'
		j = json.loads(content)
		uid = self.application.dbapi.getUserByUserName(j['username'])['id']
		self.application.dbapi.updateUserstate(uid,0)
		self.write("{'state':1}")
		self.clear_all_cookies()
		print("Logout success")