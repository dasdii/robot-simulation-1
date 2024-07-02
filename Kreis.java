import java.awt.Color;

public class Kreis extends Figur
{
    private int durchmesser;

    public Kreis(Punkt position, int durchmesser, Color farbe)
    {
        super(position, farbe);
        this.durchmesser = durchmesser;
    }

    public int getDurchmesser()
    {
        return durchmesser;
    }

    public void setDurchmesser(int durchmesser)
    {
        this.durchmesser = durchmesser;
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
        return position.getX() + durchmesser;
    }

    @Override
    public int maxY()
    {
        return position.getY() + durchmesser;
    }

    @Override
    public void bewegeUm(int dx, int dy)
    {
        position.bewegeUm(dx, dy);
        /*position.setX(position.getX() + dx);
        position.setY(position.getY() + dy);*/
    }

    @Override
    public void ausgabeAttribute()
    {
        System.out.println("Position: (" + position.getX() + ", " + position.getY() + ")");
        System.out.println("Durchmesser: " + durchmesser);
        System.out.println("Farbe: " + farbe.toString());
    }
}