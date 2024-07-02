import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Zeichenflaeche extends JPanel
{
    private ArrayList<Rechteck> hinternisse = null;
    private Kreis roboter = null;

    public Zeichenflaeche()
    {
    }

    //paintComponent zur Darstellung d. Hindernisse
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (null != hinternisse)
        {
            for (Rechteck r : hinternisse) {
                g.setColor(r.getFarbe());
                g.drawRect(r.getPosition().getX(), r.getPosition().getY(), r.getBreite(), r.getLaenge());
                g.fillRect(r.getPosition().getX(), r.getPosition().getY(), r.getBreite(), r.getLaenge());
            }
        }
        if (null != roboter)
        {
            g.drawOval(roboter.getPosition().getX(), roboter.getPosition().getY(), roboter.getDurchmesser(), roboter.getDurchmesser());
            g.fillOval(roboter.getPosition().getX(), roboter.getPosition().getY(), roboter.getDurchmesser(), roboter.getDurchmesser());
        }
    }

    //aktualisieren d. Liste v. gGezeichneten
    public void repaint(ArrayList<Rechteck> hinternisse)
    {
        this.hinternisse = hinternisse;
        repaint();
    }

    public void repaint(Kreis roboter)
    {
        this.roboter = roboter;
        repaint();
    }
}

public class Leinwand
{
    private JFrame fenster;
    private Zeichenflaeche zeichenflaeche;

    public Leinwand(int laenge, int breite)
    {
        //Erstellt Fenster mit Länge, Breite, Titel.
        fenster = new JFrame("Graphische Darstellung der Hindernisse");
        fenster.setSize(laenge, breite);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Initialisierung der Zeichenfläche + Hinzufügen zum Fenster.
        zeichenflaeche = new Zeichenflaeche();
        fenster.add(zeichenflaeche);
        fenster.setVisible(true);
    }

    //Zeichnen der Hindernisse.
    public void zeichnen(ArrayList<Rechteck> hindernisse)
    {
        //Übergabe Hindernisse an Zeichenfläche
        zeichenflaeche.repaint(hindernisse);
    }

    public void zeichnen(Kreis roboter)
    {
        zeichenflaeche.repaint(roboter);
    }
}