import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Zeichenflaeche extends JPanel
{
    private ArrayList<Rechteck> figuren; //ArryList von Rechteck, der hindernisse enthält
    private Kreis roboter;

    public Zeichenflaeche()
    {
        // Initialisierung der ArrayList figuren.
        figuren = new ArrayList<>();
        roboter = new Kreis(new Punkt(0,0), 50, java.awt.Color.BLACK);
    }

    //paintComponent zur Darstellung d. Hindernisse
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Rechteck r : figuren) {
            g.setColor(r.getFarbe());
            g.drawRect(r.getPosition().getX(), r.getPosition().getY(), r.getBreite(), r.getLaenge());
            g.fillRect(r.getPosition().getX(), r.getPosition().getY(), r.getBreite(), r.getLaenge());
        }
        g.drawOval(roboter.getPosition().getX(), roboter.getPosition().getY(), roboter.getDurchmesser(), roboter.getDurchmesser());
        g.fillOval(roboter.getPosition().getX(), roboter.getPosition().getY(), roboter.getDurchmesser(), roboter.getDurchmesser());
    }

    //aktualisieren d. Liste v. gGezeichneten
    public void repaintRechtecke(ArrayList<Rechteck> neueFiguren)
    {
        this.figuren = neueFiguren;
        repaint();
    }

    public void repaintRoboter(Kreis neueRoboter)
    {
        this.roboter = neueRoboter;
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
        zeichenflaeche.repaintRechtecke(hindernisse);
    }

    public void zeichnen(Kreis roboter)
    {
        zeichenflaeche.repaintRoboter(roboter);
    }
}