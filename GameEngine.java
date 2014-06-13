import java.util.ArrayList;


public class GameEngine 
{
	//Feste Objekte (werden nicht verändert)
	public static IScreen[] screens;
	
	//Objekte, die während des Spiels verändert werden, aber von Spiel zu Spiel erhalten bleiben
	public static int coins;
	
	//Objekte, die nur während des Spiels benötigt werden und danach verfallen
	public static ArrayList<ISpielObjekt> spielObjekte;
	
	public static void main(String[] args)
	{
		start(args);
	}
	
	public static void start(String[] args)
	{
		
	}
	
	/**
	 * lädt den Spielstand
	 */
	public static void load()
	{
		
	}
	
	/**
	 * speichert den spielstand
	 */
	public static void save()
	{
		
	}
}
