-- Luodaan tietokanta
DROP DATABASE IF EXISTS mokkikodit;
CREATE DATABASE mokkikodit;
USE mokkikodit;

-- Taulu: Asiakas (geneerinen asiakastaulu)
CREATE TABLE Asiakas (
    sahkoposti VARCHAR(100) PRIMARY KEY,
    asiakastyyppi ENUM('yksityinen', 'yritys') NOT NULL
);

-- Taulu: Yksityisasiakas (asiakasID viittaus Asiakas-tauluun)
CREATE TABLE Yksityisasiakas (
    sahkoposti VARCHAR(100) PRIMARY KEY,
    nimi VARCHAR(100),
    puhelinnumero VARCHAR(20),
    FOREIGN KEY (sahkoposti) REFERENCES Asiakas(sahkoposti) ON DELETE CASCADE
);

-- Taulu: Yritysasiakas (asiakasID viittaus Asiakas-tauluun)
CREATE TABLE Yritysasiakas (
    sahkoposti VARCHAR(100) PRIMARY KEY,
    y_tunnus VARCHAR(20),
    osoite VARCHAR(255),
    nimi VARCHAR(100),
    FOREIGN KEY (sahkoposti) REFERENCES Asiakas(sahkoposti) ON DELETE CASCADE
);

-- Taulu: Mökki
CREATE TABLE Mokki (
    mokkiID INT AUTO_INCREMENT PRIMARY KEY,
    sijainti VARCHAR(100),
    hinta DECIMAL(10,2),
    huoneala INT,
    henkilo_maara INT
);

-- Taulu: Varaus
CREATE TABLE Varaus (
    varaustunnus INT AUTO_INCREMENT PRIMARY KEY,
    aloitus_pvm DATE,
    paattymis_pvm DATE,
    henkilo_maara INT,
    sahkoposti VARCHAR(100),
    mokkiID INT,
    FOREIGN KEY (sahkoposti) REFERENCES Asiakas(sahkoposti) ON DELETE CASCADE,
    FOREIGN KEY (mokkiID) REFERENCES Mokki(mokkiID) ON DELETE CASCADE
);

-- Taulu: Laskut
CREATE TABLE Laskut (
    laskuID INT AUTO_INCREMENT PRIMARY KEY,
    veroton_hinta DECIMAL(10,2),
    alv DECIMAL(5,2),
    paivamaara DATE,
    erapaiva DATE,
    status ENUM('Avoin', 'Maksettu', 'Myöhässä') NOT NULL,
    sahkoposti VARCHAR(100),
    varaustunnus INT,
    FOREIGN KEY (sahkoposti) REFERENCES Asiakas(sahkoposti) ON DELETE SET NULL,
    FOREIGN KEY (varaustunnus) REFERENCES Varaus(varaustunnus) ON DELETE SET NULL
);

-- MÖKIT
INSERT INTO Mokki (sijainti, hinta, huoneala, henkilo_maara) VALUES
('Levi', 150.00, 80, 6),
('Ylläs', 200.00, 100, 8),
('Ruka', 180.00, 90, 6),
('Tahko', 160.00, 85, 5),
('Vuokatti', 140.00, 75, 4),
('Himos', 170.00, 95, 7),
('Salla', 130.00, 70, 4),
('Pyhä', 155.00, 82, 5),
('Iso-Syöte', 165.00, 88, 6),
('Koli', 175.00, 92, 7);

-- ASIAKKAAT: Yritykset
INSERT INTO Asiakas (sahkoposti, asiakastyyppi) VALUES
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

INSERT INTO Yritysasiakas (sahkoposti, y_tunnus, osoite, nimi) VALUES
('yritys1@example.com', '1234567-1', 'Yrityskatu 1, Helsinki', 'Yritys 1'),
('yritys2@example.com', '1234567-2', 'Yrityskatu 2, Espoo', 'Yritys 2'),
('yritys3@example.com', '1234567-3', 'Yrityskatu 3, Vantaa', 'Yritys 3'),
('yritys4@example.com', '1234567-4', 'Yrityskatu 4, Turku', 'Yritys 4'),
('yritys5@example.com', '1234567-5', 'Yrityskatu 5, Tampere', 'Yritys 5'),
('yritys6@example.com', '1234567-6', 'Yrityskatu 6, Oulu', 'Yritys 6'),
('yritys7@example.com', '1234567-7', 'Yrityskatu 7, Jyväskylä', 'Yritys 7'),
('yritys8@example.com', '1234567-8', 'Yrityskatu 8, Kuopio', 'Yritys 8'),
('yritys9@example.com', '1234567-9', 'Yrityskatu 9, Lahti', 'Yritys 9'),
('yritys10@example.com', '1234567-10', 'Yrityskatu 10, Pori', 'Yritys 10');

-- ASIAKKAAT: Yksityiset
INSERT INTO Asiakas (sahkoposti, asiakastyyppi) VALUES
('matti@example.com', 'yksityinen'),
('maija@example.com', 'yksityinen'),
('kalle@example.com', 'yksityinen'),
('laura@example.com', 'yksityinen'),
('antti@example.com', 'yksityinen'),
('elina@example.com', 'yksityinen'),
('jari@example.com', 'yksityinen'),
('tiina@example.com', 'yksityinen'),
('pekka@example.com', 'yksityinen'),
('sari@example.com', 'yksityinen');

INSERT INTO Yksityisasiakas (sahkoposti, nimi, puhelinnumero) VALUES
('matti@example.com', 'Matti Meikäläinen', '0401234567'),
('maija@example.com', 'Maija Virtanen', '0507654321'),
('kalle@example.com', 'Kalle Korhonen', '0412345678'),
('laura@example.com', 'Laura Lahtinen', '0459876543'),
('antti@example.com', 'Antti Nieminen', '0461231234'),
('elina@example.com', 'Elina Salonen', '0408765432'),
('jari@example.com', 'Jari Heikkinen', '0506543210'),
('tiina@example.com', 'Tiina Lehtinen', '0443214567'),
('pekka@example.com', 'Pekka Mäkelä', '0432109876'),
('sari@example.com', 'Sari Aalto', '0499999999');

-- Varaus- ja laskutietojen lisäys

-- Esimerkki: lisätään 30 varausta ja niihin liittyvät laskut
INSERT INTO Varaus (aloitus_pvm, paattymis_pvm, henkilo_maara, sahkoposti, mokkiID) VALUES 
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


INSERT INTO Laskut (veroton_hinta, alv, paivamaara, erapaiva, status, sahkoposti) VALUES
(300.00, 72.00, '2023-05-10', '2023-05-24', 'Maksettu', 'yritys1@example.com'),
(450.00, 108.00, '2023-06-20', '2023-07-04', 'Maksettu', 'yritys2@example.com'),
(600.00, 144.00, '2023-07-01', '2023-07-15', 'Myöhässä', 'yritys3@example.com'),
(280.00, 67.20, '2023-08-15', '2023-08-29', 'Avoin', 'yritys4@example.com'),
(500.00, 120.00, '2023-09-05', '2023-09-19', 'Maksettu', 'yritys5@example.com'),
(320.00, 76.80, '2023-10-10', '2023-10-24', 'Maksettu', 'yritys6@example.com'),
(200.00, 48.00, '2023-11-01', '2023-11-15', 'Avoin', 'yritys7@example.com'),
(700.00, 168.00, '2023-12-20', '2024-01-03', 'Maksettu', 'yritys8@example.com'),
(150.00, 36.00, '2024-01-05', '2024-01-19', 'Myöhässä', 'yritys9@example.com'),
(270.00, 64.80, '2024-02-14', '2024-02-28', 'Maksettu', 'yritys10@example.com'),
(390.00, 93.60, '2024-03-10', '2024-03-24', 'Avoin', 'matti@example.com'),
(410.00, 98.40, '2024-04-01', '2024-04-15', 'Myöhässä', 'maija@example.com'),
(580.00, 139.20, '2024-05-22', '2024-06-05', 'Maksettu', 'kalle@example.com'),
(460.00, 110.40, '2024-06-14', '2024-06-28', 'Avoin', 'laura@example.com'),
(360.00, 86.40, '2024-07-04', '2024-07-18', 'Maksettu', 'antti@example.com'),
(480.00, 115.20, '2024-08-11', '2024-08-25', 'Myöhässä', 'elina@example.com'),
(310.00, 74.40, '2024-09-09', '2024-09-23', 'Maksettu', 'jari@example.com'),
(220.00, 52.80, '2024-10-01', '2024-10-15', 'Maksettu', 'tiina@example.com'),
(330.00, 79.20, '2024-11-17', '2024-12-01', 'Avoin', 'pekka@example.com'),
(410.00, 98.40, '2024-12-05', '2024-12-19', 'Maksettu', 'sari@example.com'),
(390.00, 93.60, '2025-01-03', '2025-01-17', 'Maksettu', 'yritys1@example.com'),
(510.00, 122.40, '2025-02-15', '2025-03-01', 'Maksettu', 'yritys2@example.com'),
(220.00, 52.80, '2025-03-12', '2025-03-26', 'Myöhässä', 'yritys3@example.com'),
(300.00, 72.00, '2025-04-01', '2025-04-15', 'Maksettu', 'yritys4@example.com'),
(470.00, 112.80, '2025-05-20', '2025-06-03', 'Avoin', 'yritys5@example.com'),
(290.00, 69.60, '2025-06-10', '2025-06-24', 'Maksettu', 'yritys6@example.com'),
(510.00, 122.40, '2025-07-05', '2025-07-19', 'Maksettu', 'yritys7@example.com'),
(380.00, 91.20, '2025-08-22', '2025-09-05', 'Myöhässä', 'yritys8@example.com'),
(340.00, 81.60, '2025-09-14', '2025-09-28', 'Avoin', 'yritys9@example.com'),
(260.00, 62.40, '2025-10-01', '2025-10-15', 'Maksettu', 'yritys10@example.com');