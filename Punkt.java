public class Punkt
{
    // Instanzvariablen
    private int x = 0;
    private int y = 0;

    // Konstruktor, der die Koordinaten auf die angegebenen Werte setzt
    public Punkt(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    //(Getter-Methode) Rückgabewert der x-Koordinate
    public int getX()
    {
        return x;
    }
    //Rückgabewert der y-Koordinate
    public int getY()
    {
        return y;
    }
    //(Setter-Methode) Ersetzen der x-Koordinate mit neuen Wert
    public void setX(int neuerWert1)
    {
        this.x = neuerWert1;
    }
    //Ersetzen der y-Koordinate mit neuen Wert
    public void setY(int neuerWert2)
    {
        this.y = neuerWert2;
    }
    //Ausgabe  x- und y-Koordinaten
    public void ausgabeAttribute()
    {
        System.out.println("X-Koordinate: " + x + "," + "Y-Koordinate: " + y);
    }
    //Verschiebung des Punktes um dx und dy
    public void bewegeUm(int dx, int dy)
    {
        x += dx;
        y += dy;
    }
    /*
    // Berechnung des Abstands zw zwei Punkten (x, y) und (x2, y2)
    public double gibAbstand(double x, double y, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x2 - x, 2)+Math.pow(y2 - y, 2)); -> überflüssig da distanzZu ausreichend ist??
    }
    */

   //Berechnung der Distanz zw zwei Punkten
    public double distanzZu(Punkt andererPunkt)
    {
        // Berechnet die Differenz der x- und y-Koordinaten
        int dx = andererPunkt.getX() - this.x;
        int dy = andererPunkt.getY() - this.y;
        // Berechnet und gibt die euklidische Distanz zurück
        return Math.sqrt(dx * dx + dy * dy);
    }
}