
/**
 * Einfache Eingabe über die Konsole.
 * 
 * @author W. Cimander
 */
import java.util.Scanner;

public class Eingabe
{
    /**
     * Liefert ein int zurück, dass der Benutzer per Konsole eingibt.
     * Bei falschen Eingaben wird der Benutzer wiederholt aufgefordert.
     * @param promt Die angezeigte Eingabeaufforderung
     * @return Die int-Zahl.
     */
    public static int gibInt(String promt)
    {
        int i=0;
        boolean eingabeOK=true;
        Scanner scan = new Scanner(System.in);
        
        do {
            eingabeOK=true;
            System.out.print(promt);
            String eingabe = scan.nextLine();
            try {
                i = Integer.parseInt(eingabe);
            } catch (Exception e) {
               System.out.println("Falsche Eingabe! "); 
               eingabeOK=false;
            }
        } while (!eingabeOK);
        scan.close();
        return i;
    }

    public static int gibInt() {
        return gibInt("");
    }
    
    /**
     * Liefert ein double zurück, dass der Benutzer per Konsole eingibt.
     * Bei falschen Eingaben wird der Benutzer wiederholt aufgefordert.
     * @param promt Die angezeigte Eingabeaufforderung
     * @return Die double-Zahl.
     */
    public static double gibDouble(String promt)
    {
        double d=0;
        boolean eingabeOK=true;
        Scanner scan = new Scanner(System.in);
        
        do {
            eingabeOK=true;
            System.out.print(promt);
            String eingabe = scan.nextLine();
            try {
                d = Double.parseDouble(eingabe);
            } catch (Exception e) {
               System.out.println("Falsche Eingabe! "); 
               eingabeOK=false;
            }
        } while (!eingabeOK);
        scan.close();
        return d;
    }

    public static double gibDouble() {
        return gibDouble("");
    }
    
    /**
     * Liefert einen string zurück, den der Benutzer per Konsole eingibt.
     * @param promt Die angezeigte Eingabeaufforderung
     * @return Der String
     */
    public static String gibString(String promt)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print(promt);
        String s = scan.nextLine();
        scan.close();
        return s;
    }

    public static String gibString() {
        return gibString("");
    }
    
    /**
     * Liefert ein char zurück, dass der Benutzer per Konsole eingibt.
     * Bei falschen Eingaben wird der Benutzer wiederholt aufgefordert.
     * @param promt Die angezeigte Eingabeaufforderung
     * @return Das char
     */
    public static char gibChar(String promt) {
        Scanner scan = new Scanner(System.in);
        System.out.print(promt);
        while (true) {
            String s = scan.nextLine();
            if (s.length()>0) {
            	scan.close();
                return s.charAt(0);
            } else {
                System.out.println("Bitte ein Zeichen eingeben! ");
            }
        }
     }

    public static char gibChar() {
        return gibChar("");
    }

    /**
     * Liefert ein boolean zurück, dass der Benutzer per Konsole eingibt.
     * Bei falschen Eingaben wird der Benutzer wiederholt aufgefordert.
     * mögliche Eingaben: wahr/falsch, true/false, w/f, t/f (bzw. in Großbuchstaben)
     * @param promt Die angezeigte Eingabeaufforderung
     * @return Der Wahrheitswert
     */public static boolean gibBoolean(String promt) {
        boolean b=false;
        boolean eingabeOK=true;
        Scanner scan = new Scanner(System.in);

        do {
            eingabeOK=true;
            System.out.print(promt);
            String eingabe = scan.nextLine();
            if (eingabe.equalsIgnoreCase("true") || eingabe.equalsIgnoreCase("wahr")
                    || eingabe.equalsIgnoreCase("w") || eingabe.equalsIgnoreCase("t")) {
                b = true;
            }
            else if (eingabe.equalsIgnoreCase("false") || eingabe.equalsIgnoreCase("falsch")
                    || eingabe.equalsIgnoreCase("f")) {
                b = false;
            } else {
                eingabeOK = false;
                System.out.println("Bitte wahr oder falsch eingeben: ");
            }
        } while (!eingabeOK);
        scan.close();
        return b;
    }

    public static boolean gibBoolean() {
        return gibBoolean("");
    }


}
