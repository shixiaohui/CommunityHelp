'''Yeqin Zheng, 09/07/2014'''
import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

''' Delete a relation between two users. Succeed with "1" returned, else with "0". '''

class DeleterelativesHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("<p>DeleterelativesHandler</p><form action='/api/deleterelatives' method='post'><input type='submit' value='submit'></form>")

	def post(self):
		#self.write(u_id + r_id)
		#u_name = self.get_argument('u_name')
		#r_name = self.get_argument('r_name')
		content = '{"username1":"ooo","username2":"oo11o"}'
		j = json.loads(content)
		row = self.application.dbapi.getRelationByUsername(j['username1'],j['username2'])
		#self.write(row2)
		if row == 0 :
			delete_message = {'state': 0}
		else :
			self.application.dbapi.deleteRelationByUsername(j['username1'],j['username2'])
			delete_message = {'state': 1}

		self.write(delete_message)
