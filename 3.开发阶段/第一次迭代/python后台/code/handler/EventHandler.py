import tornado.ioloop
import tornado.web
import tornado.httpserver

class EventHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("eventHandler")
	def post(self):
		name=self.get_argument("name")
		iden=self.get_argument("id")
		event=self.application.dbapi.getEventByEventId(iden)
		if(event):
			self.write(str(event))
		else:
			self.write("No event for id "+str(iden))