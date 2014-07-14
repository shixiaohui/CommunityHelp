'''Yeqin Zheng, 09/07/2014'''
import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

''' Add a relation between two users. Succeed with "1" returned, else with "0". '''

class AddrelativesHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>AddrelativesHandler</p><form action='/api/addrelatives' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		#content =self.request.body
		content = '{"u_name":"test","r_name":"test1"}'
		j = json.loads(content)
		uname =self.get_secure_cookie('username')
		print uname
		row = self.application.dbapi.getRelationByUsername(uname, j['r_name'])	#j['u_name']
		if row == 0:
			self.application.dbapi.addRelationByUsername(uname, j['r_name'])
			add_message = {'state': 1}
			print "add relative success"
		else:
			add_message = {'state': 0}
			print "two already has relative relation"

		self.write(add_message)
		return
