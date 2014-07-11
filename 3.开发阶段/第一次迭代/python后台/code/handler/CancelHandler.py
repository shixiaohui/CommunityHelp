import tornado.ioloop
import tornado.web
import tornado.httpserver
import os
class CancelHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>CancelHandler</p><form action='/api/cancel' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		self.write("CancelHandler")
