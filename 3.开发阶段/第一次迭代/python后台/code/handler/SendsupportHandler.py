import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class SendsupportHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>SendsupportHandler</p><form action='/api/sendsupport' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		#content =self.request.body
		content='{"username":"Anton","eid":4,"message":{"content":"TestContent"}}'
		jobj=json.loads(content)
		result=self.application.dbapi.addSupportByEventIdAndUserName(jobj["eid"],jobj["username"],jobj["message"])
		self.write(str(result)+str(self.application.dbapi.getEventByEventId(jobj["eid"])))