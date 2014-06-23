
class Optionen implements IScreen{
    Taste Sound;
    Taste Musik;
    Taste Hauptmenü;
    Taste Sound2;
    Taste Musik2;
    
    public void tuWas(int ID)
    {
        
    }
    
    public void rufeAuf()
    {
        GameEngine.ueberschrift.setzeAusgabetext("Optionen");
        Sound= new Taste();
        Musik= new Taste();
        Sound2= new Taste();
        Musik2=new Taste();
        Hauptmenü= new Taste();
        Sound.setzeDimensionen(375,100,200,50);
        Sound.setzeAusgabetext("Sound an");
        Sound.setzeHintergrundfarbe("lila");
        Musik.setzeDimensionen(375,175,200,50);
        Musik.setzeAusgabetext("Musik an");
        Musik.setzeHintergrundfarbe("lila");
        Hauptmenü.setzeDimensionen(500,250,200,50);
        Hauptmenü.setzeAusgabetext("Hauptmenü");
        Hauptmenü.setzeHintergrundfarbe("lila");
        Sound2.setzeDimensionen(625,100,200,50);
        Sound2.setzeAusgabetext("Sound aus");
        Musik2.setzeDimensionen(625,175,200,50);
        Musik2.setzeAusgabetext("Musik aus");
        Musik2.setzeHintergrundfarbe("lila");
        Sound2.setzeHintergrundfarbe("lila");
    }
    
    public void schliesse()
    {
        
    }
}