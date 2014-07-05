
class Optionen implements IScreen{
    Taste sound;
    Taste musik;
    Taste hauptmenu;
    
    public void tuWas(int ID)
    {
        switch(ID)
        {
            case 0:
            this.schliesse();
            GameEngine.screens.get("Mainmenu").rufeAuf();
            break;
            case 1:
            if(GameEngine.sound)
            {
                GameEngine.soundEngine.stoppeAlle();
                GameEngine.soundEngine = new AudioPlayer();
                sound.setzeAusgabetext("Sound an");
            }
            else
            {
                GameEngine.soundEngine = new ActiveAudioPlayer();
                sound.setzeAusgabetext("Sound aus");
            }
            break;
            case 2:
            if(GameEngine.musik)
            {
                GameEngine.musikEngine.stoppeAlle();
                GameEngine.musikEngine = new AudioPlayer();
                musik.setzeAusgabetext("Musik an");
            }
                else
                {
                    GameEngine.musikEngine = new MusicLoop();
                    musik.setzeAusgabetext("Musik aus");
                }
                break;
                default:
                break;
            }
    }
    
    public void rufeAuf()
    {
        GameEngine.ueberschrift.setzeAusgabetext("Optionen");
        GameEngine.musikEngine.spieleAudioEndlos("mainMenu.wav");
        sound= new Taste();
        musik= new Taste();
        hauptmenu= new Taste();
        sound.setzeDimensionen(500,100,200,50);
        if(GameEngine.sound)
        {
        	sound.setzeAusgabetext("Sound aus");
        }
        else
        {
        	sound.setzeAusgabetext("Sound an");
        }
        sound.setzeHintergrundfarbe("lila");
        musik.setzeDimensionen(500,175,200,50);
        if(GameEngine.musik)
        {
        musik.setzeAusgabetext("Musik aus");
        }
        else
        {
        	musik.setzeAusgabetext("Musik an");
        }
        musik.setzeHintergrundfarbe("lila");
        hauptmenu.setzeDimensionen(500,250,200,50);
        hauptmenu.setzeAusgabetext("Hauptmenü");
        hauptmenu.setzeHintergrundfarbe("lila");
        sound.setzeLink(this, 1);
        musik.setzeLink(this, 2);
        hauptmenu.setzeLink(this, 0);
    }
    
    public void schliesse()
    {
        sound.entfernen();
        musik.entfernen();
        hauptmenu.entfernen();
        sound = null;
        musik = null;
        hauptmenu = null;
        GameEngine.ueberschrift.entfernen();
        GameEngine.ueberschrift= null;
    }
}