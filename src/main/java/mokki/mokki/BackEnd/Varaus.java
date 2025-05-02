package mokki.mokki.BackEnd;

import java.util.Date;

/**
 * Luokka kuvaa varausta missä sisältyy varauksen tunnus, varaajien määrä,
 * varauksien alku ja loppupäivämäärät
 */


public class Varaus
{

    private String varausTunnus;
    private int varaajienMaara;
    private Date varausAlkuPvm;
    private Date varausLoppuPvm;

    /**
     * Alustaa uuden varauksen
     * @param varausTunnus  varauksessa oleva varaustunnus
     * @param varaajienMaara  varaajien määrä
     * @param varausAlkuPvm    varauksen  alkupäivämäärä
     * @param varausLoppuPvm   varauksen loppupäivämäärä
     */

    Varaus(String varausTunnus, int varaajienMaara, Date varausAlkuPvm, Date varausLoppuPvm){

        this.varausTunnus = varausTunnus;
        this.varaajienMaara = varaajienMaara;
        this.varausAlkuPvm = varausAlkuPvm;
        this.varausLoppuPvm = varausLoppuPvm;
    }


    // setterit

    /**
     * metodi asettaa varaus tunnuksen
      * @param varausTunnus  varauksen tunnus
     */


    public void setVarausTunnus(String varausTunnus){
        this.varausTunnus = varausTunnus;
    }

    /**
     * metodi asettaa varaajien määrän
     * @param varaajienMaara   varaajien määrä
     */

    public void setVaraajienMaara(int varaajienMaara){
        this.varaajienMaara = varaajienMaara;
    }

    /**
     * metodi asettaa varauksen alkamispäivämäärän
     * @param varausAlkuPvm   varauksen alkamispäivä
     */
    public void setVarausAlkuPvm(Date varausAlkuPvm){
        this.varausAlkuPvm = varausAlkuPvm;
    }

    /**
     * metodi asettaa varauksen loppumispäivämäärän
     * @param varausLoppuPvm   varauksen loppumispäivä
     */

    public void setVarausLoppuPvm(Date varausLoppuPvm){
        this.varausLoppuPvm = varausLoppuPvm;
    }

    // getterit

    /**
     * metodi palauttaa varaus tunnuksen
     * @return   varaus tunnuksen
     */


    public String getVarausTunnus(){
        return varausTunnus;
    }

    /**
     * metodi palauttaa varaajien määrän
     * @return  varaajien määrän
     */

    public int getVaraajienMaara(){
        return varaajienMaara;
    }

    /**
     * metodi palauttaa varauksen alkamispäivämäärän
     * @return   varauksen alkamispäivämäärän
     */

    public Date getVarausAlkuPvm(){
        return varausAlkuPvm;
    }

    /**
     * metodi palauttaa varauksen loppumispäivämäärän
     * @return   varauksen loppumispäivämäärän
     */
    public Date getVarausLoppuPvm(){
        return varausLoppuPvm;
    }












}
