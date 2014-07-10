import tornado.ioloop
import tornado.web
import tornado.httpserver

class HelpMessageHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("HelpMessageHandler")
	def post(self):
		name=self.get_argument("name")
		msg=self.get_argument("message")
		result=self.application.dbapi.addEventByUserName()