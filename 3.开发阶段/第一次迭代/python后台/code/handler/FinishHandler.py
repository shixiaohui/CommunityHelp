import tornado.ioloop
import tornado.web
import tornado.httpserver
import json

class FinishHandler(tornado.web.RequestHandler):
	def post(self):
		#username = self.get_argument("username")
		#eventid = self.get_argument("eventid")
		username = "oo11o"
		eventid = 1
		uid = self.application.dbapi.getUserByUserName(username)["id"]
		event = self.application.dbapi.getEventByEventId(eventid)
		if(event is None):
			self.write("{'state':1}")
			print "event not exist"
			return
		rNamelist = self.application.dbapi.getAllRelativeNamebyUid(event["usrid"])
		print rNamelist
		if(uid not in rNamelist):
			self.write("{'state':2}")
			print "user not relative or itself,can not update sate"
			return
		self.application.dbapi.changeEventState(eventid);
		self.write("FinishHandler")
		return
