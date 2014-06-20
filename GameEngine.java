import java.util.HashMap;


public class GameEngine 
{
    //Feste Objekte (werden nicht verändert)
    public static HashMap<String, IScreen> screens = new HashMap<String, IScreen>();  //Screens wie Hauptmenu, Einstellungen, etc
    public static SoundEngine soundEngine;  //Sound
    public static SoundEngine musicEngine;  //Musik
    public static PhysEngine physEngine;    //Physik und Interactionen von Objekten miteinander
    
    //Einstellungen
    public static boolean sound = true;  //true, falls Sound abgespielt werden soll
    public static boolean musik = true;  //true, falls Musik abgespielt werden soll
    public static float delta_t = 0.05F;  //Grösse der Berechnungsabschnitte in Sekunden
    
    //Objekte, die während des Spiels verändert werden, aber von Spiel zu Spiel erhalten bleiben
    public static int coins = 0;  //Anzahl an Coins (Spielwährung)
    
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
        load();
        physEngine = new PhysEngine(delta_t);
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
            musicEngine = new ActiveSoundEngine();
        }
        else
        {
            musicEngine = new SoundEngine();
        }
        
        screens.put("mainMenu", new MainMenu());
    }
    
    /**
     * startet das Spiel
     */
    public static void start()
    {
        screens.get("mainMenu").rufeAuf();
    }
    
    /**
     * beendet das Spiel
     */
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
     * lädt den Spielstand / Optionen
     */
    public static void load()
    {
        DateiLeser leser = new DateiLeser("options.txt");
        String[] optionDat = leser.lesen();
        for(String line : optionDat)
        {
            int equalPos = line.indexOf("=");
            if(equalPos != -1)
            {
                String option = line.substring(0, equalPos - 1).trim();
                String value = line.substring(equalPos + 1).trim();
                switch(option)
                {
                case "sound":
                    sound = value.equalsIgnoreCase("true");
                    break;
                case "musik":
                    musik = value.equalsIgnoreCase("true");
                    break;
                case "delta_t":
                    try
                    {
                        float f = Float.parseFloat(value);
                        delta_t = f;
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("delta_t-wert ist falsch formatiert; Einstellung wird ignoriert.");
                    }
                    break;
                default:
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
        String[] options = new String[3];
        options[0] = "sound = " + String.valueOf(sound);
        options[1] = "musik = " + String.valueOf(musik);
        options[2] = "delta_t = " + String.valueOf(delta_t);
        DateiSchreiber schreiber = new DateiSchreiber("options.txt");
        schreiber.schreiben(options);
    }
}
