select * from order_detail;

UPDATE order_detail  
SET REVIEW = '맛있어요', SCORE = 5  
WHERE ID = 'dohy' AND MENU_NAME = '양념 치킨' AND ORDER_DATE =TO_DATE(sysdate - TO_DATE(ORDER_DATE,'YYYY-MM-DD HH:MI:SS') < 2;

