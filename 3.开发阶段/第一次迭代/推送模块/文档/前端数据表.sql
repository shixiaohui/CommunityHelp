drop table IF EXISTS tbl_help;
drop table IF EXISTS tbl_attachment;
drop table IF EXISTS tbl_avatar;
drop table IF EXISTS tbl_aid;
drop table IF EXISTS tbl_friends;

/*
事件表
id:自增id
eventid:事件id
userid:求助者的用户id
username:求助者的用户名
content:求助信息内容
time:发送求助信息的时间
kind:事件类型(安全，生活，健康)
delete:删除权限(0-有，1-无)
state:事件状态(0-求助中，1-结束)
*/
CREATE TABLE tbl_help (
id INTEGER PRIMARY KEY AUTOINCREMENT,
eventid INTEGER,
userid INTEGER,
username TEXT,
content TEXT,
time TEXT,
kind INTEGER,
delete INTEGER,
state INTEGER
);

/*
附件表
求助事件附带的图片、语音等文件
eventid:事件id
filename:附件文件名称
url:文件下载地址
type:附件类型(0-图片，1-视频，2-音频)
*/
CREATE TABLE tbl_attachment (
eventid INTEGER,
filename TEXT,
url TEXT,
type INTEGER
);

/*
援助信息表
eventid:事件id
userid:援助者的用户id
username:援助者的用户名
content:援助信息内容
time:发送援助信息的时间
*/
CREATE TABLE tbl_aid (
eventid INTEGER,
userid INTEGER,
username TEXT,
content TEXT,
time TEXT
);

/*
头像表
userid:用户id
path:头像文件存储路径
version:头像版本号
*/
CREATE TABLE tbl_avatar (
userid INTEGER,
path TEXT,
version INTEGER
);

/*
好友表
id:自增id
username:用户名
info:用户简介
group:分组(0-黑名单，1-亲人，2-好友，3-陌生人)
*/
CREATE TABLE tbl_friends (
id INTEGER PRIMARY KEY AUTOINCREMENT,
userid INTEGER,
username TEXT,
info TEXT,
group INTEGER
);