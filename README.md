# mokki
Mökkien hallinnointijärjestelmä

Käyttöohje: Mökkijärjestelmä 

 

Sisällysluettelo 

 

Johdanto 

Järjestelmän vaatimukset 

Asennusohjeet 

Käyttöliittymän selitys 

Tuki ja palaute 
 

1.Johdanto 

Kiitos kun käytät järjestelmäämme!  

Tervetuloa Mökkijärjestelmä käyttöohjeeseen. Tämä käyttöohje auttaa sinua asentamaan ja käyttämään järjestelmäämme tehokkaasti. Järjestelmä toteuttaa seuraavat toiminnallisuudet:  

- Mökkien hallinta 

- Majoitusvarausten hallinta 

- Asiakashallinta 

- Laskujen hallinta ja seuranta 

- Majoittumisen raportointi 

Suosittelemme, että luet huolellisesti tämä käyttöohje kokonaan ennen järjestelmän käyttöä. Jos sinulla on kysyttävää luettuasi tämän käyttöohjeen, ota yhteyttä MökkiOy@example.com tai 0402851892. 
 

2.Järjestelmän vaatimukset  

 

Käyttöjärjestelmä: Windows 11 	 

Ohjelmistovaatimukset: JDK 24, JavaFX 24,  MySQL   

Muisti: min 4GB ram 

Tallennustila: suositeltavaa 512 GB   

 

 

3. Asennusohjeet 

 

Asenna IntelliJ, mikäli et ole vielä sitä asentanut 

Lataa asennustiedosto 

Suorita ladattu asennustiedosto 

Kun asennus on valmis, käynnistä järjestelmä painamalla “ run ” 

 

 

Tietokannan hallinta: 

Jotta tietokanta toimisi järjestelmässä oikein, tee database.properties -tiedosto hakemiston juureen. Tiedostoon tulee laittaa seuraavat tiedot: 

DB_URL=jdbc:mysql://localhost:3306/ (tai oman localhostin portti) 
DB_DATABASE=mokkikodit DB_USER= "tähän paikalle oma mySQL käytäjätunnus" 
DB_PASSWORD="tähän paikalle oma mySQL salasana" 


Ohjelma luo käynnistyessään yhteyden tietokantaan käyttäen annettuja tunnuksia, ja luo testidatan ohjelman testaamista varten. 
 

4. Käyttöliittymän selitys 

Mökkivaraus järjestelmässä on 5 välilehteä: Kohteet, Varaukset, Asiakkaat, Laskut ja Raportit.  

Kohteet välilehti: Järjestelmän pääikkuna  

Kohteet välilehdessä käyttäjä pystyy tarkastelemaan kaikkia mökkikohteita, jotka ovat varattavissa. Mökin kohteet ja siihen liittyvät tiedot näytetään taulukko muodossa.  

Tunnus: Kohteen yksilöivä tunnusnumero 

Sijainti: Mökin sijainti 

Huoneita: Mökissä olevien huoneiden määrä 

Pinta-ala: Mökin kokonais pinta-ala  

Hinta: Mökin hinta 

Henkilömäärä: Mökkiin mahtuva henkilömäärä 

Huomioitavaa: Mahdolliset erityisohjeita tai lisätietoja 

Toiminnot: 

Lisää kohde: Luo uuden mökin järjestelmään. Painamalla tätä painiketta avautuu Lisää kohde ikkunan 

Rajaa kohteita: Tämä toiminto rajaa näkyviä kohteita tietyille kriteereille 

Poista rajaukset: Tämä toiminto poistaa kaikki aiemmin asetetut rajaukset 
 

Lisää kohde ikkuna 

Lisää kohde painiketta painamalla avautuu ikkuna, jossa käyttäjä voi lisätä uuden kohteen järjestelmään.  

Tunnus: Tässä kentässä järjestelmä täydentää automaattisesti kohteen tunnuksen juoksevan numeroinnin mukaan 

Sijainti: Tässä kentässä käyttäjä voi syöttää mökin sijainnin 

Huoneita: Tässä kentässä käyttäjä voi syöttää mökin huoneiden määrän 

Pinta-ala: Tässä asiakas voi syöttää mökin kokonais pinta-alan 

Hinta: Tässä kentässä käyttäjä voi syöttää mökin hinnan 

Henkilömäärä: Tässä kentässä käyttäjä voi syöttää henkilömäärän mokille 

Huomioitavaa: Tässä kentässä käyttäjä voi syöttää erityisohjeita tai lisäohjeita, kuten lemmikkieläimet 

 

Toiminnot: 

Lisää kohde: Kun kentät ovat täytetty, painamalla tätä painiketta lisätäksesi uuden kohteen järjestelmään 

Peruuta: Jos et ei halua lisätä uutta kohdetta, painamalla tätä painiketta peruuttaaksesi lisääminen 
 

Rajaa kohteita ikkuna 

Rajaa kohteita painiketta painamalla avautuu ikkuna, jossa voi rajata kohteita kirjoittamalla hakusanan hakukenttään. 

Toiminnot: 

Syötä hakusana: Käyttäjä kirjoittaa hakusanan (Tunnus, Sijainti, Huoneita jne ) kenttään, jolloin järjestelmä suodattaa tiedot ja näyttää vain ne tiedot, jotka vastaavat hakusanaa 

OK: Kun käyttäjä on kirjoittanut hakukenttään hakusanan, paina OK painiketta niin hakutulokset tulevat näytölle 

Peruuta: Jos käyttäjät ei halua rajata kohteita, painamalla tätä painiketta peruuttaa rajauksen ja näyttää kaikki kohteet järjestelmässä 

Poista rajaukset: Tämä painike poistaa kaikki rajaukset ja palauttaa kaikki kohteet järjestelmässä oletustilaan 
 
Osoittamalla hiirellä kohteita ja klikkaamalla hiiren oikean puolen näppäintä ilmestyy valikko, valikosta voi valita,  

Kohteen tiedot ikkuna 
Näytä kohteen tiedot painiketta painamalla avautuu ikkuna, jossa käyttäjä voi tarkastella kohteen tietoja.  
Toiminnot: Sulje painike painamalla sulkee Asiakkaan tiedot ikkunan. 
 

Muuta kohteen tietoja ikkuna 
Muuta kohteen tietoja painiketta painamalla avautuu ikkuna, jossa voi muokata kohteen tietoja.  
Toiminnot: 

Muuta tiedot: Kun halutut muutokset ovat tehty, painamalla tätä painiketta muuttaaksesi kohteen tiedot. 

Peruuta: Jos käyttäjä ei halua muuttaa tietoja, voi peruttaa toiminnon painamalla tätä painiketta 

Kohteet välilehden vahvistusikkuna 
Poista kohde painiketta painamalla avautuu vahvistusikkuna, jossa käyttäjä voi poistaa valitun kohteen.  


Toiminnot 
Vahvistusviesti: Kysyy käyttäjältä vahvistuksen kohteen poistamisesta  
Kyllä: Käyttäjä voi jatkaa poistamista painamalla kyllä painikkeen 
Ei: Käyttäjä voi peruuttaa poistamisen painamalla ei painiketta 

Varaukset välilehti:  
Varaukset välilehdessä pystyy tarkastelemaan ja hallita mökin varaustietoja. Varaukset ja siihen liittyvät tiedot näytetään taulukko muodossa.  
Tunnus: Varauksen yksilöivä tunnusnumero.  
Kohteen tunnus: Kohteen yksilöivä tunnusnumero 
Asiakas: Asiakkaan nimi ja sähköposti, joka on tehnyt varauksen.  
Alkaa: Varauksen aloituspäivämäärä.  
Päättyy: Varauksen päättymispäivämäärä.  
Tila: Varauksen tila, Aktiivinen tarkoittaa varaus on edelleen voimassa ja ei aktiivinen tarkoittaa varaus ei ole voimassa.  
Huomioitavaa: Mahdolliset lisätiedot tai erityisohjeet varaukselle.  

Toiminnot  

Lisää varaus: Luo uusi varaus painamalla tämä painike, jolloin avautuu Lisää varaus ikkuna 
Rajaa varauksia: Tämä toiminto rajaa näkyviä varauksia tietyille kriteereille 
Poista rajaukset: Tämä toiminto poistaa kaikki aiemmin asetetut rajaukset 

Lisää varaus ikkuna 
Lisää varaus painiketta painamalla avautuu ikkuna, jossa käyttäjä voi lisätä uuden varauksen järjestelmään.  
Tunnus: Tässä kentässä järjestelmä automaattisesti täydentää tunnuksen 
Kohteen tunnus: Tässä kentässä käyttäjä syöttää kohteen tunnuksen 
Asiakkaan sähköposti: Tässä kentässä käyttäjä syöttää asiakkaan sähköpostin 
Asiakkaan nimi: Tässä käyttäjä syöttää asiakkaan nimen 
Alkaa: Tässä kentässä käyttäjä voi syöttää varauksen alkamispäivän sekä valita kalenterista alkamispäivän 
Päättyy: Tässä kentässä käyttäjä voi syöttää varauksen päättymispäivän sekä valita kalenterista päättymispäivän 
Tila: Tässä kentässä käyttäjä voi valita varauksen tilan (aktiivinen, päättynyt ja peruttu) 
Huomioitavaa: Tässä kentässä käyttäjä voi lisätä lisätietoja sekä erityisohjeita varaukseen 

Toiminnot: 
Lisää varaus: Kun kentät ovat täytetty, painamalla tätä painiketta lisätäksesi varauksen järjestelmään 
Peruuta: Jos käyttäjä ei halua lisätä, voi peruuttaa toiminnon painamalla tätä painiketta 

Rajaa varauksia ikkuna 
Rajaa varauksia painiketta painamalla avautuu ikkuna, jossa käyttäjä voi rajata varauksia kirjoittamalla hakusanan hakukenttään.  

Toiminnot:  

Syötä hakusana: Käyttäjä kirjoita hakusanan (tunnus, kohteen tunnus, jne) kenttään, jolloin järjestelmä suodattaa tiedot ja näyttää vain ne tiedot, jotka vastaavat hakusanaa 

OK: Kun käyttäjä on kirjoittanut hakukenttään hakusanan, paina OK painiketta niin hakutulokset tulevat näytölle 

Peruuta: Jos käyttäjä ei halua rajata varauksen, painamalla tätä painiketta peruttaa rajauksen ja näyttää kaikki varaukset järjestelmässä 

Poista rajaukset: Tämä painike poistaa kaikki rajaukset ja palauttaa kaikki varaukset järjestelmässä oletustilaan 

 

Osoittamalla hiirellä varauksia ja klikkaamalla hiiren oikean puolen näppäintä ilmestyy valikko, valikosta voi valita, 

Näytä varauksen tiedot 

Näytä kohteen tiedot 

Näytä asiakkaan tiedot 

Muuta varauksen tietoja 

Poista varaus 

Varauksen tiedot ikkuna 
Näytä varauksen tiedot painiketta painamalla avautuu ikkuna, jossa käyttäjä voi tarkastella varauksen tietoja.  
Toiminnot: Sulje painike painamalla sulkee Varauksen tiedot ikkunan.  

Kohteen tiedot ikkuna 

Näytä kohteen tiedot painiketta painamalla avautuu ikkuna, jossa käyttäjä voi tarkastella mökin kohteen tietoja.  
Toiminnot: Sulje painike painamalla sulkee Kohteen tiedot ikkunan. 

Asiakkaan tiedot ikkuna 
Näytä asiakkaan tiedot painiketta painamalla avautuu ikkuna, jossa käyttäjä voi tarkastella asiakkaan tietoja.  
Toiminnot: Sulje painike painamalla sulkee Asiakkaan tiedot ikkunan. 


Muuta varauksen tietoja ikkuna 
Muuta varauksen tietoja painiketta painamalla avautuu ikkuna, jossa voi muokata varauksen tietoja.  

 

Toiminnot: 

Muuta tiedot: Kun halutut muutokset ovat tehty, painamalla tätä painiketta muuttaaksesi varauksen tiedot. 
Peruuta: Jos käyttäjä ei halua muuttaa tietoja, voi peruttaa toiminnon painamalla tätä painiketta 
 

Varauksen välilehden vahvistusikkuna 

Poista varaus painiketta painamalla avautuu vahvistusikkuna, jossa käyttäjä voi poistaa valitun varauksen.  

Toiminnot 
Vahvistusviesti: Kysyy käyttäjältä vahvistuksen varauksen poistamisesta  
Kyllä: Käyttäjä voi jatkaa poistamista painamalla kyllä painikkeen 
Ei: Käyttäjä voi peruuttaa poistamisen painamalla ei painiketta 



Asiakkaat välilehti: 

Asiakkaat välilehdessä pystyy tarkastelemaan ja hallita mökin asiakkaiden tietoja. Asiakkaiden tiedot näytetään taulukko muodossa.  

Nimi: Asiakkaan nimi 

Sähköposti: Asiakkaan sähköposti 

Puhelinnumero: Asiakkaan puhelinnumero 

Tyyppi: Asiakkaan tyyppi, yksityisasiakas/yritys 

Osoite: Asiakkaan osoite 

Y-tunnus: Yrityksen y-tunnus 

 

Toiminnot: 

Lisää asiakas: Tämä toiminto luo uuden asiakkaan, jollain avautuu Lisää asiakkaita ikkunan 

Rajaa asiakkaita: Tämä toiminto rajaa asiakkaita ja avaa Rajaa asiakkaita ikkunan 

Poista rajaukset: Poistaa kaikki rajaukset 

Lisää asiakas ikkuna 

Lisää asiakas painiketta painamalla avautuu ikkuna, jossa käyttäjä voi lisätä uuden asiakkaan järjestelmään.  

Nimi: Tässä kentässä käyttäjä voi syöttää asiakkaan nimen 

Sähköposti: Tässä kentässä käyttäjä voi syöttää sähköpostiosoitteen 

Puhelinnumero: Tässä kentässä käyttäjä voi syöttää puhelinnumeron 

Tyyppi: Tässä asiakas voi valita yksityisasiakas tai yritys 

Osoite: Tässä kentässä käyttäjä voi syöttää osoitteen 

Y-tunnus: Tässä kentässä käyttäjä voi syöttää y-tunnuksen mikäli käyttäjä on valinnut yritys  

Toiminnot: 

Lisää asiakas: Kun kentät ovat täytetty, painamalla tätä painiketta lisätäksesi asiakkaan järjestelmään 

Peruuta: Jos käyttäjä ei halua lisätä, voi peruuttaa toiminnon painamalla tätä painiketta 


Rajaa asiakkaita ikkuna 

Rajaa asiakkaita painiketta painamalla avautuu ikkuna, jossa käyttäjä voi rajata asiakkaita kirjoittamalla hakusanan hakukenttään.  

 

Toiminnot:  
Syötä hakusana: Käyttäjä kirjoita hakusanan (nimi, sähköposti, puhelinnumero, tyyppi, osoite, y-tunnus) kenttään, jolloin järjestelmä suodattaa tiedot ja näyttää vain ne tiedot, jotka vastaavat hakusanaa 

OK: Kun käyttäjä on kirjoittanut hakukenttään hakusanan, paina OK painiketta niin hakutulokset tulevat näytölle 

Peruuta: Jos käyttäjä ei halua rajata asiakkaita, painamalla tätä painiketta peruttaa rajauksen ja näyttää kaikki asiakkaat järjestelmässä 

Poista rajaukset: Tämä painike poistaa kaikki rajaukset ja palauttaa kaikki asiakkaat järjestelmässä 
 

Osoittamalla hiirellä asiakkaita ja klikkaamalla hiiren oikean puolen näppäintä ilmestyy valikko, valikosta voi valita, 

Näytä asiakkaan tiedot: 

Muuta asiakkaan tietoja: 

Poista asiakas:  
 

Asiakkaan tiedot ikkuna 

Näytä asiakkaan tiedot painiketta painamalla avautuu ikkuna, jossa käyttäjä voi tarkastella asiakkaan tietoja.  

Toiminnot: Sulje painike painamalla sulkee Asiakkaan tiedot ikkunan. 

Muuta asiakkaan tietoja ikkuna 
Muuta asiakkaan tietoja painiketta painamalla avautuu ikkuna, jossa voi muokata asiakkaan tietoja.  

 

Toiminnot: 

Muuta tiedot: Kun halutut muutokset ovat tehty, painamalla tätä painiketta muuttaaksesi asiakkaan tiedot. 

Peruuta: Jos käyttäjä ei halua muuttaa tietoja, voi peruttaa toiminnon painamalla tätä painiketta 

Asiakkaat välilehden vahvistusikkuna 

 

Poista asiakas painiketta painamalla avautuu vahvistusikkuna, jossa käyttäjä voi poistaa valitun asiakkaan.  

Toiminnot 

Vahvistusviesti: Kysyy käyttäjältä vahvistuksen asiakkaan poistamisesta  

Kyllä: Käyttäjä voi jatkaa poistamista painamalla kyllä painikkeen 

Ei: Käyttäjä voi peruuttaa poistamisen painamalla ei painiketta 
 

Laskut välilehti: 

Laskut välilehdessä pystyy tarkastelemaan ja hallita mökin laskujen tietoja. Laskujen tiedot näytetään taulukko muodossa.  

Laskunumero: Laskun yksilöivä tunnusnumero 

Varaustunnus: Varauksen yksilöivä tunnusnumero 

Asiakas: Asiakkaan tiedot (etunimi, sukunimi, sähköposti) 

Viitenumero: Laskun viitenumero, jonka asiakas käyttää maksamiseen 

Veroton hinta: Laskun veroton hinta 

ALV: Arvonlisävero laskun summasta 

Maksettava hinta: Laskun loppusumma veron kanssa 

Päivämäärä: Laskun alkamispäivä 

Eräpäivä: Laskun eräpäivä 

Tila: Laskun tila (avoin, maksettu, myöhässä) 

Toiminnot: 

Lisää lasku: Tämä toiminto luo uuden laskun, jollain avautuu Lisää lasku ikkunan 

Rajaa laskuja: Tämä toiminto rajaa laskuja ja avaa Rajaa laskuja ikkunan 

Poista rajaukset: Poistaa kaikki rajaukset 
 

Lisää lasku ikkuna 

Lisää lasku painiketta painamalla avautuu ikkuna, jossa käyttäjä voi lisätä uuden laskun järjestelmään.  

Laskunumero: Tässä kentässä järjestelmä automaattisesti luo laskunumeron 

Varaustunnus: Tässä kentässä käyttäjä voi syöttää varaustunnuksen 

Nimi: Tässä kentässä käyttäjä voi syöttää asiakkaan nimen 

Sähköposti: Tässä kentässä käyttäjä voi syöttää asiakkaan sähköpostin 

Osoite: Tässä kentässä käyttäjä voi syöttää asiakkaan osoitteen 

Viitenumero: Tässä kentässä käyttäjä voi luoda viitenumeron 

Veroton hinta: Tässä kentässä käyttäjä voi syöttää laskun verottoman hinnan 

Päivämäärä: Tässä kentässä käyttäjä voi syöttää laskun aloitus päivämäärän sekä valita kalenterista aloituspäivän 

Eräpäivä: Tässä kentässä käyttäjä voi syöttää laskun eräpäivän sekä valita kalenterista eräpäivän 

Tila: Tässä kentässä käyttäjä voi syöttää laskun sen hetkisen tilan 

Toiminnot: 

Lisää lasku: Kun kentät ovat täytetty, painamalla tätä painiketta lisätäksesi laskun järjestelmään 

Peruuta: Jos käyttäjä ei halua lisätä, voi peruuttaa toiminnon painamalla tätä painiketta 

Rajaa laskuja ikkuna 

Rajaa laskuja painiketta painamalla avautuu ikkuna, jossa käyttäjä voi rajata laskuja kirjoittamalla hakusanan hakukenttään.  

Toiminnot:  

Syötä hakusana: Käyttäjä kirjoita hakusanan (asiakas, viitenumero, laskunumero, jne) kenttään, jolloin järjestelmä suodattaa tiedot ja näyttää vain ne tiedot, jotka vastaavat hakusanaa 

OK: Kun käyttäjä on kirjoittanut hakukenttään hakusanan, paina OK painiketta niin hakutulokset tulevat näytölle 

Peruuta: Jos käyttäjä ei halua rajata laskuja, painamalla tätä painiketta peruttaa rajauksen ja näyttää kaikki laskut järjestelmässä 

Poista rajaukset: Tämä painike poistaa kaikki rajaukset ja palauttaa kaikki laskut järjestelmässä oletustilaan  


Osoittamalla hiirellä laskuja ja klikkaamalla hiiren oikean puolen näppäintä ilmestyy valikko, valikosta voi valita, 

Näytä laskun tiedot 

Muuta laskun tietoja 

Merkitse lasku maksetuksi 

Poista lasku 
 

Laskun tiedot ikkuna 

Näytä laskun tiedot painiketta painamalla avautuu ikkuna, jossa käyttäjä voi tarkastella laskun tietoja.  

 

Toiminnot: Sulje painike painamalla sulkee Laskun tiedot ikkunan. 


Muuta laskun tietoja ikkuna 

Muuta laskun tietoja painiketta painamalla avautuu ikkuna, jossa voi muokata laskun tietoja.  

 

Toiminnot: 

Muuta tiedot: Kun halutut muutokset ovat tehty, painamalla tätä painiketta muuttaaksesi laskun tiedot. 

Peruuta: Jos käyttäjä ei halua muuttaa tietoja, voi peruttaa toiminnon painamalla tätä painiketta 
 

Merkitse lasku maksetuksi toiminto 

Painamalla Merkitse lasku maksetuksi painike, muuttaa laskun tilan maksetuksi 

 

Laskut välilehden vahvistusikkuna 

 

Poista lasku painiketta painamalla avautuu vahvistusikkuna, jossa käyttäjä voi poistaa valitun laskun.  

 

Toiminnot 

Vahvistusviesti: Kysyy käyttäjältä vahvistuksen laskun poistamisesta  

Kyllä: Käyttäjä voi jatkaa poistamista painamalla kyllä painikkeen 

Ei: Käyttäjä voi peruuttaa poistamisen painamalla ei painiketta 
 

Raportit välilehti: 

Raportit välilehdessä pystyy tarkastelemaan raporttien tietoja. Raporttien tiedot näytetään taulukko muodossa.  

Kohde: Mökin kohteen tunnusnumero 

Käyttöaste: Kohteen käyttöaste  

Varausten määrä: Kohteen varausmäärä  

Päivätulot: Kohteen päivätulot  

Viikkotulot: Kohteen viikkotulot 

Kuukausitulot: Kohteen kuukausitulot 

Kokonaistulot: Kohteen kokonaistulot 

 

Toiminnot: 

Alkupäivämäärä ja loppupäivämäärä: Kalenteri painikkeen painaminen avaa kalenterin ja mahdollistaa tietyn aikarajan valitsemisen raportin tarkastelua varten 

Osoittamalla hiirellä raportteja ja klikkaamalla hiiren oikean puolen näppäintä ilmestyy valikko, valikosta voi valita, 

Näytä kohteen tiedot 

Näytä kohteen varaukset 

Kohteen tiedot ikkuna 

Näytä kohteen tiedot painiketta painamalla avautuu ikkuna, jossa käyttäjä voi tarkastella kyseisen kohteen raporttien tietoja.  

Toiminnot: Sulje painike painamalla sulkee Kohteen tiedot ikkunan. 


Varauksen tiedot ikkuna 

 

Näytä kohteen varaukset painiketta painamalla avautuu ikkuna, jossa käyttäjä voi tarkastella kyseisen kohteen raporttien tietoja.  

 

Toiminnot: Sulje painike painamalla sulkee Varauksen tiedot ikkunan. 


5.Tuki ja palaute 

 

Jos tarvitse apua tai sinulla on kysyttävää, ota yhteyttä tiimiimme:  

Sähköposti: MökkiOy@example.com 

Puhelin: 0402851892  
