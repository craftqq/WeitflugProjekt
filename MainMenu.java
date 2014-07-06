
public class MainMenu implements IScreen 
{
    Taste exit;
    Taste spielen;
    Taste credits;
    Taste optionen;
    Taste shop;
    Taste achievements;
    Ausgabe coins;

    @Override
    public void tuWas(int ID) 
    {
        switch(ID)
        {
            case 0:
            this.schliesse();
            GameEngine.end();
            break;
            case 1:
            this.schliesse();
            GameEngine.screens.get("Spiel").rufeAuf();
            break;
            case 2:
            this.schliesse();
            GameEngine.screens.get("Optionen").rufeAuf();
            break;
            case 3:
            this.schliesse();
            GameEngine.screens.get("Shop").rufeAuf();
            break;
            default:
            break;
            case 5:
            this.schliesse();
            GameEngine.screens.get("Credits").rufeAuf();
            break;
        }
    }

    @Override
    public void rufeAuf() 
    {
        spielen= new Taste("Spielen", 500, 100, 200,50);
        optionen= new Taste("Optionen", 500,200,200,50);
        shop= new Taste("Shop",500,300,200,50);
        achievements= new Taste("Achievements", 500,400,200,50);
        credits= new Taste("Credits", 500,500,200,50);
        exit = new Taste("Exit", 500, 600, 200, 50);
        coins = new Ausgabe(((Integer) ((int)GameEngine.coins)).toString(), 10, 10, 100, 50);
        exit.setzeLink(this, 0);
        spielen.setzeLink(this, 1);
        optionen.setzeLink(this, 2);
        shop.setzeLink(this, 3);
        achievements.setzeLink(this, 4);
        credits.setzeLink(this, 5);
        GameEngine.musikEngine.spieleAudioEndlos("mainMenu.wav");

    }

    @Override
    public void schliesse() 
    {
        spielen.entfernen();
        spielen = null;
        credits.entfernen();
        credits= null;
        optionen.entfernen();
        optionen= null;
        shop.entfernen();
        shop= null;
        achievements.entfernen();
        achievements= null;
        exit.entfernen();
        exit = null;
        coins.entfernen();
        coins = null;
        GameEngine.musikEngine.stoppeAudio("mainMenu.wav");
    }

}
