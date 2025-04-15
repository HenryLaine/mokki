-- Luodaan tietokanta
CREATE DATABASE mokkikodit;
USE mokkikodit;

-- Taulu: Mökkikodit oy
CREATE TABLE MokkikoditOy (
    y_tunnus VARCHAR(20) PRIMARY KEY,
    sahkoposti VARCHAR(100),
    osoite VARCHAR(255)
);

-- Taulu: Asiakas (asiakas superluokka)
CREATE TABLE Asiakas (
    asiakasID INT AUTO_INCREMENT PRIMARY KEY,
    asiakastyyppi ENUM('yksityinen', 'yritys') NOT NULL
);

-- Taulu: Yksityisasiakas (alaluokka asiakkaalle, asiakasID viittaus Asiakas-tauluun)
CREATE TABLE Yksityisasiakas (
    asiakasID INT PRIMARY KEY,
    nimi VARCHAR(100),
    sahkoposti VARCHAR(100),
    puhelinnumero VARCHAR(20),
    FOREIGN KEY (asiakasID) REFERENCES Asiakas(asiakasID) ON DELETE CASCADE
);

-- Taulu: Yritysasiakas (alaluokka asiakkaalle, asiakasID viittaus Asiakas-tauluun)
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
    pinta-ala INT,
    henkilomaara INT
);

-- Taulu: Varaus
CREATE TABLE Varaus (
    varaustunnus INT AUTO_INCREMENT PRIMARY KEY,
    aloitus_pvm DATE,
    paattymis_pvm DATE,
    henkilo_maara INT,
    FOREIGN KEY (asiakasID) REFERENCES Asiakas(asiakasID) ON DELETE CASCADE,
    FOREIGN KEY (mokkiID) REFERENCES Mokkis(mokkiID) ON DELETE CASCADE
);
