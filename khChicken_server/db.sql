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
values ('도현', 'dohyeon', 'dh', 3, '모란역', '010-1234-5678');

select * from member;

CREATE TABLE MENU(
    MENU_NAME VARCHAR2(30) PRIMARY KEY,
    PRICE NUMBER(5) NOT NULL,
    MENU_TYPE VARCHAR2(10) NOT NULL,
    DESCRIPTION VARCHAR2(1000) NOT NULL,
    AVG_RATE NUMBER(2)
);

INSERT INTO MENU VALUES ('후라이드 치킨', 11000, '메인', '바삭바삭 맛있는 후라이드 치킨입니다. 매일 신선한 기름으로 튀겨냅니다', 10);
INSERT INTO MENU VALUES ('양념 치킨', 12000, '메인', '매콤달콤 양념치킨', 9);
INSERT INTO MENU VALUES ('반반 치킨', 12000, '메인', '싸우지 말고 반반치킨', 10);
INSERT INTO MENU VALUES ('케이준 치킨 샐러드', 5000, '사이드', '치킨엔 치킨샐러드지', 7.9);
INSERT INTO MENU VALUES ('감자튀김', 2000, '사이드', '언제 먹어도 맛있는 감자튀김', 8.6);
INSERT INTO MENU VALUES ('치즈스틱', 3800, '사이드', '치즈는 진리', 9.5);
INSERT INTO MENU VALUES ('해쉬 브라운', 6000, '사이드', '싸고 양 많은 해쉬 브라운', 9.5);
INSERT INTO MENU VALUES ('콘 샐러드', 2400, '사이드', '옥수수 맛있어', 8);
INSERT INTO MENU (MENU_NAME, PRICE, MENU_TYPE, DESCRIPTION) VALUES ('콜라', 3000, '음료', '음료는 콜라');
INSERT INTO MENU (MENU_NAME, PRICE, MENU_TYPE, DESCRIPTION) VALUES ('사이다', 3000, '음료', '음료는 사이다');
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


INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);

INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛없음', 1);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '그냥그냥 먹을만 함', 7);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '최고', 10);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '해쉬 브라운', 1, 0, sysdate, '비쌈', 7);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '콜라', 1, 0, sysdate, null , null);
   
SELECT * FROM ORDER_DETAIL;

