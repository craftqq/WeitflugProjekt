import java.util.ArrayList;


public class GameEngine 
{
	//Feste Objekte (werden nicht ver�ndert)
	public static IScreen[] screens;
	
	//Objekte, die w�hrend des Spiels ver�ndert werden, aber von Spiel zu Spiel erhalten bleiben
	public static int coins;
	
	//Objekte, die nur w�hrend des Spiels ben�tigt werden und danach verfallen
	public static ArrayList<ISpielObjekt> spielObjekte;
	
	public static void main(String[] args)
	{
		start(args);
	}
	
	public static void start(String[] args)
	{
		
	}
	
	/**
	 * l�dt den Spielstand
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
