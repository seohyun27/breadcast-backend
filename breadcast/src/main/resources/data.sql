-- 1. 빵집(Bakery) 정보 삽입
-- (Menu 테이블이 bakery_id를 참조하므로 먼저 삽입)
INSERT INTO bakery (bakery_id, bakery_name, address, phone, latitude, longitude, url, photo1, photo2)
VALUES (1, '성심당 본점', '대전광역시 중구 대종로480번길 15', '1588-8069', 36.327470, 127.427210, 'http://www.sungsimdang.co.kr', 'images/sungsimdang_main.jpg', 'images/sungsimdang_sub.jpg');

-- 2. 빵(Bread) 종류 정보 삽입
-- (Classfy 테이블이 bread_id를 참조하므로 먼저 삽입)
INSERT INTO bread (bread_id, bread_name, category)
VALUES (1, '튀김소보로', '시그니처');
INSERT INTO bread (bread_id, bread_name, category)
VALUES (2, '판타롱부추빵', '시그니처');
INSERT INTO bread (bread_id, bread_name, category)
VALUES (3, '명란바게트', '하드계열');

-- 3. 메뉴(Menu) 정보 삽입
-- (bakery_id = 1 (성심당)을 참조)
INSERT INTO menu (menu_id, menu_name, price, inform, menu_photo, bakery_id)
VALUES (1, '튀김소보로', 1700, '바삭한 소보로와 달콤한 팥앙금의 조화', 'images/menu_fried_soboro.jpg', 1);
INSERT INTO menu (menu_id, menu_name, price, inform, menu_photo, bakery_id)
VALUES (2, '판타롱부추빵', 2000, '향긋한 부추와 햄이 가득 들어간 건강빵', 'images/menu_buchu.jpg', 1);
INSERT INTO menu (menu_id, menu_name, price, inform, menu_photo, bakery_id)
VALUES (3, '명란바게트', 4300, '짭조름한 명란 소스가 듬뿍 발린 바게트', 'images/menu_myeongran.jpg', 1);

-- 4. 분류(Classfy) 정보 삽입 (Menu와 Bread 관계 매핑)
-- (menu_id = 1 (튀김소보로 메뉴) -> bread_id = 1 (튀김소보로 빵))
INSERT INTO classfy (classfy_id, menu_id, bread_id)
VALUES (1, 1, 1);
-- (menu_id = 2 (부추빵 메뉴) -> bread_id = 2 (부추빵))
INSERT INTO classfy (classfy_id, menu_id, bread_id)
VALUES (2, 2, 2);
-- (menu_id = 3 (명란바게트 메뉴) -> bread_id = 3 (명란바게트 빵))
INSERT INTO classfy (classfy_id, menu_id, bread_id)
VALUES (3, 3, 3);