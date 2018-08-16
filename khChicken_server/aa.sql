select * from order_detail;

delete REVIEW from ORDER_DETAIL WHERE ID = 'dohyeon' AND MENU_NAME = '양념 치킨' ;

UPDATE order_detail  
SET REVIEW = '맛있어요', SCORE = 5  
WHERE ID = 'dohyeon' AND MENU_NAME = '양념 치킨' 
AND ((TO_DATE(sysdate, 'yyyy/mm/dd') - TO_DATE(ORDER_DATE, 'yyyy/mm/dd'))) <= 2;

