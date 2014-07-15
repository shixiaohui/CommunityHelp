import tornado.ioloop
import tornado.web
import tornado.httpserver
import os,MySQLdb,dbapi
from handler import *

#login url handler
class IndexHandler(tornado.web.RequestHandler):
				def get(self):
								self.render("index.html")

class app(tornado.web.Application):
	def __init__(self):
		settings = {
			"static_path": os.path.join(os.path.dirname(__file__), "static"),
			"debug": True,
			"cookie_secret":"community"
		}
		handlers=[(r"/",IndexHandler),
			(r"/api/login",LoginHandler.LoginHandler),
			(r"/api/register",RegisterHandler.RegisterHandler),
			(r"/api/userauthentication",AuthenHandler.AuthenHandler),
			(r"/api/logout",LogoutHandler.LogoutHandler),
			(r"/api/cancel",CancelHandler.CancelHandler),
			(r"/api/checkrelatives",CheckrelativesHandler.CheckrelativesHandler),
			(r"/api/deleterelatives",DeleterelativesHandler.DeleterelativesHandler),
			(r"/api/addrelatives",AddrelativesHandler.AddrelativesHandler),
			(r"/api/history",HistoryHandler.HistoryHandler),
			(r"/api/helpmessage",HelpmessageHandler.HelpmessageHandler),
			(r"/api/supportmessage",SupportmessageHandler.SupportmessageHandler),
			(r"/api/finish",FinishHandler.FinishHandler),
			(r"/api/givecredit",GivecreditHandler.GivecreditHandler),
			(r"/api/addaid",AddaidHandler.AddaidHandler),
			(r"/api/sendsupport",SendsupportHandler.SendsupportHandler),
			(r"/api/quitaid",QuitaidHandler.QuitaidHandler),
			(r"/api/event",EventHandler.EventHandler)]
		tornado.web.Application.__init__(self,handlers,**settings)
		self.dbapi=dbapi.dbapi()
		

if __name__=="__main__":
	server=tornado.httpserver.HTTPServer(app())
	server.listen(8080)
	tornado.ioloop.IOLoop.instance().start()
