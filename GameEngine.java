import java.util.ArrayList;
import java.util.HashMap;


public class GameEngine 
{
	//Feste Objekte (werden nicht ver�ndert)
	public static HashMap<String, IScreen> screens = new HashMap<String, IScreen>();
	public static SoundEngine soundEngine;
	public static SoundEngine musicEngine;
	public static PhysEngine physEngine;
	
	//Einstellungen
	public static boolean sound = true;
	public static boolean music = true;
	
	//Objekte, die w�hrend des Spiels ver�ndert werden, aber von Spiel zu Spiel erhalten bleiben
	public static int coins = 0;
	
	//Objekte, die nur w�hrend des Spiels ben�tigt werden und danach verfallen
	public static ArrayList<ISpielObjekt> spielObjekte;
	
	public static void main(String[] args)
	{
		setup();
		start();
	}
	
	public static void setup()
	{
		load();
		physEngine = new PhysEngine();
		if(sound)
		{
			soundEngine = new ActiveSoundEngine();
		}
		else
		{
			soundEngine = new SoundEngine();
		}
		
		if(music)
		{
			musicEngine = new ActiveSoundEngine();
		}
		else
		{
			musicEngine = new SoundEngine();
		}
		
		screens.put("mainMenu", new MainMenu());
	}
	
	public static void start()
	{
		screens.get("mainMenu").rufeAuf();
	}
	
	public static void end()
	{
		physEngine.stop();
		soundEngine.stoppeAlle();
		Object[] screenObjects = screens.values().toArray();
		for(Object screen : screenObjects)
		{
			try
			{
				((IScreen) screen).schliesse();
			}
			catch(Exception e)
			{
				
			}
		}
		save();
		System.exit(0);
	}
	
	/**
	 * l�dt den Spielstand / Optionen
	 */
	public static void load()
	{
		
	}
	
	/**
	 * speichert den spielstand / Optionen
	 */
	public static void save()
	{
		
	}
}
