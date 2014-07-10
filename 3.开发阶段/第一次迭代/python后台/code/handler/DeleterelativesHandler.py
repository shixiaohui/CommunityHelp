'''Yeqin Zheng, 09/07/2014'''
import tornado.ioloop
import tornado.web
import tornado.httpserver
import os, json, sys
sys.path.append("..")
import  dbapi

''' Delete a relation between two users. Succeed with "1" returned, else with "0". '''

class DeleterelativesHandler(tornado.web.RequestHandler):
    def post(self):
        #self.write(u_id + r_id)
        u_name = self.get_argument('u_name')
        r_name = self.get_argument('r_name')

        row = self.application.dbapi.getRelationByUserId(u_name, r_name)

        #self.write(row2)
        if row == 0 :
            delete_message = {'state': 0}
        else :
            self.application.dbapi.deleteRelationByUserId(u_name, r_name)
            delete_message = {'state': 1}

        self.write(delete_message)
