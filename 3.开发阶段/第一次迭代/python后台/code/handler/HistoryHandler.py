import tornado.ioloop
import tornado.web
import tornado.httpserver

class HistoryHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("getHandler")

	def post(self):
		name=self.get_argument("name")
		iden=self.get_argument("id")
		his1=self.application.dbapi.getEventsByUserId(iden)
		his2=self.application.dbapi.getEventsByUserName(name)
		self.write(str([his1,his2]))