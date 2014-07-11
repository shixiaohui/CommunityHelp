import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class EventHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>eventHandler</p><form action='/api/event' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		content='{"eventid":1}'
		jobj=json.loads(content)
		event=self.application.dbapi.getEventByEventId(jobj['eventid'])
		if(event):
			self.write(str(event))
		else:
			self.write("No event for eventid: "+str(jobj['eventid']))