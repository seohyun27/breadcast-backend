INSERT INTO bakery (bakery_id, bakery_name, address, phone, latitude, longitude, url, photo1, photo2)
VALUES (1, '그리다빵', '경상북도 경산시 조영동 583-1', '0538182788', 35.8386542860074, 128.753310857291, 'https://www.instagram.com/gridabread/', 'https://breadcast-images.s3.ap-northeast-2.amazonaws.com/bakery_1.png', '');

INSERT INTO menu (menu_id, menu_name, price, inform, menu_photo, bakery_id)
VALUES (1, '그리다빵', 3700, '복숭아과즙이 콕콕 씹히는 100% 우유생크림빵 그리다빵 인기생크림빵', '', 1);
INSERT INTO menu (menu_id, menu_name, price, inform, menu_photo, bakery_id)
VALUES (2, '말차크림빵', 4200, '제주산말차가루로 만든 빵과 매장에서 직접 끓여 만든 쌉쌀한 제주말차크림 듬뿍', '', 1);
INSERT INTO menu (menu_id, menu_name, price, inform, menu_photo, bakery_id)
VALUES (3, '시오키', 3000, '쫄깃한 소금빵 위에 바삭한 쿠키 도우', '', 1);
INSERT INTO menu (menu_id, menu_name, price, inform, menu_photo, bakery_id)
VALUES (4, '딸기 타르트', 7200, '크림치즈와 우유생크림으로 만든 인기타르트', '', 1);

