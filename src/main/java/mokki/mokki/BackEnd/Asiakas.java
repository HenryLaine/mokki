package mokki.mokki.BackEnd;

public abstract class Asiakas {
    private int asiakasID;
    private String asiakasTyyppi;

    private int puhelinnumero;
    private String sahkoposti;

    protected Asiakas(int asiakasID, String asiakasTyyppi, int puhelinnumero, String sahkoposti) {
        this.asiakasID = asiakasID;
        this.asiakasTyyppi = asiakasTyyppi;
        this.puhelinnumero = puhelinnumero;
        this.sahkoposti = sahkoposti;
    }

    public void setAsiakasID(int asiakasID) {
        this.asiakasID = asiakasID;
    }

    public int getAsiakasID() {
        return asiakasID;
    }

    public void setAsiakasTyyppi(String asiakasTyyppi) {
        this.asiakasTyyppi = asiakasTyyppi;
    }

    public String getAsiakasTyyppi() {
        return asiakasTyyppi;
    }

    public void setPuhelinnumero(int puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public int getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public abstract void setAsiakasTyyppi();
}
