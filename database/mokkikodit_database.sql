-- Luodaan tietokanta
DROP DATABASE IF EXISTS mokkikodit;
CREATE DATABASE mokkikodit;
USE mokkikodit;

-- Taulu: Asiakas (geneerinen asiakastaulu)
CREATE TABLE Asiakas (
    asiakasID INT AUTO_INCREMENT PRIMARY KEY,
    asiakastyyppi ENUM('yksityinen', 'yritys') NOT NULL
);

-- Taulu: Yksityisasiakas (asiakasID viittaus Asiakas-tauluun)
CREATE TABLE Yksityisasiakas (
    asiakasID INT PRIMARY KEY,
    nimi VARCHAR(100),
    sahkoposti VARCHAR(100),
    puhelinnumero VARCHAR(20),
    FOREIGN KEY (asiakasID) REFERENCES Asiakas(asiakasID) ON DELETE CASCADE
);

-- Taulu: Yritysasiakas (asiakasID viittaus Asiakas-tauluun)
CREATE TABLE Yritysasiakas (
    asiakasID INT PRIMARY KEY,
    y_tunnus VARCHAR(20),
    sahkoposti VARCHAR(100),
    osoite VARCHAR(255),
    nimi VARCHAR(100),
    FOREIGN KEY (asiakasID) REFERENCES Asiakas(asiakasID) ON DELETE CASCADE
);

-- Taulu: Laskut
CREATE TABLE Laskut (
    laskuID INT AUTO_INCREMENT PRIMARY KEY,
    veroton_hinta DECIMAL(10,2),
    alv DECIMAL(5,2),
    paivamaara DATE,
    erapaiva DATE,
    status ENUM('Avoin', 'Maksettu', 'Myöhässä') NOT NULL,
    asiakasID INT,
    FOREIGN KEY (asiakasID) REFERENCES Asiakas(asiakasID) ON DELETE SET NULL
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
    asiakasID INT,
    mokkiID INT,
    FOREIGN KEY (asiakasID) REFERENCES Asiakas(asiakasID) ON DELETE CASCADE,
    FOREIGN KEY (mokkiID) REFERENCES Mokki(mokkiID) ON DELETE CASCADE
);

-- 1. Lisää 10 mökkiä
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

INSERT INTO Asiakas (asiakastyyppi) VALUES 
('yritys'), ('yritys'), ('yritys'), ('yritys'), ('yritys'),
('yritys'), ('yritys'), ('yritys'), ('yritys'), ('yritys');

INSERT INTO Yritysasiakas (asiakasID, y_tunnus, sahkoposti, osoite, nimi) VALUES
(1, '1234567-1', 'yritys1@example.com', 'Yrityskatu 1, Helsinki', 'Yritys 1'),
(2, '1234567-2', 'yritys2@example.com', 'Yrityskatu 2, Espoo', 'Yritys 2'),
(3, '1234567-3', 'yritys3@example.com', 'Yrityskatu 3, Vantaa', 'Yritys 3'),
(4, '1234567-4', 'yritys4@example.com', 'Yrityskatu 4, Turku', 'Yritys 4'),
(5, '1234567-5', 'yritys5@example.com', 'Yrityskatu 5, Tampere', 'Yritys 5'),
(6, '1234567-6', 'yritys6@example.com', 'Yrityskatu 6, Oulu', 'Yritys 6'),
(7, '1234567-7', 'yritys7@example.com', 'Yrityskatu 7, Jyväskylä', 'Yritys 7'),
(8, '1234567-8', 'yritys8@example.com', 'Yrityskatu 8, Kuopio', 'Yritys 8'),
(9, '1234567-9', 'yritys9@example.com', 'Yrityskatu 9, Lahti', 'Yritys 9'),
(10,'1234567-10','yritys10@example.com','Yrityskatu 10, Pori', 'Yritys 10');

-- Lisää yksityisasiakkaat (Asiakas → Yksityisasiakas)
INSERT INTO Asiakas (asiakastyyppi) VALUES 
('yksityinen'), ('yksityinen'), ('yksityinen'), ('yksityinen'), ('yksityinen'),
('yksityinen'), ('yksityinen'), ('yksityinen'), ('yksityinen'), ('yksityinen');

INSERT INTO Yksityisasiakas (asiakasID, nimi, sahkoposti, puhelinnumero) VALUES
(11, 'Matti Meikäläinen', 'matti@example.com', '0401234567'),
(12, 'Maija Virtanen', 'maija@example.com', '0507654321'),
(13, 'Kalle Korhonen', 'kalle@example.com', '0412345678'),
(14, 'Laura Lahtinen', 'laura@example.com', '0459876543'),
(15, 'Antti Nieminen', 'antti@example.com', '0461231234'),
(16, 'Elina Salonen', 'elina@example.com', '0408765432'),
(17, 'Jari Heikkinen', 'jari@example.com', '0506543210'),
(18, 'Tiina Lehtinen', 'tiina@example.com', '0443214567'),
(19, 'Pekka Mäkelä', 'pekka@example.com', '0432109876'),
(20, 'Sari Aalto', 'sari@example.com', '0499999999');

-- Varaus- ja laskutietojen lisäys

-- Esimerkki: lisätään 30 varausta ja niihin liittyvät laskut
INSERT INTO Varaus (aloitus_pvm, paattymis_pvm, henkilo_maara, asiakasID, mokkiID) VALUES 
('2023-05-10', '2023-05-15', 4, 1, 1),
('2023-06-20', '2023-06-25', 2, 2, 2),
('2023-07-01', '2023-07-10', 6, 3, 3),
('2023-08-15', '2023-08-20', 5, 4, 4),
('2023-09-05', '2023-09-12', 3, 5, 5),
('2023-10-10', '2023-10-15', 4, 6, 1),
('2023-11-01', '2023-11-07', 2, 7, 2),
('2023-12-20', '2023-12-27', 8, 8, 3),
('2024-01-05', '2024-01-10', 1, 9, 4),
('2024-02-14', '2024-02-20', 2, 10, 5),
('2024-03-10', '2024-03-15', 5, 11, 1),
('2024-04-01', '2024-04-05', 6, 12, 2),
('2024-05-22', '2024-05-27', 7, 13, 3),
('2024-06-14', '2024-06-21', 3, 14, 4),
('2024-07-04', '2024-07-09', 4, 15, 5),
('2024-08-11', '2024-08-18', 2, 16, 1),
('2024-09-09', '2024-09-14', 6, 17, 2),
('2024-10-01', '2024-10-06', 3, 18, 3),
('2024-11-17', '2024-11-23', 5, 19, 4),
('2024-12-05', '2024-12-12', 4, 20, 5),
('2025-01-03', '2025-01-10', 4, 1, 1),
('2025-02-15', '2025-02-20', 3, 2, 2),
('2025-03-12', '2025-03-18', 2, 3, 3),
('2025-04-01', '2025-04-06', 1, 4, 4),
('2025-05-20', '2025-05-25', 2, 5, 5),
('2025-06-10', '2025-06-15', 6, 6, 1),
('2025-07-05', '2025-07-10', 5, 7, 2),
('2025-08-22', '2025-08-28', 3, 8, 3),
('2025-09-14', '2025-09-19', 4, 9, 4),
('2025-10-01', '2025-10-07', 2, 10, 5);

-- Laskut samoille asiakkaille (30 kpl), sisältäen eri statuksia
INSERT INTO Laskut (veroton_hinta, alv, paivamaara, erapaiva, status, asiakasID) VALUES
(300.00, 72.00, '2023-05-10', '2023-05-24', 'Maksettu', 1),
(450.00, 108.00, '2023-06-20', '2023-07-04', 'Maksettu', 2),
(600.00, 144.00, '2023-07-01', '2023-07-15', 'Myöhässä', 3),
(280.00, 67.20, '2023-08-15', '2023-08-29', 'Avoin', 4),
(500.00, 120.00, '2023-09-05', '2023-09-19', 'Maksettu', 5),
(320.00, 76.80, '2023-10-10', '2023-10-24', 'Maksettu', 6),
(200.00, 48.00, '2023-11-01', '2023-11-15', 'Avoin', 7),
(700.00, 168.00, '2023-12-20', '2024-01-03', 'Maksettu', 8),
(150.00, 36.00, '2024-01-05', '2024-01-19', 'Myöhässä', 9),
(270.00, 64.80, '2024-02-14', '2024-02-28', 'Maksettu', 10),
(390.00, 93.60, '2024-03-10', '2024-03-24', 'Avoin', 11),
(410.00, 98.40, '2024-04-01', '2024-04-15', 'Myöhässä', 12),
(580.00, 139.20, '2024-05-22', '2024-06-05', 'Maksettu', 13),
(460.00, 110.40, '2024-06-14', '2024-06-28', 'Avoin', 14),
(360.00, 86.40, '2024-07-04', '2024-07-18', 'Maksettu', 15),
(480.00, 115.20, '2024-08-11', '2024-08-25', 'Myöhässä', 16),
(310.00, 74.40, '2024-09-09', '2024-09-23', 'Maksettu', 17),
(220.00, 52.80, '2024-10-01', '2024-10-15', 'Maksettu', 18),
(330.00, 79.20, '2024-11-17', '2024-12-01', 'Avoin', 19),
(410.00, 98.40, '2024-12-05', '2024-12-19', 'Maksettu', 20),
(390.00, 93.60, '2025-01-03', '2025-01-17', 'Maksettu', 1),
(510.00, 122.40, '2025-02-15', '2025-03-01', 'Maksettu', 2),
(220.00, 52.80, '2025-03-12', '2025-03-26', 'Myöhässä', 3),
(300.00, 72.00, '2025-04-01', '2025-04-15', 'Maksettu', 4),
(470.00, 112.80, '2025-05-20', '2025-06-03', 'Avoin', 5),
(290.00, 69.60, '2025-06-10', '2025-06-24', 'Maksettu', 6),
(510.00, 122.40, '2025-07-05', '2025-07-19', 'Maksettu', 7),
(380.00, 91.20, '2025-08-22', '2025-09-05', 'Myöhässä', 8),
(340.00, 81.60, '2025-09-14', '2025-09-28', 'Avoin', 9),
(260.00, 62.40, '2025-10-01', '2025-10-15', 'Maksettu', 10);