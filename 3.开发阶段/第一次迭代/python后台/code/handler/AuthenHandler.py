import tornado.ioloop
import tornado.web
import tornado.httpserver
import os
class AuthenHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>AuthenHandler</p><form action='/api/userauthentication' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		self.write("AuthenHandler")
