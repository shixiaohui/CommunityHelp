import tornado.ioloop
import tornado.web
import tornado.httpserver
import os,dbapi
class QuitaidHandler(tornado.web.RequestHandler):
		def post(self):
				self.write("QuitaidHandler")
