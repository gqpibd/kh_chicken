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


INSERT INTO MEMBER (name, id, pw, USEDCOUPON, auth, adr, phone) VALUES ( 'ddd', '1', '1', 0, 0, '1', '1' );


 INSERT INTO MEMBER (ID, MENU_NAME, COUNT1, BEV_COUPON, REVIEW, SCORE ) 
 VALUES ( '윤상필', 'null', 0, 0, '히히히히 맛있어요', 1 )

select * from ORDER_DETAIL;



SELECT ID, MENU_NAME, COUNT1, BEV_COUPON, ORDER_DATE, REVIEW, SCORE 
FROM ORDER_DETAIL
CREATE TABLE MENU(
    MENU_NAME VARCHAR2(30) PRIMARY KEY,
    PRICE NUMBER(5) NOT NULL,
    MENU_TYPE VARCHAR2(10) NOT NULL,
    DESCRIPTION VARCHAR2(1000) NOT NULL,
    AVG_RATE NUMBER(2)
);


CREATE TABLE ORDER_DETAIL(
    ID VARCHAR2(10),
    MENU_NAME VARCHAR2(30),
    COUNT1 NUMBER(10) NOT NULL,
    BEV_COUPON NUMBER(3),
    ORDER_DATE DATE NOT NULL,
    REVIEW VARCHAR2(1000),
    SCORE NUMBER(5)
);

 INSERT INTO ORDER_DETAIL (ID, MENU_NAME, COUNT1, BEV_COUPON, ORDER_DATE,REVIEW, SCORE )
 VALUES ( '윤상필', 'null', 0, 0, TO_DATE(sysdate , 'yyyy/mm/dd') , '히히히히 맛있어요', 1 )

SELECT ID, MENU_NAME, COUNT1, BEV_COUPON, ORDER_DATE, REVIEW, SCORE 
INSERT INTO ORDER_DETAIL (ID, MENU_NAME, COUNT1, BEV_COUPON, ORDER_DATE, REVIEW, SCORE)
VALUES('88', 'd', 1, 2, sysdate, '짱짱 맛qqzzz있어요', 5)


    NAME VARCHAR2(20) NOT NULL,
    ID VARCHAR2(10) PRIMARY KEY,
    PW VARCHAR2(20) NOT NULL,
    USEDCOUPON NUMBER(2),
    AUTH NUMBER(1) NOT NULL,
    ADR VARCHAR2(50) NOT NULL,
    PHONE VARCHAR2(20) NOT NULL


INSERT INTO MEMBER (name, id, pw, USEDCOUPON, auth, adr, phone) 
VALUES ( '111', '된다', '1111', 0, 0, '111', '11' );
 INSERT INTO ORDER_DETAIL (ID, MENU_NAME, COUNT1, BEV_COUPON, ORDER_DATE,REVIEW, SCORE ) 
 VALUES ( '윤상필', 'null', 0, 0, TO_DATE(sysdate , 'yyyy/mm/dd'), '히히히히 맛있어요', 1 )

SELECT * FROM ORDER_DETAIL;
 
DROP TABLE MEMBER
DROP TABLE ORDER_DETAIL

ALTER TABLE ORDER_DETAIL
DROP CONSTRAINT PK_ID

ALTER TABLE ORDER_DETAIL
DROP CONSTRAINT FK_ID

ALTER TABLE ORDER_DETAIL
ADD CONSTRAINT FK_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);

CONSTRAINT MEMBER_ID 
FOREIGN KEY ID 
REFERENCES MEMBER ID

alter table MEMBER
drop primary key








