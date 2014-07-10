import tornado.ioloop
import tornado.web
import tornado.httpserver
import os,sys,json
sys.path.append("..")
import dbapi
class FinishHandler(tornado.web.RequestHandler):
    def post(self):
        #username = self.get_argument("username")
        #eventid = self.get_argument("eventid")
        event = self.application.dbapi.getEventByEventId(eventid)
        if(event is None):
            self.write("{'state':1}")
            print "event not exist"
            return
        rNamelist = self.application.dbapi.getAllRelativeNamebyUid(event["usrid"])
        if()
        self.application.dbapi.updateEstate(username,eventid)

        self.write("FinishHandler")