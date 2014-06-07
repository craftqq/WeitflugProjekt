/**
 * Serverimplementierung - Echo
 * 
 * @author Hans Witt
 * @version 1.0
 * 
 */
public class Echo_SERVER {

    /**
     * bidirektionale Schnittstelle zur Netzwerkprotokoll-Implementierung des
     * Servers
     */
    private VerbindungServer verbindung = null;

    private Console console = null;

    /** Botschaft von Client zum Server */
    private String clientBotschaft = null;
    /** Botschaft vom Server zum Client */
    private String serverAntwort = null;

    /**
     * Konstruktor des Servers
     * 
     */
    public Echo_SERVER() {

        ServerStarten();

        ClientVerbindungStarten(); // auf Client warten und verbinden

        Serverprozess();
    }

    /**
     * fragt den Port ab und erzeugt den Serversocket
     */
    private void ServerStarten() {

        console = new Console();

        console.println("Port eingeben: ");

        // Serverport einlesen
        int port = console.readInt();

        // Servermodul erzeugen
        // Wenn Verbindung zum Port nicht m�glich wird null zur�ckgegeben
        // Mit new ... w�re das nicht m�glich
        verbindung = VerbindungServer.getVerbindungServerPort(port);

        console.println("Server gestartet...");

    }

    /**
     * wartet auf eine Clientverbindung warten und erzeugt die n&ouml;tigen
     * Lese- und Schreibobjekte nach dem eine Verbindung hergestellt wurde
     */
    private void ClientVerbindungStarten() {

        // Wartet auf Verbindungswunsch vom Client
        verbindung.warteAufClient();

        // Begrue&szlig;ung
        
        verbindung
                .writeLine("Server v    erbunden. Ende durch Eingabe von 'ENDE'.");
        console.println("Client verbunden");
    }

    public void Serverprozess() {
        do {// lesen und antworten

            clientBotschaft = verbindung.readLine();
            System.out.println(clientBotschaft);
            // Beim Spiegelserver ist die Antwort gleich der Frage
            serverAntwort = clientBotschaft;

            verbindung.writeLine(serverAntwort);

        } while (!serverAntwort.equals("ENDE"));

        VerbindungBeenden();
    }

    /**
     * schlie&szlig;t den Serversocket
     */
    private void VerbindungBeenden() {
        verbindung.close();
        console.println("Server gestoppt...");
        console.close();
    }

    /**
     * Hauptprogramm zum Erzeugen des Serverobjekts
     * 
     * @param args
     *            keine Parameter beim Programmaufruf erforderlich
     */

}
