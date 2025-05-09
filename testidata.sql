USE mokkikodit;
-- MÖKIT
INSERT IGNORE INTO Mokki (sijainti, hinta, huoneita, huoneala, henkilo_maara, huomioitavaa) VALUES
('Levi', 150.00,3, 80, 6, 'Sisältää poreammeen'),
('Ylläs', 200.00, 5, 100, 8, NULL),
('Ruka', 180.00,3, 90, 6, 'Lemmikkieläimet sallittu'),
('Tahko', 160.00,4, 85, 5, NULL),
('Vuokatti', 140.00,5, 75, 4, 'Rantasauna käytettävissä'),
('Himos', 170.00,6, 95, 7, 'Sisältää ulkoporeammeen ja grillikatoksen'),
('Salla', 130.00,3, 70, 4, NULL),
('Pyhä', 155.00,4, 82, 5, 'Savuton mökki'),
('Iso-Syöte', 165.00,5, 88, 6, NULL),
('Koli', 175.00,7, 92, 7, 'Soveltuu etätyöhön – nopea nettiyhteys');

-- ASIAKKAAT: Yritykset
INSERT IGNORE INTO Asiakas (sahkoposti, asiakastyyppi) VALUES
('yritys1@example.com', 'yritys'),
('yritys2@example.com', 'yritys'),
('yritys3@example.com', 'yritys'),
('yritys4@example.com', 'yritys'),
('yritys5@example.com', 'yritys'),
('yritys6@example.com', 'yritys'),
('yritys7@example.com', 'yritys'),
('yritys8@example.com', 'yritys'),
('yritys9@example.com', 'yritys'),
('yritys10@example.com', 'yritys');

INSERT IGNORE INTO Yritysasiakas (sahkoposti, y_tunnus, puhelinnumero, osoite, nimi) VALUES
('yritys1@example.com', '1234567-1', '12341234', 'Yrityskatu 1, Helsinki', 'Yritys 1'),
('yritys2@example.com', '1234567-2','343523543', 'Yrityskatu 2, Espoo', 'Yritys 2'),
('yritys3@example.com', '1234567-3', '3423453456', 'Yrityskatu 3, Vantaa', 'Yritys 3'),
('yritys4@example.com', '1234567-4', '343462356', 'Yrityskatu 4, Turku', 'Yritys 4'),
('yritys5@example.com', '1234567-5', '3566426643', 'Yrityskatu 5, Tampere', 'Yritys 5'),
('yritys6@example.com', '1234567-6', '6676754325', 'Yrityskatu 6, Oulu', 'Yritys 6'),
('yritys7@example.com', '1234567-7', '34543464', 'Yrityskatu 7, Jyväskylä', 'Yritys 7'),
('yritys8@example.com', '1234567-8', '343454345', 'Yrityskatu 8, Kuopio', 'Yritys 8'),
('yritys9@example.com', '1234567-9', '3454344564', 'Yrityskatu 9, Lahti', 'Yritys 9'),
('yritys10@example.com', '1234567-10', '34546467787', 'Yrityskatu 10, Pori', 'Yritys 10');

-- ASIAKKAAT: Yksityiset
INSERT IGNORE INTO Asiakas (sahkoposti, asiakastyyppi) VALUES
('matti@example.com', 'yksityisasiakas'),
('maija@example.com', 'yksityisasiakas'),
('kalle@example.com', 'yksityisasiakas'),
('laura@example.com', 'yksityisasiakas'),
('antti@example.com', 'yksityisasiakas'),
('elina@example.com', 'yksityisasiakas'),
('jari@example.com', 'yksityisasiakas'),
('tiina@example.com', 'yksityisasiakas'),
('pekka@example.com', 'yksityisasiakas'),
('sari@example.com', 'yksityisasiakas');

INSERT IGNORE INTO Yksityisasiakas (sahkoposti, nimi, puhelinnumero, osoite) VALUES
('matti@example.com', 'Matti Meikäläinen', '0401234567', 'Katuosoite 1, Helsinki'),
('maija@example.com', 'Maija Virtanen', '0507654321', 'Katuosoite 2, Espoo'),
('kalle@example.com', 'Kalle Korhonen', '0412345678', 'Katuosoite 3, Vantaa'),
('laura@example.com', 'Laura Lahtinen', '0459876543', 'Katuosoite 4, Turku'),
('antti@example.com', 'Antti Nieminen', '0461231234', 'Katuosoite 5, Tampere'),
('elina@example.com', 'Elina Salonen', '0408765432', 'Katuosoite 6, Oulu'),
('jari@example.com', 'Jari Heikkinen', '0506543210', 'Katuosoite 7, Jyväskylä'),
('tiina@example.com', 'Tiina Lehtinen', '0443214567', 'Katuosoite 8, Kuopio'),
('pekka@example.com', 'Pekka Mäkelä', '0432109876', 'Katuosoite 9, Lahti'),
('sari@example.com', 'Sari Aalto', '0499999999', 'Katuosoite 10, Pori');

-- Varaus- ja laskutietojen lisäys

-- Esimerkki: lisätään 30 varausta ja niihin liittyvät laskut
INSERT IGNORE INTO Varaus (aloitus_pvm, paattymis_pvm, henkilo_maara, sahkoposti, mokkiID) VALUES 
('2023-05-10', '2023-05-15', 4, 'yritys1@example.com', 1),
('2023-06-20', '2023-06-25', 2, 'yritys2@example.com', 2),
('2023-07-01', '2023-07-10', 6, 'yritys3@example.com', 3),
('2023-08-15', '2023-08-20', 5, 'yritys4@example.com', 4),
('2023-09-05', '2023-09-12', 3, 'yritys5@example.com', 5),
('2023-10-10', '2023-10-15', 4, 'yritys6@example.com', 1),
('2023-11-01', '2023-11-07', 2, 'yritys7@example.com', 2),
('2023-12-20', '2023-12-27', 8, 'yritys8@example.com', 3),
('2024-01-05', '2024-01-10', 1, 'yritys9@example.com', 4),
('2024-02-14', '2024-02-20', 2, 'yritys10@example.com', 5),
('2024-03-10', '2024-03-15', 5, 'matti@example.com', 1),
('2024-04-01', '2024-04-05', 6, 'maija@example.com', 2),
('2024-05-22', '2024-05-27', 7, 'kalle@example.com', 3),
('2024-06-14', '2024-06-21', 3, 'laura@example.com', 4),
('2024-07-04', '2024-07-09', 4, 'antti@example.com', 5),
('2024-08-11', '2024-08-18', 2, 'elina@example.com', 1),
('2024-09-09', '2024-09-14', 6, 'jari@example.com', 2),
('2024-10-01', '2024-10-06', 3, 'tiina@example.com', 3),
('2024-11-17', '2024-11-23', 5, 'pekka@example.com', 4),
('2024-12-05', '2024-12-12', 4, 'sari@example.com', 5),
('2025-01-03', '2025-01-10', 4, 'yritys1@example.com', 1),
('2025-02-15', '2025-02-20', 3, 'yritys2@example.com', 2),
('2025-03-12', '2025-03-18', 2, 'yritys3@example.com', 3),
('2025-04-01', '2025-04-06', 1, 'yritys4@example.com', 4),
('2025-05-20', '2025-05-25', 2, 'yritys5@example.com', 5),
('2025-06-10', '2025-06-15', 6, 'yritys6@example.com', 1),
('2025-07-05', '2025-07-10', 5, 'yritys7@example.com', 2),
('2025-08-22', '2025-08-28', 3, 'yritys8@example.com', 3),
('2025-09-14', '2025-09-19', 4, 'yritys9@example.com', 4),
('2025-10-01', '2025-10-07', 2, 'yritys10@example.com', 5);


-- Laskut
INSERT IGNORE INTO Laskut (veroton_hinta, alv, paivamaara, erapaiva, status, viitenumero, sahkoposti, osoite, nimi, varaustunnus) VALUES
(300.00, 72.00, '2023-05-10', '2023-05-24', 'Maksettu', 10001, 'yritys1@example.com', 'Yrityskatu 1, Helsinki', 'Yritys 1', 1),
(450.00, 108.00, '2023-06-20', '2023-07-04', 'Maksettu', 10002, 'yritys2@example.com', 'Yrityskatu 2, Espoo', 'Yritys 2', 2),
(600.00, 144.00, '2023-07-01', '2023-07-15', 'Myöhässä', 10003, 'yritys3@example.com', 'Yrityskatu 3, Vantaa', 'Yritys 3', 3),
(280.00, 67.20, '2023-08-15', '2023-08-29', 'Avoin', 10004, 'yritys4@example.com', 'Yrityskatu 4, Turku', 'Yritys 4', 4),
(500.00, 120.00, '2023-09-05', '2023-09-19', 'Maksettu', 10005, 'yritys5@example.com', 'Yrityskatu 5, Tampere', 'Yritys 5', 5),
(320.00, 76.80, '2023-10-10', '2023-10-24', 'Maksettu', 10006, 'yritys6@example.com', 'Yrityskatu 6, Oulu', 'Yritys 6', 6),
(200.00, 48.00, '2023-11-01', '2023-11-15', 'Avoin', 10007, 'yritys7@example.com', 'Yrityskatu 7, Jyväskylä', 'Yritys 7', 7),
(700.00, 168.00, '2023-12-20', '2024-01-03', 'Maksettu', 10008, 'yritys8@example.com', 'Yrityskatu 8, Kuopio', 'Yritys 8', 8),
(150.00, 36.00, '2024-01-05', '2024-01-19', 'Myöhässä', 10009, 'yritys9@example.com', 'Yrityskatu 9, Lahti', 'Yritys 9', 9),
(270.00, 64.80, '2024-02-14', '2024-02-28', 'Maksettu', 10010, 'yritys10@example.com', 'Yrityskatu 10, Pori', 'Yritys 10', 10),
(390.00, 93.60, '2024-03-10', '2024-03-24', 'Avoin', 10011, 'matti@example.com', 'Katuosoite 1, Helsinki', 'Matti Meikäläinen', 11),
(410.00, 98.40, '2024-04-01', '2024-04-15', 'Myöhässä', 10012, 'maija@example.com', 'Katuosoite 2, Espoo', 'Maija Virtanen', 12),
(580.00, 139.20, '2024-05-22', '2024-06-05', 'Maksettu', 10013, 'kalle@example.com', 'Katuosoite 3, Vantaa', 'Kalle Korhonen', 13),
(460.00, 110.40, '2024-06-14', '2024-06-28', 'Avoin', 10014, 'laura@example.com', 'Katuosoite 4, Turku', 'Laura Lahtinen', 14),
(360.00, 86.40, '2024-07-04', '2024-07-18', 'Maksettu', 10015, 'antti@example.com', 'Katuosoite 5, Tampere', 'Antti Nieminen', 15),
(480.00, 115.20, '2024-08-11', '2024-08-25', 'Myöhässä', 10016, 'elina@example.com', 'Katuosoite 6, Oulu', 'Elina Salonen', 16),
(310.00, 74.40, '2024-09-09', '2024-09-23', 'Maksettu', 10017, 'jari@example.com', 'Katuosoite 7, Jyväskylä', 'Jari Heikkinen', 17),
(220.00, 52.80, '2024-10-01', '2024-10-15', 'Maksettu', 10018, 'tiina@example.com', 'Katuosoite 8, Kuopio', 'Tiina Lehtinen', 18),
(330.00, 79.20, '2024-11-17', '2024-12-01', 'Avoin', 10019, 'pekka@example.com', 'Katuosoite 9, Lahti', 'Pekka Mäkelä', 19),
(410.00, 98.40, '2024-12-05', '2024-12-19', 'Maksettu', 10020, 'sari@example.com', 'Katuosoite 10, Pori', 'Sari Aalto', 20);