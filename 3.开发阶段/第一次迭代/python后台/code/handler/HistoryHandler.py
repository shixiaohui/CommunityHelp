import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class HistoryHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>historyHandler</p><form action='/api/history' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		content='{"id":1,"name":"ooo"}'
		jobj=json.loads(content)
		#his1=self.application.dbapi.getEventsByUserId(jobj['id'])
		his2=self.application.dbapi.getEventsByUserName(jobj['name'])
		self.write(str(his2))