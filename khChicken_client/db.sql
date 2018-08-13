drop table menu
cascade constraints;

drop table member
cascade constraints;

drop table order_detail
cascade constraints;
CREATE TABLE MEMBER(
    NAME VARCHAR2(20) NOT NULL,
    ID VARCHAR2(10) PRIMARY KEY,
    PW VARCHAR2(20) NOT NULL,
    USEDCOUPON NUMBER(2),
    AUTH NUMBER(1) NOT NULL,
    ADR VARCHAR2(50) NOT NULL,
    PHONE VARCHAR2(20) NOT NULL
);

insert into member (name,id,pw,auth,adr,phone)
values ('admin', 'admin', 'admin', 1, 'KH CHICKEN', '010-0000-0000');

insert into member (name,id,pw,auth,adr,phone)
values ('�룄�쁽', 'dohyeon', 'dh', 3, '紐⑤��뿭', '010-1234-5678');

select * from member;

CREATE TABLE MENU(
    MENU_NAME VARCHAR2(30) PRIMARY KEY,
    PRICE NUMBER(5) NOT NULL,
    MENU_TYPE VARCHAR2(10) NOT NULL,
    DESCRIPTION VARCHAR2(1000) NOT NULL,
    AVG_RATE NUMBER(2)
);


SELECT *
FROM MENU
INSERT INTO MENU VALUES ('�썑�씪�씠�뱶 移섑궓', 11000, '硫붿씤', '諛붿궘諛붿궘 留쏆엳�뒗 �썑�씪�씠�뱶 移섑궓�엯�땲�떎. 留ㅼ씪 �떊�꽑�븳 湲곕쫫�쑝濡� ��寃⑤깄�땲�떎', 10);
INSERT INTO MENU VALUES ('�뼇�뀗 移섑궓', 12000, '硫붿씤', '留ㅼ숴�떖肄� �뼇�뀗移섑궓', 9);
INSERT INTO MENU VALUES ('諛섎컲 移섑궓', 12000, '硫붿씤', '�떥�슦吏� 留먭퀬 諛섎컲移섑궓', 10);
INSERT INTO MENU VALUES ('耳��씠以� 移섑궓 �깘�윭�뱶', 5000, '�궗�씠�뱶', '移섑궓�뿏 移섑궓�깘�윭�뱶吏�', 7.9);
INSERT INTO MENU VALUES ('媛먯옄��源�', 2000, '�궗�씠�뱶', '�뼵�젣 癒뱀뼱�룄 留쏆엳�뒗 媛먯옄��源�', 8.6);
INSERT INTO MENU VALUES ('移섏쫰�뒪�떛', 3800, '�궗�씠�뱶', '移섏쫰�뒗 吏꾨━', 9.5);
INSERT INTO MENU VALUES ('�빐�돩 釉뚮씪�슫', 6000, '�궗�씠�뱶', '�떥怨� �뼇 留롮� �빐�돩 釉뚮씪�슫', 9.5);
INSERT INTO MENU VALUES ('肄� �깘�윭�뱶', 2400, '�궗�씠�뱶', '�삦�닔�닔 留쏆엳�뼱', 8);
INSERT INTO MENU (MENU_NAME, PRICE, MENU_TYPE, DESCRIPTION) VALUES ('肄쒕씪', 3000, '�쓬猷�', '�쓬猷뚮뒗 肄쒕씪');
INSERT INTO MENU (MENU_NAME, PRICE, MENU_TYPE, DESCRIPTION) VALUES ('�궗�씠�떎', 3000, '�쓬猷�', '�쓬猷뚮뒗 �궗�씠�떎');
select * from Menu;

CREATE TABLE ORDER_DETAIL(
    ID VARCHAR2(10),
    MENU_NAME VARCHAR2(30),
    COUNT NUMBER(10) NOT NULL,
    BEV_COUPON NUMBER(3),
    ORDER_DATE DATE NOT NULL,
    REVIEW VARCHAR2(1000),
    SCORE NUMBER(5),
    CONSTRAINT FK_ID FOREIGN KEY(ID)
    REFERENCES MEMBER(ID),
    CONSTRAINT FK_MENU FOREIGN KEY(MENU_NAME)
    REFERENCES MENU(MENU_NAME)
);



ALTER TABLE ORDER_DETAIL
RENAME COLUMN COUNT TO COUNTS;


INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '留쏆엳�쓬', 9);

INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '留쏆뾾�쓬', 1);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '洹몃깷洹몃깷 癒뱀쓣留� �븿', 7);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '理쒓퀬', 10);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '留쏆엳�쓬', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '留쏆엳�쓬', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '留쏆엳�쓬', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '留쏆엳�쓬', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '留쏆엳�쓬', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�썑�씪�씠�뱶 移섑궓', 1, 0, sysdate, '留쏆엳�쓬', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '�빐�돩 釉뚮씪�슫', 1, 0, sysdate, '鍮꾩뙂', 7);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '肄쒕씪', 1, 0, sysdate, null , null);
   
SELECT * FROM ORDER_DETAIL;



CREATE TABLE LOADNAME_ADD(
	LOAD VARCHAR2(80),	
	SIDO VARCHAR2(20),
	SIGUNGU VARCHAR2(20),
	EUBMEONDONG VARCHAR2(20)
);

);

