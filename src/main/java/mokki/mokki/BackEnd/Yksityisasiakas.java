package mokki.mokki.BackEnd;

public class Yksityisasiakas extends Asiakas{
    private String nimi;

    protected Yksityisasiakas(int asiakasID, String asiakasTyyppi, int puhelinnumero, String sahkoposti, String nimi) {
        super(asiakasID, asiakasTyyppi, puhelinnumero, sahkoposti);
        this.nimi = nimi;
    }

    @Override
    public void setAsiakasTyyppi() {
        super.setAsiakasTyyppi("Yksityinen");
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }
}
