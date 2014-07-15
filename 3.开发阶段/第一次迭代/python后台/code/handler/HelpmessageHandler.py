import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class HelpmessageHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>HelpmessageHandler</p><form action='/api/helpmessage' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		#content =self.request.body
		content='{"username":"oo","message":{"kind":1,"content":"TestContent","assist":"TestAssist"}}'
		jobj=json.loads(content)
		result=self.application.dbapi.addEventByUserName(jobj["username"],jobj["message"])
		self.write(str(result))
		return