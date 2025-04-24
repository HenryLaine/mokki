package mokki.mokki.BackEnd;

public class Yritysasiakas extends Asiakas{
    private String y_tunnus;

    protected Yritysasiakas(int asiakasID, String asiakasTyyppi, int puhelinnumero, String sahkoposti, String yTunnus) {
        super(asiakasID, asiakasTyyppi, puhelinnumero, sahkoposti);
        y_tunnus = yTunnus;
    }

    @Override
    public void setAsiakasTyyppi() {
        super.setAsiakasTyyppi("Yritys");
    }

    public void setY_tunnus(String y_tunnus) {
        this.y_tunnus = y_tunnus;
    }

    public String getY_tunnus() {
        return y_tunnus;
    }
}
