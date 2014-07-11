'''Yeqin Zheng, 09/07/2014'''
import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

''' Add a helper to an event. Succeed with "1" returned, else with "0". '''

class AddaidHandler(tornado.web.RequestHandler):
		def post(self):
			u_name = self.get_argument('u_name')
			e_id = self.get_argument('e_id')

			result = self.application.dbapi.addaidhelper(u_name, e_id)
			self.write("{'state': " + result + "}")

