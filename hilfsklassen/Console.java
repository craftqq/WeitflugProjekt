package hilfsklassen;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Die Klasse <b>Console</b> kapselt Ein- und Ausgabe von Text und Zahlen. <BR />
 * Eingabe von Text und Zahlen erfolgt immer zeilenweise. <BR /><BR />
 * 
 * Die Ausgabe (der Vollständigkeit halber) kapselt System.out.print.<br /><BR />
 *  
 * Die Eingabe kann als <b>int</b> oder <b>double</b> weitergegeben werden.  <BR />
 * Fehler (try/catch) werden abgefangen.<br />
 * 
 * @author Witt
 * 
 */
public class Console {
	
	public static int	ERROR	= -1;
	
	public static void setERROR(int error) {
		ERROR = error;
	}
	
	/** Datenstrom von der Tastatur */
	private BufferedReader	tastatur	= null;
	
	public Console() {
		tastatur = new BufferedReader(new InputStreamReader(System.in));
	};
	
	public String readLine() {
		try {
			return tastatur.readLine();
		} catch (IOException e) {
		}
		return "";
	}
	
	public int readInt() {
		try {
			return Integer.parseInt(tastatur.readLine());
		} catch (Exception e) {
		}
		return ERROR;
	}
	
	public double readDouble() {
		try {
			return Double.parseDouble(tastatur.readLine());
		} catch (Exception e) {
		}
		return ERROR;
	}
	
	public void println() {
		System.out.println();
	}
	
	public void println(String text) {
		print(text);
		println();
	}
	
	public void print(String text) {
		System.out.print(text);
	}
	
	public void printlnInt(int number) {
		println("" + number);
	}
	
	public void printInt(int number) {
		print("" + number);
	}
	
	public void printlnDouble(double number) {
		println("" + number);
	}
	
	public void printDouble(double number) {
		print("" + number);
	}
	
	public void close() {
		try {
			tastatur.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
