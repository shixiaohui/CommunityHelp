import tornado.ioloop
import tornado.web
import tornado.httpserver
import os,sys,json
sys.path.append("..")
import dbapi
class LoginHandler(tornado.web.RequestHandler):
        def post(self):
                username = self.get_argument('username')
                passwd = self.get_argument('passwd')
                user = self.application.dbapi.getUserByUserName(username)
                if(user is None):
                        self.write("{'state':1}")
                        print "username not exist"
                        return
                if(user["password"]!= passwd)
                        self.write("{'state':2}")
                        print "passwd incorrect"
                        return
                self.write("{'state':3}")
                print("Login")
                return
                        

