import java.util.HashMap;


public class GameEngine 
{
    //Feste Objekte (werden nicht ver�ndert)
    public static HashMap<String, IScreen> screens = new HashMap<String, IScreen>();  //Screens wie Hauptmenu, Einstellungen, etc
    public static SoundEngine soundEngine;  //Sound
    public static SoundEngine musikEngine;  //Musik
    public static PhysEngine physEngine;    //Physik und Interactionen von Objekten miteinander
    public static Ausgabe ueberschrift = new Ausgabe("", 500,0,200,50);
    
    //Einstellungen
    public static boolean sound = true;  //true, falls Sound abgespielt werden soll
    public static boolean musik = true;  //true, falls Musik abgespielt werden soll
    public static float delta_t = 0.05F;  //Gr�sse der Berechnungsabschnitte in Sekunden
    
    //Objekte, die w�hrend des Spiels ver�ndert werden, aber von Spiel zu Spiel erhalten bleiben
    public static int coins = 0;  //Anzahl an Coins (Spielw�hrung)
    
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
        physEngine = new PhysEngine(delta_t); //erstellt den physEngine
        if(sound)
        {
            soundEngine = new ActiveSoundEngine();
        }
        else
        {
            soundEngine = new SoundEngine();
        }
        
        if(musik)
        {
            musikEngine = new ActiveSoundEngine();
        }
        else
        {
            musikEngine = new SoundEngine();
        }
        
        screens.put("Mainmenu", new MainMenu()); //f�gt das hauptmenu zur screensMap hinzu
        screens.put("Optionen", new Optionen());
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
                case "delta_t":											 //option delta_t; ist float, liest den wert aus der Datei
                    try													 //versucht den String in float umzuwandeln (exception bei falscher
                    {													 //formatierung
                        float f = Float.parseFloat(value);				 //  -//-
                        delta_t = f;									 //  -//-
                    }
                    catch(NumberFormatException e)						 //f�ngt die exception auf
                    {
                        System.out.println("delta_t-wert ist falsch formatiert; Einstellung wird ignoriert.");  //benachrichtigung
                    }
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
        options[2] = "delta_t = " + String.valueOf(delta_t);	//f�gt den wert f�r delta_t hinzu
        DateiSchreiber schreiber = new DateiSchreiber("options.txt");//erstellt den DateiSchreiber f�r "options.txt"
        schreiber.schreiben(options);							//schreibt die optionen
    }
}
