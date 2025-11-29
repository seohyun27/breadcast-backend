INSERT INTO bakery (bakery_name, bakery_text, address, phone, latitude, longitude, url, photo1, photo2, operating_hours)
VALUES ( '그리다빵 영남대본점', '편안함이 있는 베이커리 공간',
        '경상북도 경산시 조영동 583-1','0507-1401-2788', 35.8386542860074, 128.753310857291,
        'https://www.instagram.com/gridabread/', '', '', '9:00 - 23:00');

INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('그리다빵', 3700, '복숭아과즙이 콕콕 씹히는 100% 우유생크림빵 그리다빵 인기생크림빵', '', 1);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('말차크림빵', 4200, '제주산말차가루로 만든 빵과 매장에서 직접 끓여 만든 쌉쌀한 제주말차크림 듬뿍', '', 1);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('시오키', 3000, '쫄깃한 소금빵 위에 바삭한 쿠키 도우', '', 1);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('딸기 타르트', 7200, '크림치즈와 우유생크림으로 만든 인기타르트', '', 1);



INSERT INTO bakery (bakery_name, bakery_text, address, phone, latitude, longitude, url, photo1, photo2, operating_hours)
VALUES ('코너커피', '휘낭시에 맛집으로 소문난 감성 카페',
        '경북 경산시 청운로 31 (조영동) 1층 코너커피', '0507-1347-2477', 35.8382196131202, 128.754191608447,
        'https://www.instagram.com/cornercake.house', '', '', '12:00 - 24:00');

INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('플레인휘낭시에', 2900, '기본이 맛있어야 된다, 그 기본에 충실했습니다 버터의 풍미가 가아득한 플레인 휘낭시에', '', 2);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('레몬마들렌', 3200, '레몬아이싱이 전체적으로 입혀져 폭신하고 상큼하고 촉촉해요 인기 짱 ++', '', 2);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('사각바스크치즈케이크', 6500, 'No밀가루 / 끼리크림치즈를 듬뿍 넣어 만들었어요:) 꾸덕하고 촉촉하고 부드러운 완벽한 수제 바스크치즈케이크', '', 2);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('쫀득 크루키', 6500, '[크로와상 겉과 속에 쿠키반죽을 넣어 겉은 바삭 속은 쫀~득] : 깔리바우트 고급초콜릿이 듬뿍 들어가있어요 , 크루아상 안의 쿠키반죽은 촉촉해요 :)', '', 2);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('에그타르트', 3300, '+인기메뉴+ 필링가득한 포르투갈식 에그타르트 / 재료값 인상으로 가격이 인상된 점 양해부탁드립니다 ㅠㅁㅠ', '', 2);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('초코버터크로플', 4500, '크로플 위에 초코소스와 쿠키크럼블이 듬뿍 뿌려드려요요 :)', '', 2);



INSERT INTO bakery (bakery_id, bakery_name, bakery_text, address, phone, latitude, longitude, url, photo1, photo2, operating_hours)
VALUES (3, '밀로우', '딸기 크레이프 케이크의 부드러운 매력',
        '경북 경산시 청운로 57-5 1층', '0507-1487-7028', 35.8399169164116, 128.755842329982,
        'https://www.instagram.com/meal.low', '', '', '18:00 - 22:00');

INSERT INTO menu ( menu_name, price, inform, menu_photo, bakery_id)
VALUES ('후르츠크레이프', 9800, '바닐라빈이들어간 디플로마크림에 제철과일 4-5가지가 들오가는 후르츠크레이프', '', 3);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('프렌치토스트', 10000, '직접 구운 브리오슈를 아파레유에 30시간 이상 숙성 후 버터에 구워낸 겉바속촉 토스트', '', 3);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('조각타르트/케이크', 7000, '종류에 따라 가격이 달라요!', '', 3);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('밀로우크로플', 11000, '잠자는 곰돌이아이스크림이 올라갑니다. 바닐라or초코 중 선택', '', 3);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('캐릭터마들렌', 6500, '레몬, 초코마들렌 구성', '', 3);



INSERT INTO bakery (bakery_name, bakery_text, address, phone, latitude, longitude, url, photo1, photo2, operating_hours)
VALUES ('CAFE101', '친절한 사장님이 맞이하는 편안한 공간',
        '경북 경산시 청운로 30 2층', '0507-1303-7673', 35.8379255199456, 128.754232497484,
        'https://www.instagram.com/cafe101_official', '', '', '10:00 - 23:00');

INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('생과일 홍콩에그와플', 9900, '(에그와플+생크림)+생과일', '', 4);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('101 수제 크로플', 8900, '크로플3EA + (생크림+메이플시럽+바닐라아이스크림)', '', 4);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('브뤼셀 와플 + 생크림(메이플와플)', 8500, '주문즉시 바로 구워서 나가요ლ(´ڡ`ლ)', '', 4);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ( '브뤼셀 와플 + 생크림(누텔라와플)', 8500, '주문즉시 바로 구워서 나가요ლ(´ڡ`ლ)', '', 4);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('브뤼셀 와플 + 생크림(메이플/누텔라 반반 와플)', 8500, '주문즉시 바로 구워서 나가요ლ(´ڡ`ლ)', '', 4);



INSERT INTO bakery (bakery_name, bakery_text, address, phone, latitude, longitude, url, photo1, photo2, operating_hours)
VALUES ('위켄드프로젝트', '케이크전문',
        '경북 경산시 조영길 9 1층 101호,102호', '0507-1354-2210', 35.8382175486755, 128.757175595772,
        'https://www.instagram.com/weekend_project_official', '', '', '12:00 - 19:00');

INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('계절 생과일 케이크', 7000, '수제 바닐라 케이크 시트+신선한 계절 과일+고소한 우유 크림 or 치즈생크림', '', 5);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('갸또 쇼콜라', 6500, '벨기에 커버춰 초코를 넣어 만든 꾸덕하고 풍미깊은 갸또쇼콜라. 올라가는 크림이 킥입니다.', '', 5);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('치즈 케이크', 6500, '원물을 듬뿍 넣어 만든 부드러운 치즈 케이크. 데코는 조금씩 달라질 수 있습니다.', '', 5);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('치즈 케이크', 5000, '고급 식빵 + 신선한 과일 + 마스카포네 우유 생크림 or 요거트 생크림', '', 5);



INSERT INTO bakery (bakery_name, bakery_text, address, phone, latitude, longitude, url, photo1, photo2, operating_hours)
VALUES ('사오 경산점', '크림 가득 담긴 크로칸슈의 유혹',
        '경북 경산시 대학로 285 1층', '0507-1367-2796', 35.8367881680931, 128.753352672328,
        '', '', '', '12:00 - 23:00');

INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('크로칸슈', 3000, '바삭한 슈에 고소하고 달달한 커스터드 크림이 가득', '', 6);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('카라멜 크로칸슈', 3000, '크로칸슈와 카라멜의 환상조화', '', 6);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('크로칸볼', 3000, '겉은 바삭하고 속은 부드러운 크로칸볼', '', 6);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('에그타르트', 2800, '기본에 충실한 에그타르트', '', 6);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('클래식스콘', 3200, '담백하고 바삭한 스콘', '', 6);



INSERT INTO bakery (bakery_name, bakery_text, address, phone, latitude, longitude, url, photo1, photo2, operating_hours)
VALUES ('오브픽', '예약으로 더 특별해지는 생일 케이크',
        '경북 경산시 계양로37길 16 1층 오브픽', '0507-1341-8421', 35.8322311993968, 128.747270708605,
        'https://www.instagram.com/ovpick_dessert', '', '', '11:00 - 19:00');

INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('초코 골든키위', 8500, '초코시트+100%동물성생크림+골든키위생과', '', 7);
INSERT INTO menu ( menu_name, price, inform, menu_photo, bakery_id)
VALUES ('순우유 망청', 8500, '바닐라시트+100%동물성생크림+망고생과+청포도생과', '', 7);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('샹티 망고', 8500, '바닐라시트+100%동물성생크림+마스카포네+생과일2단샌딩및데코', '', 7);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('초코 딸기', 8500, '초코시트+100%동물성생크림+과일2단샌딩및데코', '', 7);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('요거트생크림', 8800, '바닐라시트+무가당요거트+100%동물성생크림+직접조린퓨레+생과일2단샌딩 및 데코', '', 7);



INSERT INTO bakery (bakery_name, bakery_text, address, phone, latitude, longitude, url, photo1, photo2, operating_hours)
VALUES ('카페 브라우니', '요거트와플과 함께 즐기는 상큼한 하루',
        '경북 경산시 청운로 14-3', '053-817-6006', 35.8370351044826, 128.753301335946,
        '', '', '', '11:00 - 23:00');

INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('바닐라앤탑-두바이', 8000, '바삭달콤와플 + 피스타치오초콜릿스프레드 + 바닐라아이스크림 + 그래놀라', '', 8);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('로투스와플', 7000, '바삭달콤와플 + 로투스스프레드 + 바닐라아이스크림 + 로투스크럼블', '', 8);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('생복숭아와플', 13000, '바삭와플 + 생복숭아 + 요거트아이스크림 + 100%우유휘핑크림', '', 8);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('골드키위와플', 13000, '달콤한 골드키위 한가득 + 와플 + 100% 매일우유 휘핑 크림 + 요거트아이스크림', '', 8);
INSERT INTO menu (menu_name, price, inform, menu_photo, bakery_id)
VALUES ('요거트와플', 9000, '바삭달콤와플 + 100% 매일우유 휘핑 크림 + 요거트아이스크림 + 메이플시럽', '', 8);


