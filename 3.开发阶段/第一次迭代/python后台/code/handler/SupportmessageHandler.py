import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class SupportmessageHandler(tornado.web.RequestHandler):
	def post(self):
		#content = self.get_argument("content")
		content = '{"username": "ooo","eventid": 1,"assist": "ssssssssssssssss"}'
		j = json.loads(content)
		us = self.application.dbapi.getUserByUserName(j['username'])
		if(us is None):
			self.write("{'state':1}")
			print "username not exist"
			return
		print us["id"]
		
		event = self.application.dbapi.getEventByEventId(j['eventid'])
		if(event is None):
			self.write("{'state':2}")
			print "event not exist"
			return
		if (event['state']==1):
			self.write("{'state':3}")
			print "event is end"
			return
		if(us["id"]==event["usrid"]):
			self.application.dbapi.supportmessageinsert(j)
			self.write("{'state':4}")
			print "insert success"
			return
		return
