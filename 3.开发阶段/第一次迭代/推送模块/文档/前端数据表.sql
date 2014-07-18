drop table IF EXISTS tbl_help;
drop table IF EXISTS tbl_aid;
drop table IF EXISTS tbl_user;

/*
事件表
id:自增id
eventid:事件id
username:求助者的用户名
avatar:求助者头像的URL地址
content:求助信息内容
time:发送求助信息的时间
kind:事件类型(安全，生活，健康)
attention:关注人数
participants:参与人数
state:事件状态(求助中，结束)
*/
CREATE TABLE tbl_help (
id INTEGER PRIMARY KEY AUTOINCREMENT,
username TEXT,
avatar TEXT,
content TEXT,
time TEXT,
kind INTEGER,
attention INTEGER,
participants INTEGER,
state INTEGER
);

/*
附件表
求助事件附带的图片、语音等文件
eventid:事件id
filename:附件文件名称
path:附件下载地址或存储路径
state:下载状态(0-未下载，1-已下载)
type:附件类型(0-图片，1-视频，2-音频)
*/
CREATE TABLE tbl_attachment (
eventid INTEGER,
filename TEXT,
path TEXT,
state INTEGER,
type INTEGER
);

/*
头像表
username:用户名
path:头像文件存储路径
*/
CREATE TABLE tbl_avatar (
username TEXT,
path TEXT
);

/*
援助信息表
eventid:事件id
username:援助者的用户名
avatar:援助者头像的URL地址
content:援助信息内容
time:发送援助信息的时间
*/
CREATE TABLE tbl_aid (
eventid INTEGER,
username TEXT,
avatar TEXT,
content TEXT,
time TEXT
);

/*
好友表
id:自增id
avatar:头像URL地址
username:用户名
info:用户简介
group:分组(0-黑名单，1-亲人，2-好友，3-陌生人)
*/
CREATE TABLE tbl_user (
id INTEGER PRIMARY KEY AUTOINCREMENT,
avatar TEXT,
username TEXT,
info TEXT,
group INTEGER
);