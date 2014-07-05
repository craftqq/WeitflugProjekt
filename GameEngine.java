import java.util.HashMap;


public class GameEngine 
{
    //Feste Objekte (werden nicht ver�ndert)
    public static HashMap<String, IScreen> screens = new HashMap<String, IScreen>();  //Screens wie Hauptmenu, Einstellungen, etc
    public static AudioPlayer  soundEngine = new AudioPlayer ();  //Sound
    public static AudioPlayer  musikEngine = new AudioPlayer ();  //Musik
    public static GameLoop physEngine = new GameLoop();    //Physik und Interactionen von Objekten miteinander
    public static Ausgabe ueberschrift = new Ausgabe("", 500,0,200,50);
    
    //Einstellungen
    public static boolean sound = true;  //true, falls Sound abgespielt werden soll
    public static boolean musik = true;  //true, falls Musik abgespielt werden soll
    
    //Spieleigenschaften
    public static double normalG = 9.81D;
    public static double maxV = 50.0D;
    public static double maxA = 10.0D;
    
    public static double WiderstandV = 0.05D;
    public static double widerstandZusatzV = 0.5D;
    
    public static double startV = 40.0D;
    public static double startA = 0.0D;
    
    public static double coinsPerS = 1.0D;
    public static double coinsMultiplier  = 1.0D;
    public static double coinsPerM = 1.0D;
    
    public static int xSize = 20;
    public static int ySize = 20;
    
    
    //Objekte, die w�hrend des Spiels ver�ndert werden, aber von Spiel zu Spiel erhalten bleiben
    public static double coins = 0;  //Anzahl an Coins (Spielw�hrung)
    
    public static void main(String[] args)
    {
        setup();
        start();
    }
    
    /**
     * Alles, was vor dem Spielstart erledigt werden muss (Objekte initialisieren etc)
     */
    public static void setup()
    {
        load();		//l�dt optionen / spielstand
        if(sound)
        {
            soundEngine = new ActiveAudioPlayer ();
        }
        else
        {
            soundEngine = new AudioPlayer ();
        }
        
        if(musik)
        {
            musikEngine = new ActiveAudioPlayer ();
        }
        else
        {
            musikEngine = new AudioPlayer ();
        }
        
        screens.put("Mainmenu", new MainMenu()); //f�gt das hauptmenu zur screensMap hinzu
        screens.put("Optionen", new Optionen());
        screens.put("Achievements", new Achievements());
        screens.put("Shop", new Shop());
        screens.put("Credits", new Credits());
        screens.put("Spiel", new Spiel());
    }
    
    /**
     * startet das Spiel
     */
    public static void start()
    {
        screens.get("Mainmenu").rufeAuf(); //�ffnet das Hauptmenu
    }
    
    /**
     * beendet das Spiel
     */
    public static void end()
    {
        physEngine.stop();			//stoppt den physEngine
        soundEngine.stoppeAlle();	//stoppt den soundEngine
        musikEngine.stoppeAlle();	//stoppt den musikEngine (SoundEngine f�r musik)
        Object[] screenObjects = screens.values().toArray(); //erstellt ein Array f�r die screens und f�llt diese mit den screens aus der map
        for(Object screen : screenObjects)					 //geht alle screens durch
        {
            try
            {
                ((IScreen) screen).schliesse();				 //versucht, den screen zu schliessen
            }
            catch(Exception e)		//f�ngt m�gliche exceptions auf
            {
                
            }
        }
        save();						//speichert
        System.exit(0);				//beendet das spiel
    }
    
    /**
     * l�dt den Spielstand / Optionen
     */
    public static void load()
    {
        DateiLeser leser = new DateiLeser("options.txt"); //der DateiLeser, um die Optionen einzulesen
        String[] optionDat = leser.lesen();               //lesen der Optionen
        for(String line : optionDat)
        {
            int equalPos = line.indexOf("=");       //Postion von "=" bestimmen, damit die Option / der wert zugeordnet werden kann
            if(equalPos != -1)						//equalPos ist -1, falls "=" nicht vorhanden ist (und die zeile damit ung�ltig)
            {
                String option = line.substring(0, equalPos - 1).trim();  //k�rzt die Leerzeichen weg
                String value = line.substring(equalPos + 1).trim();      //  -//-
                switch(option)											 //geht die einzelnen zeilen mit optionen durch
                {
                case "sound":											 //option Sound
                    sound = value.equalsIgnoreCase("true");				 //setzt sound auf true, falls der wert dem String "true"
                    break;												 //entspricht
                case "musik":											 //option musik; ist wie sound boolean
                    musik = value.equalsIgnoreCase("true");				 // (String true setze musik auf true)
                    break;
                default:			//wird ausgef�hrt, wenn keine option dem String (option) entspricht
                    break;
                }
            }
        }
    }
    
    /**
     * speichert den spielstand / Optionen
     */
    public static void save()
    {
        String[] options = new String[3];						//erstellt ein neues Array f�r die optionen
        options[0] = "sound = " + String.valueOf(sound);		//f�gt den wert f�r sound hinzu
        options[1] = "musik = " + String.valueOf(musik);		//f�gt den wert f�r musik hinzu
        DateiSchreiber schreiber = new DateiSchreiber("options.txt");//erstellt den DateiSchreiber f�r "options.txt"
        schreiber.schreiben(options);							//schreibt die optionen
    }
}
