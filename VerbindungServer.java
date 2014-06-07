import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

class ServerVerbindungen {
	ServerSocket server = null;
	int verbunden = 0;

	ServerVerbindungen(ServerSocket server) {
		this.server = server;
		verbunden = 1;
	}

	public void incVerbindung() {
		verbunden++;
	}

	public void decVerbindung() {
		verbunden--;
	}

	public boolean verbindungenVorhanden() {
		return (verbunden > 0);
	}

	protected void finalize() {
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
			}
		}
	}

}

public class VerbindungServer {

	public static final int ERROR = -1;

	// Die IOStreams
	protected InputStream inStream = null;
	protected OutputStream outStream = null;

	/** Datenstrom raus */
	private PrintWriter outString = null;
	/** Datenstrom rein */
	private BufferedReader inString = null;

	private static ArrayList<ServerVerbindungen> socketlist = new ArrayList<ServerVerbindungen>();

	private static synchronized ServerSocket getServer(int serverPort) {
		if (serverPort != 0) {
			for (ServerVerbindungen s : socketlist) {
				if (s.server.getLocalPort() == serverPort)
					s.incVerbindung();
				return s.server;
			}
		}
		ServerSocket server = null;
		try {
			server = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		VerbindungServer.addServer(server);

		return server;
	}

	private static void addServer(ServerSocket server) {
		socketlist.add(new ServerVerbindungen(server));
	}

	private static synchronized void serverEnfernen(ServerSocket server) {
		ServerVerbindungen erg = null;
		for (ServerVerbindungen s : socketlist) {
			if (s.server == server) {
				s.decVerbindung();
				if (!s.verbindungenVorhanden()) {
					erg = s;
				}
			}
		}
		if (erg != null) {
			socketlist.remove(erg);
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Server zu Port " + server.getLocalPort()
					+ " geschlossen");

			server = null;
		}
	}

	/**
	 * Server bei einem Serversocket
	 */
	protected ServerSocket server;

	/**
	 * Soket für die Übertragung
	 */
	protected Socket aufruf = null;

	/**
	 * Die Server-Portnummer
	 * 
	 * Wird bei neuerServerkanalAufbauen vom System vergeben
	 */
	protected int serverport = ERROR;

	/**
	 * Normaes IO-Modul
	 */
	private VerbindungServer() {
		aufruf = null;
		inStream = null;
		outStream = null;
	}
	
	
	// Server-IOModul mit neuem automatisch gewaehlten Serverport
	// wird als statische Methode installiert, damit auch NULL zurückkommen kann
	public static VerbindungServer getVerbindungServerPort() {
		return getVerbindungServerPort(0);
	}


	// Server-IOModul horcht am Port serverPort
	// wird als statische Methode installiert, damit auch NULL zurückkommen kann
	public static VerbindungServer getVerbindungServerPort(int serverPort) {
		VerbindungServer tmp = new VerbindungServer();
		tmp.server = VerbindungServer.getServer(serverPort);
		if (tmp.server != null) {
			return tmp;
		}
		return null;
	}

	protected void finalize() {
		close();
	}

	
	/*
	 * serverkanalAufbauen()
	 * 
	 * wartet auf Soccket-Anfrage vom Client
	 */
	public boolean warteAufClient() {
		return warteAufClient(0);
	}

	/*
	 * serverkanalAufbauen()
	 * 
	 * wartet auf Soccket-Anfrage vom Client
	 * 
	 * Nach timeout ms wird abgebrochen
	 */
	public boolean warteAufClient(int timeout) {
		if (server == null) {
			return false;
		} else {

			// Kommunikation aufbauen
			Socket socket = null;
			try {
				server.setSoTimeout(timeout);

				try {
					socket = server.accept();
				} catch (IOException e) {
					return false;
				}
			} catch (SocketException e1) {
				return false;
			}

			// Kanal zum Socket aufbauen
			verbindungAufbauen(socket);
			return true;
		}
	}

	/*
	 * serverkanalAufbauen()
	 * 
	 * wartet auf Soccket-Anfrage vom Client
	 * 
	 * Nach timeout ms wird abgebrochen
	 */
	public boolean warteAufClientSpezial(int timeout) {
		if (server == null) {
			return false;
		} else {

			// Kommunikation aufbauen
			Socket socket = null;
			try {
				server.setSoTimeout(timeout);
				try {
					socket = server.accept();
				} catch (IOException e) {
					return false;
				}
			} catch (SocketException e1) {
				return false;
			}

			// Kanal zum Socket aufbauen
			verbindungAufbauen(socket);
			return true;
		}
	}

	/**
	 * Erzeugt eine neue Serververbindung
	 * 
	 * Portnummer wird vom Betriebssystem zugeordnet
	 * 
	 * Sie kann mit getServerport abgefragt werden
	 * 
	 * @return serverport oder ERROR
	 */
	public int neuenServerportAufbauen() {
		try {
			server = new ServerSocket();
			server.bind(null);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}

		VerbindungServer.addServer(server);

		serverport = server.getLocalPort();

		if (warteAufClient()) {
			return serverport;
		} else {
			return ERROR;
		}
	}

	/**
	 * gibt die portnummer zurück
	 * 
	 * @return serverport
	 */
	public int getServerport() {
		return serverport;
	}

	/**
	 * Baut eine Verbindung über das gegebene Socket auf
	 * 
	 * @param socket
	 * @return Erfolg
	 */
	private boolean verbindungAufbauen(Socket socket) {
		try {
			if (aufruf != null) {
				aufruf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		aufruf = socket;
		try {
			inStream = aufruf.getInputStream();
			outStream = aufruf.getOutputStream();

			outString = new PrintWriter(outStream, true);
			inString = new BufferedReader(new InputStreamReader(inStream));

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Für Server:
	 * 
	 * gibt den Port des angerufenen Client zurück
	 * 
	 * @return clientPort
	 */
	public int getClientPort() {
		return aufruf.getPort();
	}

	/**
	 * gibt den eigenen Port zurück
	 * 
	 * @return serverport
	 */
	public int getLocalPort() {
		return server.getLocalPort();
	}

	/**
	 * Öffenliche IP des Servers
	 * 
	 * @param iPSTART
	 *            // Start der IP-Adresse bei mehreren Server-IP-Adressen
	 * @return
	 */
	public static String getNonLocalIP(String iPSTART) {
		if (iPSTART == null)
			iPSTART = "";
		String erg = "";
		int len = iPSTART.length();
		// Holt den Hostnamen
		try {
			String localHost = InetAddress.getLocalHost().getHostName();
			// Für jede Netzwerkkarte
			for (InetAddress ia : InetAddress.getAllByName(localHost)) {
				// IP-Adresse ausgeben
				String ip = ia.getHostAddress();
				System.out.println(ip);
				if (ip.substring(0, len).equals(iPSTART))
					return ip;
			}
		} catch (UnknownHostException e) {
			return null;
		}
		return erg;
	}

	public String getClientIP() {
		return aufruf.getInetAddress().getHostAddress();
	}

	public String getClientName() {
		return aufruf.getInetAddress().getHostName();
	}

	/**
	 * Schließt die Verbindung
	 * 
	 */
	public void close() {
		VerbindungServer.serverEnfernen(server);
	}

	/**
	 * Liest einen String
	 * 
	 * @return als String
	 */
	public String readLine() {
		try {
			return inString.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Schreibt einen String
	 * 
	 */
	public void writeLine(String zeile) {

		outString.println(zeile);
	}

	/**
	 * Lesen eines Integer-Werts
	 * 
	 * @return als int
	 */
	public int readInt(int iError) {
		String temp = readLine();
		int erg = iError;
		try {
			erg = Integer.valueOf(temp);
		} catch (NumberFormatException e) {
		}
		return erg;

	}

	/**
	 * Schreiben eines Integer-Werts
	 * 
	 */
	public void writeInt(int number) {
		writeLine("" + number);
	}

	/**
	 * Lesen eines Double-Werts
	 * 
	 * @return als double
	 */
	public double readDouble(double dError) {

		String temp = readLine();
		double erg = dError;
		try {
			erg = Double.valueOf(temp);
		} catch (NumberFormatException e) {
		}
		return erg;
	}

	/**
	 * Schreiben eines Double-Werts
	 * 
	 */
	public void writeDouble(double number) {
		writeLine("" + number);
	}

}
