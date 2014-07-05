
class Optionen implements IScreen{
    Taste Sound;
    Taste Musik;
    Taste Hauptmenue;
    
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
                GameEngine.soundEngine = new AudioPlayer ();
            }
            else
            {
                GameEngine.soundEngine = new ActiveAudioPlayer ();
            }
            break;
            case 2:
            if(GameEngine.musik)
            {
                GameEngine.musikEngine.stoppeAlle();
                GameEngine.musikEngine = new AudioPlayer ();
            }
                else
                {
                    GameEngine.musikEngine = new ActiveAudioPlayer ();
                }
                break;
                default:
                break;
            }
    }
    
    public void rufeAuf()
    {
        GameEngine.ueberschrift.setzeAusgabetext("Optionen");
        Sound= new Taste();
        Musik= new Taste();
        Hauptmenue= new Taste();
        Sound.setzeDimensionen(500,100,200,50);
        Sound.setzeAusgabetext("Sound an");
        Sound.setzeHintergrundfarbe("lila");
        Musik.setzeDimensionen(500,175,200,50);
        Musik.setzeAusgabetext("Musik an");
        Musik.setzeHintergrundfarbe("lila");
        Hauptmenue.setzeDimensionen(500,250,200,50);
        Hauptmenue.setzeAusgabetext("Hauptmenü");
        Hauptmenue.setzeHintergrundfarbe("lila");
        Sound.setzeLink(this, 1);
        Musik.setzeLink(this, 2);
        Hauptmenue.setzeLink(this, 0);
    }
    
    public void schliesse()
    {
        Sound.entfernen();
        Musik.entfernen();
        Hauptmenue.entfernen();
        Sound = null;
        Musik = null;
        Hauptmenue = null;
        GameEngine.ueberschrift.entfernen();
        GameEngine.ueberschrift= null;
    }
}