import java.util.Scanner;
import java.util.ArrayList;

public class Roboter extends Kreis
{
    private int x;
    private int y;

    public Roboter()
    {
        super(new Punkt(0,0), 50, java.awt.Color.BLACK);
        this.x = 0;
        this.y = 0;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        //this.setMittelpunkt(new Punkt(x, y));
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public enum Stichwort
    {
        NAME, ALTER, HERSTELLER, GESCHLECHT
    }

    //Erkennung und Verarbeitung v. Stichwörtern
    public void spracherkennung()
    {
        Scanner scanner = new Scanner(System.in);
        String eingabe;

        // Schleife zur fortlaufenden Abfrage von Benutzereingaben.
        do
        {
            System.out.print("Stellen Sie eine Frage: ");
            eingabe = scanner.nextLine();

            // Überprüfung, ob eines der Stichwörter in der Eingabe enthalten ist.
            for (Stichwort wort : Stichwort.values())
            {
                if (eingabe.toUpperCase().contains(wort.name())) {
                    // Ausgabe einer festgelegten Antwort basierend auf dem erkannten Stichwort.
                    switch (wort) {
                        case NAME:
                            System.out.println("Heiße Robotrix.");
                            break;
                        case ALTER:
                            System.out.println("Ich habe kein Alter");
                            break;
                        case HERSTELLER:
                            System.out.println("Ich wurde von zwei Studierenden (BMT) der TU Dresden geschaffen.");
                            break;
                        case GESCHLECHT:
                            System.out.println("Ich habe kein Geschlecht.");
                            break;
                    }
                }
            }
        }
        //Beendet die do-while-Schleife, wenn der Benutzer "Ende" eingibt.
        while (!eingabe.equalsIgnoreCase("Ende"));

        scanner.close(); // Schließen des Scanner-Objekts, um Ressourcen freizugeben.
    }

    public void bewegeRechts()
    {
        x++;
        //this.setPosition(x, y);
    }

    public void bewegeLinks()
    {
        x--;
        //this.setPosition(x, y);
    }

    public void bewegeRunter()
    {
        y++;
        //this.setPosition(x, y);
    }

    public void bewegeHoch()
    {
        y--;
        //this.setPosition(x, y);
    }

    public boolean anWand(int wandX, int wandY)
    {
        return (x >= wandX || y >= wandY);
    }

    public boolean kannNachRechtsBewegen(ArrayList<Rechteck> hindernisse, int spielfeldBreite)
    {
        if (x + 1 >= spielfeldBreite)
        {
            return false;
        }
        for (Rechteck hindernis : hindernisse)
        {
            if (hindernis.contains(x + 1, y))
            {
                return false;
            }
        }
        return true;
    }

    public boolean kannNachUntenBewegen(ArrayList<Rechteck> hindernisse, int spielfeldHoehe)
    {
        if (y + 1 >= spielfeldHoehe)
        {
            return false;
        }

        for (Rechteck hindernis : hindernisse)
        {
            if (hindernis.contains(x, y + 1))
            {
                return false;
            }
        }
        return true;
    }

    public boolean kannNachLinksBewegen(ArrayList<Rechteck> hindernisse)
    {
        if (x - 1 < 0)
        {
            return false;
        }

        for (Rechteck hindernis : hindernisse)
        {
            if (hindernis.contains(x - 1, y))
            {
                return false;
            }
        }
        return true;
    }

    public boolean kannNachObenBewegen(ArrayList<Rechteck> hindernisse)
    {
        if (y - 1 < 0)
        {
            return false;
        }

        for (Rechteck hindernis : hindernisse)
        {
            if (hindernis.contains(x, y - 1))
            {
                return false;
            }
        }
        return true;
    }
}