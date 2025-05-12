# mokki
Mökkien hallinnointijärjestelmä

Järjestelmä toteuttaa seuraavat toiminnallisuudet:

1. Mökkien hallinta

2. Majoitusvarausten hallinta

3. Asiakashallinta

4. Laskujen hallinta ja seuranta

5. Majoittumisen raportointi

Jotta tietokanta toimisi järjestelmässä oikein, tee database.properties -tiedosto hakemiston juureen.
Tiedostoon tulee laittaa seuraavat tiedot:

DB_URL=jdbc:mysql://localhost:3306/   (tai oman localhostin portti)
DB_DATABASE=mokkikodit
DB_USER= "tähän paikalle oma mySQL käytäjätunnus"
DB_PASSWORD="tähän paikalle oma mySQL salasana"

Ohjelma luo käynnistyessään yhteyden tietokantaan käyttäen annettuja tunnuksia, ja luo testidatan ohjelman testaamista varten.