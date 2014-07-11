import tornado.ioloop
import tornado.web
import tornado.httpserver
import os
class CheckrelativesHandler(tornado.web.RequestHandler):
		def post(self):
				self.write("CheckrelativesHandler")
