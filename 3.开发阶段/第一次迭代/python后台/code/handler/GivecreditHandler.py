import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class GivecreditHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>GivecreditHandler</p><form action='/api/givecredit' method='post'><input type='submit' value='submit'></form>")
	def post(self):
		content='{"eventid":4,"helpername":"test2","credit":3}'
		jobj=json.loads(content)
		result=self.application.dbapi.setCreditByEventIdAndUserName(jobj["eventid"],jobj["helpername"],jobj["credit"])
		self.write(str(result)+str(self.application.dbapi.getHelperByEventIdAndUserName(jobj["eventid"],jobj["helpername"])))