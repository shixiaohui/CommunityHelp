drop table IF EXISTS tbl_help;
drop table IF EXISTS tbl_attachment;
drop table IF EXISTS tbl_avatar;
drop table IF EXISTS tbl_aid;
drop table IF EXISTS tbl_friends;

/*
�¼���
id:����id
eventid:�¼�id
userid:�����ߵ��û�id
username:�����ߵ��û���
content:������Ϣ����
time:����������Ϣ��ʱ��
kind:�¼�����(��ȫ���������)
delete:ɾ��Ȩ��(0-�У�1-��)
state:�¼�״̬(0-�����У�1-����)
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
������
�����¼�������ͼƬ���������ļ�
eventid:�¼�id
filename:�����ļ�����
url:�ļ����ص�ַ
type:��������(0-ͼƬ��1-��Ƶ��2-��Ƶ)
*/
CREATE TABLE tbl_attachment (
eventid INTEGER,
filename TEXT,
url TEXT,
type INTEGER
);

/*
Ԯ����Ϣ��
eventid:�¼�id
userid:Ԯ���ߵ��û�id
username:Ԯ���ߵ��û���
content:Ԯ����Ϣ����
time:����Ԯ����Ϣ��ʱ��
*/
CREATE TABLE tbl_aid (
eventid INTEGER,
userid INTEGER,
username TEXT,
content TEXT,
time TEXT
);

/*
ͷ���
userid:�û�id
path:ͷ���ļ��洢·��
version:ͷ��汾��
*/
CREATE TABLE tbl_avatar (
userid INTEGER,
path TEXT,
version INTEGER
);

/*
���ѱ�
id:����id
username:�û���
info:�û����
group:����(0-��������1-���ˣ�2-���ѣ�3-İ����)
*/
CREATE TABLE tbl_friends (
id INTEGER PRIMARY KEY AUTOINCREMENT,
userid INTEGER,
username TEXT,
info TEXT,
group INTEGER
);