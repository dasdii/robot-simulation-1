import java.awt.Color;
import java.util.*;

public class Spielfeld
{
    private int BREITE = 1000;
    private int LAENGE = 1000;
    private static Random zufallsgenerator = new Random();
    private Roboter roboter;
    private Leinwand leinwand;
    private ArrayList<Rechteck> hindernisse;
    private ArrayList<Punkt> pois;

    public Spielfeld()
    {
        //Initialisierung eines Roboter-Objekts.
        roboter = null; //new Roboter();
        hindernisse = new ArrayList<>();
        leinwand = new Leinwand(BREITE, LAENGE);
        pois = new ArrayList<>();
    }

    //Generierung zufälliger Zahl/Farbe
    private int zufallszahl(int von, int bis)
    {
        //z.B. 5-10 ->   10 - 5 + 1 = 6 (0...5), deshalb + 5 für (5...10)
        return zufallsgenerator.nextInt(bis - von + 1) + von;
    }

    private Color zufallsfarbe()
    {
        int r = zufallszahl(0, 255);
        int g = zufallszahl(0, 255);
        int b = zufallszahl(0, 255);
        return new Color(r, g, b);
    }

    //Erzeugen des Robotters/Hindernisliste
    public Kreis roboterErzeugen()
    {
        return new Kreis (new Punkt(0, 0), 50, Color.BLACK);
        /*Kreis neuerRoboter = new Kreis(new Punkt(0, 0), 50, java.awt.Color.BLACK);
        return neuerRoboter;    //!roboter
        */
    }

    public ArrayList<Rechteck> hindernislisteErzeugen()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wie viele Hindernisse möchten Sie eingeben? ");
        int anzahlHindernisse = scanner.nextInt();
        ArrayList<Rechteck> hindernisliste = new ArrayList<>();

        if (50 <= anzahlHindernisse)
        {
            System.out.println("Maximale Grenze von 50 generierten Rechtecken wurde erreicht.");
            return hindernisliste;
        }

        int versuche = 0;
        for (int i = 0; i < anzahlHindernisse; i++)
        {
            int breite = zufallszahl(1, 100);
            int laenge = zufallszahl(1, 100);
            int x = zufallszahl(0, BREITE - breite);
            int y = zufallszahl(0, LAENGE - laenge);
            Color farbe = zufallsfarbe();
            String bezeichnung = "Rechteck " + (i + 1);
            Rechteck neuesRechteck = new Rechteck(new Punkt(x, y), breite, laenge, bezeichnung, farbe);
            boolean ueberschneidet = false;
            for (Rechteck r: hindernisliste)
            {
                if (neuesRechteck.ueberlappt(r))
                {
                    ueberschneidet = true;
                    break;
                }
            }

            if (!ueberschneidet)
            {
                hindernisliste.add(neuesRechteck);
                versuche = 0;  //Zurücksetzen der Zählung bei erfolgreicher Platzierung
            }
            else
            {
                i--;  //Erneuter Versuch, da aktuelle Platzierung fehlschlug
                versuche++;
            }
        }
        return hindernisliste;
    }

    //Zeichnen des Roboters/Hindernisse
    public void zeichnen(Kreis neuerRoboter)
    {
        leinwand.zeichnen(neuerRoboter);
    }

    public void zeichnen(ArrayList<Rechteck> hindernisse)
    {
        leinwand.zeichnen(hindernisse);
    }

    public void punkteEingeben()
    {
        Scanner scanner = new Scanner(System.in);
        try
        {
            System.out.println("Geben Sie die Anzahl der Hindernisse ein:");
            int anzahl = scanner.nextInt();

            for (int i = 0; i < anzahl; i++)
            {
                System.out.println("Geben Sie die Koordinaten des Hindernisses " + (i + 1) + " ein (x y breite laenge bezeichnung farbe):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int breite = scanner.nextInt();
                int laenge = scanner.nextInt();
                 String bezeichnung = scanner.next();
                String farbeString = scanner.next();
                Color farbe = Color.decode(farbeString);
                hindernisse.add(new Rechteck(new Punkt(x, y), breite, laenge, bezeichnung, farbe));
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("Fehler: Ungültige Eingabe. Bitte geben Sie ganze Zahlen ein.");
        }
        finally
        {
            scanner.close();
        }
    }

    //Sortieren der Punkte
    public Punkt[] poiSortieren(Punkt[] poi)
    {
        //Bestimmt Anzahl der Punkte im Array poi.
        int n = poi.length;
        //Erstellt Array, das verfolgt, welche Punkte besucht wurden
        boolean[] besucht = new boolean[n];
        //Erstellt Array, um die sortierten (besuchten) Punkte zu speichern
        Punkt[] sortiertePunkte = new Punkt[n];
        //Setzt den ersten Punkt im sortierten Array als ersten Punkt im poi-Array.
        sortiertePunkte[0] = poi[0];
        //Markiert den ersten Punkt als besucht.
        besucht[0] = true;
        //Schleife über verbleibenden Punkte, beginnend mit dem zweiten...
        for (int i = 1; i < n; i++)
        {
            //Setzt zuletzt besuchten Punkt als letzten Punkt im sortierten Array (-1 weil es ja bei 0 losgeht).
            Punkt letzterPunkt = sortiertePunkte[i - 1];
            //Initialisiert Index des nächsten zu besuchenden Punktes
            int naechsterPunktIndex = -1;
            //Initialisiert kürzeste Distanz mit dem maximal möglichen Wert.
            double kuerzesteDistanz = Double.MAX_VALUE;
            //Schleife über alle Punkte, um nächsten unbesuchten Punkt zu finden
            for (int j = 0; j < n; j++)
            {
                 //Prüft, ob Punkt noch nicht besucht
                if (!besucht[j])
                {
                    //Berechnet Distanz zwischen letzten besuchten und aktuellen Punkt
                    double distanz = letzterPunkt.distanzZu(poi[j]);
                    //Wenn berechnete Distanz kürzer als aktuelle kürzeste Distanz:
                    if (distanz < kuerzesteDistanz)
                    {
                        //Aktualisiert bzw. ersetzt kürzeste Distanz
                        kuerzesteDistanz = distanz;
                        //Setzt Index des nächsten zu besuchenden Punktes.
                        naechsterPunktIndex = j;
                    }
                }
            }
             //Fügt den nächsten zu besuchenden Punkt in das sortierte Array ein.
            sortiertePunkte[i] = poi[naechsterPunktIndex];
            //Markiert nächsten zu besuchenden Punkt als besucht
            besucht[naechsterPunktIndex] = true;
            //Ausgabe v. Informationen über aktuellen Punkt und Distanz zum vorherigen
            System.out.println("Punkt " + i + ": (" + sortiertePunkte[i].getX() + ", " + sortiertePunkte[i].getY() + ") - Distanz: " + kuerzesteDistanz);
        }
        return sortiertePunkte;
    }

    //Abfahren der Punkte
    public void poiAbfahren()
    {
        roboter = new Roboter();
        roboter.setPosition(0, 0);

        for (Punkt poi : pois)
        {
            while (roboter.getX() != poi.getX() || roboter.getY() != poi.getY())
            {
                if (roboter.getX() < poi.getX() && roboter.kannNachRechtsBewegen(hindernisse, getWidth()))
                {
                    roboter.bewegeRechts();
                }
                else if (roboter.getY() < poi.getY() && roboter.kannNachUntenBewegen(hindernisse, getHeight()))
                {
                    roboter.bewegeRunter();
                }
                else if (roboter.getX() > poi.getX() && roboter.kannNachLinksBewegen(hindernisse))
                {
                    roboter.bewegeLinks();
                }
                else if (roboter.getY() > poi.getY() && roboter.kannNachObenBewegen(hindernisse))
                {
                    roboter.bewegeHoch();
                }
                else
                {
                    System.out.println("Roboter kann sich nicht bewegen");
                    return; //Roboter kann sich nicht mehr bewegen
                }
            }
            System.out.println("POI erreicht: (" + poi.getX() + ", " + poi.getY() + ")");
        }
    }

    public void hindernisseUmfahren()
    {
        roboter = new Roboter();
        roboter.setPosition(0, 0);

        while (!roboter.anWand(getWidth(), getHeight()))
        {
            if (roboter.kannNachRechtsBewegen(hindernisse, getWidth()))
            {
                roboter.bewegeRechts();
            }
            else if (roboter.kannNachUntenBewegen(hindernisse, getHeight()))
            {
                roboter.bewegeRunter();
            }
            else
            {
                break; //Roboter kann sich nicht mehr bewegen
            }
        }
    }

    public int getWidth()
    {
        // Breite des Spielfelds
        return BREITE; //
    }

    public int getHeight()
    {
        return LAENGE; //
    }

    public static void main(String[] args)
    {
        Spielfeld spielfeld = new Spielfeld();
        ArrayList<Rechteck> hindernisse = spielfeld.hindernislisteErzeugen();
        spielfeld.zeichnen(hindernisse);
        Kreis roboter = spielfeld.roboterErzeugen();
        spielfeld.zeichnen(roboter);
        System.out.println("Hindernisse:");
        for (Rechteck r : hindernisse)
        {
            System.out.println(r.getBezeichnung() + " an Position (" + r.getPosition() + ") mit Breite " + r.getBreite() + " und Länge " + r.getLaenge());
        }

        Scanner scanner = new Scanner(System.in);
        try
        {
            String aufgabe = new String();
            while (!aufgabe.equalsIgnoreCase("Ende"))
            {
                System.out.println("Welche Aufgabe möchten Sie lösen?");
                System.out.println("1. Points-of-Interest abfahren");
                System.out.println("2. Hindernisse umfahren");
                System.out.println("3. Stichwörter erkennen und antworten");
                System.out.println("Ende zum Beenden");
                aufgabe = scanner.nextLine();
                //Auswahl entsprechender Methoden basierend auf Benutzereingabe.
                switch (aufgabe)
                {
                    case "1":
                        // Code Points-of-Interest abfahren
                        spielfeld.poiAbfahren();
                        break;
                    case "2":
                        // Code Hindernisseumfahren
                        spielfeld.hindernisseUmfahren();
                        break;
                    case "3":
                        // Aufruf Spracherkennung
                        spielfeld.roboter.spracherkennung();
                        break;
                }
            }
        }
        finally
        {
            scanner.close();
        }
    }
}