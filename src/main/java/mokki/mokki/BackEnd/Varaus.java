package mokki.mokki.BackEnd;

import java.util.Date;

public class Varaus
{
    // Viimeistelen perjantaina kuntoon

    private String varausTunnus;
    private int varaajienMaara;
    private Date varausAlkuPvm;
    private Date varausLoppuPvm;


    Varaus(String varausTunnus, int varaajienMaara, Date varausAlkuPvm, Date varausLoppuPvm){

        this.varausTunnus = varausTunnus;
        this.varaajienMaara = varaajienMaara;
        this.varausAlkuPvm = varausAlkuPvm;
        this.varausLoppuPvm = varausLoppuPvm;
    }



    public void setVarausTunnus(String varausTunnus){
        this.varausTunnus = varausTunnus;
    }

    public void setVaraajienMaara(int varaajienMaara){
        this.varaajienMaara = varaajienMaara;
    }

    public void setVarausAlkuPvm(Date varausAlkuPvm){
        this.varausAlkuPvm = varausAlkuPvm;
    }

    public void setVarausLoppuPvm(Date varausLoppuPvm){
        this.varausLoppuPvm = varausLoppuPvm;
    }


    public String getVarausTunnus(){
        return varausTunnus;
    }

    public int getVaraajienMaara(){
        return varaajienMaara;
    }

    public Date getVarausAlkuPvm(){
        return varausAlkuPvm;
    }
    public Date getVarausLoppuPvm(){
        return getVarausLoppuPvm();
    }












}
