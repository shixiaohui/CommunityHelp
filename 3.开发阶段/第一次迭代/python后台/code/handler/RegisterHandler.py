import tornado.ioloop
import tornado.web
import tornado.httpserver
import os,sys,json
sys.path.append("..")
import dbapi
class RegisterHandler(tornado.web.RequestHandler):
        def post(self):
                #content = self.get_argument("content")
                content = '{"username": "ooo","password": 111111,"kind": 1, "cardid":"42434q" ,"realname":"hiii","sex":1,"age":41, "address":"iii","illness":"hijiiii"}'
                j = json.loads(content)
                if(self.application.dbapi.getUserByUserName(j['username']) is not None):
                        self.write("{'state':1}")
                        print "username exist"
                        return
                if(self.application.dbapi.getInfoBycardid(j['cardid']) is not None):
                        self.write("{'state':2}")
                        print "cardid exist"
                        return
                
                self.application.dbapi.register(j)
                self.write("{'state':3}")
                print("Register")
                return
