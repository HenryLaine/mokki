-- Luodaan tietokanta
CREATE DATABASE IF NOT EXISTS mokkikodit;
USE mokkikodit;


-- Taulu: Asiakas (geneerinen asiakastaulu)
CREATE TABLE IF NOT EXISTS Asiakas (
    sahkoposti VARCHAR(100) PRIMARY KEY,
    asiakastyyppi VARCHAR(30)
);

-- Taulu: Yksityisasiakas (asiakasID viittaus Asiakas-tauluun)
CREATE TABLE IF NOT EXISTS Yksityisasiakas (
    sahkoposti VARCHAR(100) PRIMARY KEY,
    nimi VARCHAR(100),
    puhelinnumero VARCHAR(20),
    osoite varchar(100),
    FOREIGN KEY (sahkoposti) REFERENCES Asiakas(sahkoposti) ON DELETE CASCADE
);

-- Taulu: Yritysasiakas (asiakasID viittaus Asiakas-tauluun)
CREATE TABLE IF NOT EXISTS Yritysasiakas (
    sahkoposti VARCHAR(100) PRIMARY KEY,
    y_tunnus VARCHAR(20),
    osoite VARCHAR(255),
    nimi VARCHAR(100),
    FOREIGN KEY (sahkoposti) REFERENCES Asiakas(sahkoposti) ON DELETE CASCADE
);

CREATE OR REPLACE VIEW AsiakasTiedotView AS
SELECT 
    a.sahkoposti,
    a.asiakastyyppi,
    y.nimi AS nimi,
    y.osoite AS osoite
FROM Asiakas a
JOIN Yksityisasiakas y ON a.sahkoposti = y.sahkoposti

UNION

SELECT 
    a.sahkoposti,
    a.asiakastyyppi,
    yr.nimi AS nimi,
    yr.osoite AS osoite
FROM Asiakas a
JOIN Yritysasiakas yr ON a.sahkoposti = yr.sahkoposti;

-- Taulu: Mökki
CREATE TABLE IF NOT EXISTS Mokki (
    mokkiID INT AUTO_INCREMENT PRIMARY KEY,
    sijainti VARCHAR(100),
    huoneita INT,
    hinta DOUBLE(10,2),
    huoneala DOUBLE,
    henkilo_maara INT,
    huomioitavaa VARCHAR(1000)
);

-- Taulu: Varaus
CREATE TABLE IF NOT EXISTS Varaus (
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
CREATE TABLE IF NOT EXISTS Laskut (
    laskuID INT AUTO_INCREMENT PRIMARY KEY,
    veroton_hinta DECIMAL(10,2),
    alv DECIMAL(5,2),
    paivamaara DATE,
    erapaiva DATE,
    osoite VARCHAR(100),
    nimi VARCHAR(100),
    status ENUM('Avoin', 'Maksettu', 'Myöhässä') NOT NULL,
    viitenumero INT,
    sahkoposti VARCHAR(100),
    varaustunnus INT,
    FOREIGN KEY (sahkoposti) REFERENCES Asiakas(sahkoposti) ON DELETE SET NULL,
    FOREIGN KEY (varaustunnus) REFERENCES Varaus(varaustunnus) ON DELETE SET NULL
);