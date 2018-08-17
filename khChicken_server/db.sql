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


insert into member (name,id,pw,auth,adr,phone)
values ('도현', 'dohyeon', 'dh', 3, '서울특별시 종로구 효자동', '010-1234-5678');
insert into member (name,id,pw,auth,adr,phone)
values ('승지', 'seungji', 'sj', 3, '경기도 부천시 고강동', '010-7639-3949');
insert into member (name,id,pw,auth,adr,phone)
values ('다슬', 'dasul', 'ds', 3, '부산광역시 사상구 괘법동', '010-2345-6789');
insert into member (name,id,pw,auth,adr,phone)
values ('진영', 'jinyeong', 'jy', 3, '경상북도 의성군 의성읍', '010-5353-8282');
insert into member (name,id,pw,auth,adr,phone)
values ('상필', 'sangpil', 'sp', 3, '강원도 화천군 사내면', '010-4974-1515');

select * from member;
update MEMBER SET USEDCOUPON = 1 WHERE ID = 'dohyeon';

ALTER TABLE MEMBER MODIFY (
	ADR VARCHAR2(100)
);

CREATE TABLE MENU(
    MENU_NAME VARCHAR2(30) PRIMARY KEY,
    PRICE NUMBER(5) NOT NULL,
    MENU_TYPE VARCHAR2(10) NOT NULL,
    DESCRIPTION VARCHAR2(1000) NOT NULL,
    AVG_RATE NUMBER(2)
);


DROP TABLE MENU

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
    COUNTS NUMBER(10) NOT NULL,
    BEV_COUPON NUMBER(3),
    ORDER_DATE DATE NOT NULL,
    REVIEW VARCHAR2(1000),
    SCORE NUMBER(5),
    CONSTRAINT FK_ID FOREIGN KEY(ID)
    REFERENCES MEMBER(ID),
    CONSTRAINT FK_MENU FOREIGN KEY(MENU_NAME)
    REFERENCES MENU(MENU_NAME)
);

SELECT * FROM ORDER_DETAIL;


DROP TABLE ORDER_DETAIL

ALTER TABLE ORDER_DETAIL
RENAME COLUMN COUNT TO COUNTS;

INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);

INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛없음', 1);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '그냥그냥 먹을만 함', 7);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '최고', 10);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, sysdate, '맛있음', 9);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '해쉬 브라운', 1, 0, sysdate, '비쌈', 7);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '콜라', 1, 0, sysdate, null , null);
 ID VARCHAR2(10),
    MENU_NAME VARCHAR2(30),
    COUNTS NUMBER(10) NOT NULL,
    BEV_COUPON NUMBER(3),
    ORDER_DATE DATE NOT NULL,
    REVIEW VARCHAR2(1000),
    SCORE NUMBER(5),


   
SELECT * FROM ORDER_DETAIL;




-- 날짜순
SELECT DISTINCT A.ORDER_DATE, A.ID, B.MENU_TYPE,  A.MENU_NAME, B.PRICE, A.COUNTS, A.BEV_COUPON, B.PRICE
FROM ORDER_DETAIL A, MENU B
WHERE A.MENU_NAME = B.MENU_NAME
ORDER BY A.ORDER_DATE DESC;


-- 매출순
SELECT b.menu_type, A.menu_name, B.price, A.판매량, A.사용쿠폰, (B.PRICE*A.판매량) 총판매액
FROM (SELECT 정렬.menu_name , 정렬.판매량 , 정렬.쿠폰 사용쿠폰
FROM(SELECT menu_name , SUM(counts) 판매량, SUM(BEV_COUPON) 쿠폰
FROM ORDER_DETAIL
GROUP BY menu_name) 정렬) A, MENU B
WHERE A.menu_name = B.MENU_NAME
ORDER BY (B.PRICE*A.판매량) DESC;

-- 별점순
SELECT b.menu_type, A.menu_name, B.price, A.판매량, A.사용쿠폰, (B.PRICE*A.판매량) 총판매액, A.별점
FROM (SELECT 정렬.menu_name , 정렬.판매량 , 정렬.쿠폰 사용쿠폰, 정렬.별점
FROM(SELECT menu_name , SUM(counts) 판매량, SUM(BEV_COUPON) 쿠폰, ROUND(AVG(SCORE), 2) 별점
FROM ORDER_DETAIL
GROUP BY menu_name
HAVING AVG(SCORE) IS NOT NULL) 정렬) A, MENU B
WHERE A.menu_name = B.MENU_NAME
ORDER BY A.별점 DESC;

-- 고객관리
SELECT A.ID, A.NAME, A.ADR, A.PHONE, B.주문건수
FROM MEMBER A,
(SELECT A.ID, COUNT(*) 주문건수
FROM ORDER_DETAIL A, MENU B
WHERE A.MENU_NAME = B.MENU_NAME
AND B.MENU_TYPE = '메인'
GROUP BY ID
ORDER BY COUNT(*) DESC) B
WHERE A.ID = B.ID;


UPDATE ORDER_DETAIL SET REVIEW = null, SCORE = null 
WHERE ID = 'dohyeon' AND MENU_NAME = '반반 치킨' AND ORDER_DATE = TO_DATE('2018-08-15 02:01:05','YYYY-MM-DD HH:MI:SS');

SELECT * FROM ORDER_DETAIL;
select to_date('2018-08-15 02:01:05', 'yyyy-mm-dd hh:mi:ss') from dual;
SELECT TO_CHAR(ORDER_DATE,'YYYY-MM-DD HH:MI:SS') FROM ORDER_DETAIL


INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '후라이드 치킨', 1, 0, TO_DATE('20161125134554','YYYYMMDDHH24MISS'), '튀김이 바삭하니 맛있네요~ 강추강추', 9);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '후라이드 치킨', 3, 0, TO_DATE('20161225233000','YYYYMMDDHH24MISS'), '새로 생긴 곳인가봐요 사장님이 친절하셔서 좋네요^^', 10);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '케이준 치킨 샐러드', 1, 0, TO_DATE('20161225233000','YYYYMMDDHH24MISS'), '사이드는 제 취항이 아니네요ㅠㅠ 아쉽', 2);
INSERT INTO ORDER_DETAIL VALUES ('dasul', '양념 치킨', 2, 0, TO_DATE('20170101152312','YYYYMMDDHH24MISS'), '양념치킨 후기가 없길래 복불복으로 도전했는데 취향저격 성공!!! 크으으... 배달이 좀 느려서 2점 깎았어요', 8);
INSERT INTO ORDER_DETAIL VALUES ('dasul', '콜라', 2, 1, TO_DATE('20170101152312','YYYYMMDDHH24MISS'), NULL, NULL);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '양념 치킨', 4, 0, TO_DATE('20170227183559','YYYYMMDDHH24MISS'), '저번에 시키고 오늘 또시켰습니다 빠른배달 굿~', 7);
INSERT INTO ORDER_DETAIL VALUES ('jinyeong', '반반 치킨', 1, 0, TO_DATE('20170530222405','YYYYMMDDHH24MISS'), '좋은 기름 쓰는것 같아서 맛있게 잘 먹었어요. 여러분 역시 치킨은 반반입니다...ㅎㅎㅎ', 9);
INSERT INTO ORDER_DETAIL VALUES ('jinyeong', '해쉬 브라운', 2, 0, TO_DATE('20170530222405','YYYYMMDDHH24MISS'), '이마트에서 파는 해쉬브라운이랑 맛 똑같아요 최애 메뉴 등극각ㅠㅠ', 10);
INSERT INTO ORDER_DETAIL VALUES ('jinyeong', '사이다', 1, 1, TO_DATE('20170530222405','YYYYMMDDHH24MISS'), NULL, NULL);
INSERT INTO ORDER_DETAIL VALUES ('sangpil', '반반 치킨', 5, 0, TO_DATE('20170610235504','YYYYMMDDHH24MISS'), '집에 친구들 놀러와서 야식 시켰는데 5마리로 6명이 배부르게 먹었습니다~ 근데 무를 빠트리셨더라구요ㅜ 아쉬웠습니다', 6);
INSERT INTO ORDER_DETAIL VALUES ('sangpil', '감자튀김', 4, 0, TO_DATE('20170610235504','YYYYMMDDHH24MISS'), '감자가 큼직큼직 썰려있어서 씹는맛이 있네요 엄청 맛있습니다 치킨보다 이게 더 나은듯?', 7);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '양념 치킨', 1, 0, TO_DATE('20170726104504','YYYYMMDDHH24MISS'), '아침부터 치킨이 땡겨서 참지못하고 그만..크흡.... 맛있으면 0칼로리 양념치킨 0칼로리' , 10);
INSERT INTO ORDER_DETAIL VALUES ('sangpil', '후라이드 치킨', 2, 0, TO_DATE('20170831095821','YYYYMMDDHH24MISS'), '바-삭! 역시 진리의 후라이드ㅋㅋ 튀김옷 진짜 제대롭니다. 앞으로도 여기서만 시켜먹을거에요. 첫가입 쿠폰으로 음료쿠폰 주시네요 감사합니다', 10);
INSERT INTO ORDER_DETAIL VALUES ('sangpil', '콜라', 1, 1, TO_DATE('20170831095821','YYYYMMDDHH24MISS'), null , null);
INSERT INTO ORDER_DETAIL VALUES ('dasul', '반반 치킨', 2, 0, TO_DATE('20170925203139','YYYYMMDDHH24MISS'), '퇴근하자마자 시켰어요. 다른 후기처럼 양이 많네요 닭 큰거 쓰시는듯ㅎㅎ 1인가구에는 좀 많네요', 6);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '후라이드 치킨', 2, 0, TO_DATE('20170915194736','YYYYMMDDHH24MISS'), '후라이드, 양념 둘다 먹어봤는데 후라이드가 좀더 입맛에 맞네요. 그리고 앞서 후기3개 썼더니 음료쿠폰이 들어와있어서 기분 급상승ㅋㅋ' , 8);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '치즈스틱', 10, 0, TO_DATE('20170915194736','YYYYMMDDHH24MISS'), '치즈가 진심 쭉쭉 늘어납니다. 모짜렐라 덕후라 쟁여두고 먹을라고 한번에 10개나 시켰어요 ㅋㅋ 후회안함' , 10);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '사이다', 3, 1, TO_DATE('20170915194736','YYYYMMDDHH24MISS'), NULL, NULL);
INSERT INTO ORDER_DETAIL VALUES ('jinyeong', '반반 치킨', 1, 0, TO_DATE('20171025173001','YYYYMMDDHH24MISS'), '하나 꽂히면 그것만 조지는 스타일이라 이번에도 역시나 반반시켰습니다. 여러분 반반드세요!!!', 10);
INSERT INTO ORDER_DETAIL VALUES ('jinyeong', '콘샐러드', 1, 0, TO_DATE('20171025173001','YYYYMMDDHH24MISS'), '콘이 흐물흐물 합니다 ㅠㅠ 별루데스...', 5);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '양념 치킨', 2, 0, TO_DATE('20171225212659','YYYYMMDDHH24MISS'), '크리스마스라 닭 2마리 시켜서 셀프선물 했습니다. 연말이라 주문이 많이 밀렸나봐요.. 그와중에 맛있네요... ', 7);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '치즈스틱', 3, 0, TO_DATE('20171225212659','YYYYMMDDHH24MISS'), '치즈는 제게 조금 느끼하네요... 늬끼늬끼 먹기 힘들었습니다', 4);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '콜라', 1, 0, TO_DATE('20171225212659','YYYYMMDDHH24MISS'), NULL, NULL);
INSERT INTO ORDER_DETAIL VALUES ('sangpil', '후라이드 치킨', 2, 0, TO_DATE('20180116085400','YYYYMMDDHH24MISS'), '후라이드 최고존엄 존맛탱 JMT인정따리인정따', 8);
INSERT INTO ORDER_DETAIL VALUES ('sangpil', '케이준 치킨 샐러드', 1, 0, TO_DATE('20180116085400','YYYYMMDDHH24MISS'), '치킨위에 샐러드 올려먹으니까 건강식같아졌어요 소스뭐죠 맛있네요', 9);
INSERT INTO ORDER_DETAIL VALUES ('sangpil', '사이다', 3, 1, TO_DATE('20180116085400','YYYYMMDDHH24MISS'), NULL, NULL);
INSERT INTO ORDER_DETAIL VALUES ('dasul', '반반 치킨', 1, 0, TO_DATE('20180220160024','YYYYMMDDHH24MISS'), '저번보다 양념은 조금 더 매워진거 같아요 제가 매운걸 진짜 못먹는데 ㅠㅠ 그것만 보완하시면 좋을것 같아요 잘먹었습니다', 6);
INSERT INTO ORDER_DETAIL VALUES ('dasul', '사이다', 1, 0, TO_DATE('20180220160024','YYYYMMDDHH24MISS'), NULL, NULL);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '반반 치킨', 1, 0, TO_DATE('20180310223059','YYYYMMDDHH24MISS'), '반반은 처음 먹어보는데 맛있네요. 양념이 제 입맛에 딱입니다 여기 밥비벼먹으면 존맛일듯', 10);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '후라이드 치킨', 1, 0, TO_DATE('20180310223059','YYYYMMDDHH24MISS'), '제가 워낙 후라이드를 좋아해서 반반이랑 같이 시켰어요 근데 왜 반반보다 후라이드가 더 닭크기가 작아보일까요... 흠', 8);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '해쉬 브라운', 3, 0, TO_DATE('20180310223059','YYYYMMDDHH24MISS'), '감자가 포슬포슬 ㅠㅠ 김나는 상태로 허버허버거리면서 먹음', 7);
INSERT INTO ORDER_DETAIL VALUES ('seungji', '콜라', 1, 0, TO_DATE('20180310223059','YYYYMMDDHH24MISS'), NULL, NULL);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '반반 치킨', 3, 0, TO_DATE('20180520161200','YYYYMMDDHH24MISS'), '결정장애가 도지는바람에... 반반시켰는데 맛있네요 근데 역시 후라이드에 더 손이 갑니다', 10);
INSERT INTO ORDER_DETAIL VALUES ('dohyeon', '감자튀김', 1, 0, TO_DATE('20180520161200','YYYYMMDDHH24MISS'), '감자 찍어먹는 소스가 맛있어서 치킨도 여기 찍어먹으면 맛있어요', 9);

select * from order_detail;

UPDATE ORDER_DETAIL  SET REVIEW = 'asdf', SCORE = 6
WHERE ID = 'seungji' AND MENU_NAME = '후라이드 치킨' AND ORDER_DATE = TO_DATE('2018-08-15 05:42:25','YYYY-MM-DD HH:MI:SS')
SELECT ID, MENU_NAME, TO_CHAR(ORDER_DATE, 'YYYY-MM-DD HH24:MI:SS'), REVIEW, SCORE 
FROM ORDER_DETAIL WHERE MENU_NAME = '후라이드 치킨', ID = 'seungji', AND REVIEW NULL