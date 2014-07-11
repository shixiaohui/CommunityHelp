# -*- coding: utf-8 -*-
import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class LoginHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>LoginHandler</p><form action='/api/login' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		#username = self.get_argument('username')
		#passwd = self.get_argument('passwd')
		content = '{"username":"oo","password":"111111"}'
		j = json.loads(content)
		user = self.application.dbapi.getUserByUserName(j['username'])
		if(user is None):
			self.write("{'state':1}")
			print "username not exist"
			return
		if(user["password"]!= j['password']):
			self.write("{'state':2}")
			print "passwd incorrect"
			return
		self.write("{'state':3}")
		print("Login")
		return
