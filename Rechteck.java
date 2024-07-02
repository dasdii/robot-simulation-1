import java.awt.Color;

public class Rechteck extends Figur
{
    //Instanzvariablen (Speicherung der Eigenschaften v. Rechteck)
    private int breite = 0;
    private int laenge = 0;
    private String bezeichnung = new String();

    //Konstruktor
    public Rechteck(Punkt position, int breite, int laenge, String bezeichnung, Color farbe)
    {
        super(position, farbe);
        this.breite = breite;
        this.laenge = laenge;
        this.bezeichnung = bezeichnung;
    }

    public int getBreite()
    {
        return breite;
    }

    public void setBreite(int breite)
    {
        this.breite = breite;
    }

    public int getLaenge()
    {
        return laenge;
    }

    public void setLaenge(int laenge)
    {
        this.laenge = laenge;
    }

    public String getBezeichnung()
    {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung)
    {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public int minX()
    {
        return position.getX();
    }

    @Override
    public int minY()
    {
        return position.getY();
    }

    @Override
    public int maxX()
    {
        return position.getX() + breite;
    }

    @Override
    public int maxY()
    {
        return position.getY() + laenge;
    }

    @Override
    public void bewegeUm(int dx, int dy)
    {
        position.bewegeUm(dx, dy);
        /*position.setX(position.getX() + dx);
        position.setY(position.getY() + dy);*/
    }

    public void bewegeUm(Punkt verschiebevektor)
    {
        position.bewegeUm(verschiebevektor.getX(), verschiebevektor.getY());
        /*position.setX(position.getX() + verschiebevektor.getX());
        position.setY(position.getY() + verschiebevektor.getY());*/
    }

    //Ausgabe der Attribute v. Rechteck
    @Override
    public void ausgabeAttribute()
    {
        // Erzeugt Rechteck-Objekt mit aktuellen Attributen
        Rechteck rechteck = new Rechteck(position, breite, laenge, bezeichnung, farbe);

        // Gibt die Attribute des Rechtecks aus
        System.out.println("Position: (" + rechteck.getPosition().getX() + ", " + rechteck.getPosition().getY() + ")");
        System.out.println("Breite: " + rechteck.getBreite());
        System.out.println("Länge: " + rechteck.getLaenge());
        System.out.println("Bezeichnung: " + rechteck.getBezeichnung());
        System.out.println("Farbe: " + rechteck.getFarbe().toString());
    }

    public boolean ueberlappt(Rechteck r)
    {
        //Überprüfung ob Rechtecke überlappen
        return !(r.position.getX() >= this.position.getX() + this.breite ||
                 r.position.getX() + r.breite <= this.position.getX() ||
                 r.position.getY() >= this.position.getY() + this.laenge ||
                 r.position.getY() + r.laenge <= this.position.getY());
    }

    public boolean contains(int px, int py)
    {
        return px >= position.getX() && px < position.getX() + breite && py >= position.getY() && py < position.getY() + laenge;
    }
}