package hilfsklassen;
import java.io.*;

/**
 * Clientimplementierung - Echo
 * 
 * @author Hans Witt
 * @version 1.0
 * 
 */
public class Echo_CLIENT {

    /** bidirektionale Schnittstelle zur Netzwerkprotokoll-Implementierung*/
    private VerbindungClient verbindung = null;

    /** Datenstrom von der Tastatur*/
    private Console console = null ;
    /** Eingabe von Client an Server*/
    private String clientEingabe;

    /** Botschaft vom Server*/ 
    private String serverBotschaft;

    /**
     * Konstruktor
     * @exception IOException eine Ausnahme tritt m&ouml;glicherweise auf falls:<br/>
     * - die Clientverbindung nicht hergestellt werden konnte (beispielsweise bei falscher IP-Adresse oder
     * falschem Port)<br/>
     * - die Verbindung zum Server gest&ouml;rt bzw. unterbrochen wurde.
     */
    public Echo_CLIENT() {

        VerbindungHerstellen();

        //Tastatureingabe, Senden und Empfangen
        while (((serverBotschaft = verbindung.readLine()) != null) && (!serverBotschaft.equals("ENDE"))) {

            //Serverbotschaft anzeigen.
            console.println("Server: " + serverBotschaft);

            //Eingabe von Client-Console lesen
            clientEingabe = console.readLine();

            //auf die Clientkonsole ausgeben
            console.println("Client: " + clientEingabe);
            
            //und zum Server schicken
            verbindung.writeLine(clientEingabe);
        }

        VerbindungBeenden();
    }

    /**
     * stellt die Serververbindung her und erzeugt die n&ouml;tigen Lese- und Schreibojekte
     * @exception IOException tritt auf, falls die Verbindung nicht korrekt erstellt werden kann.
     */
    private void VerbindungHerstellen()  {
        //Ipadresse und Port ermitteln
        console  = new Console();

        console.println("IP-Adresse des Servers eingeben:");
        String serverIPadresse = console.readLine();

        console.println("Port eingeben:");
        int serverPort = console.readInt();

        //Verbindung herstellen zum Server
        verbindung = new VerbindungClient(serverIPadresse, serverPort);
        
        
    }

    /**
     * beendet alle Lese- und Schreibkan&auml;le und die Verbindung zum Server
     * @exception IOException tritt auf, falls eine Verbindung oder ein Stream nicht beendet werden kann.
     */
    private void VerbindungBeenden() {
        verbindung.close();
        console.close();
    }

    /**
     * Hauptprogramm zum Erzeugen eines Clientobjekts
     * @param args keine Parameter beim Programmaufruf erforderlich
     */

}
