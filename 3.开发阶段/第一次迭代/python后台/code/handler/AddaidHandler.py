import tornado.ioloop
import tornado.web
import tornado.httpserver
import os,dbapi
class AddaidHandler(tornado.web.RequestHandler):
        def post(self):
                self.write("AddaidHandler")
