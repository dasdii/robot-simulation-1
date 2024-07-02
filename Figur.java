import java.awt.Color;

public abstract class Figur
{
    protected Punkt position;
    protected Color farbe;

    public Figur(Punkt position, Color farbe)
    {
        this.position = position;
        this.farbe = farbe;
    }

    public Punkt getPosition() {
        return position;
    }

    public void setPosition(Punkt position) {
        this.position = position;
    }

    public Color getFarbe() {
        return farbe;
    }

    public void setFarbe(Color farbe) {
        if(farbe.equals(Color.white))
        {
            System.out.println("Diese Farbe darf nicht verwendet werden!");
        }else
        {
            this.farbe = farbe;
        }
    }

    public abstract int minX();
    public abstract int minY();
    public abstract int maxX();
    public abstract int maxY();

    public abstract void bewegeUm(int dx, int dy);
    public abstract void ausgabeAttribute();
}
