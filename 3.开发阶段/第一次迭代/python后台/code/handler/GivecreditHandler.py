import tornado.ioloop
import tornado.web
import tornado.httpserver
import os,dbapi
class GivecreditHandler(tornado.web.RequestHandler):
        def post(self):
                self.write("GivecreditHandler")
