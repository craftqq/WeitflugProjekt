

public class Shop implements IScreen {
    Taste Anfang1;
    Taste Anfang2;
    Taste Anfang3;
    Taste Speed1;
    Taste Speed2;
    Taste Speed3;
    Taste Sprung1;
    Taste Sprung2;
    Taste Sprung3;
    Taste ges1;
    Taste ges2;
    Taste ges3;
    Taste Hauptmenue;
    Ausgabe Speed;
    Ausgabe ges;
    Ausgabe Anfang;
    Ausgabe Sprung;
    int steigerung_speed;
    int steigerung_sprung;
    int steigerung_ges;
    int steigerung_anfang;
    @Override
    public void schliesse() {
        // TODO Auto-generated method stubSpeed1.entfernen();
        Speed.entfernen();
        ges.entfernen();
        Anfang.entfernen();
        Sprung.entfernen();
        Speed1.entfernen();
        Speed2.entfernen();
        Speed3.entfernen();
        Sprung1.entfernen();
        Sprung2.entfernen();
        Sprung3.entfernen();
        Anfang1.entfernen();
        Anfang2.entfernen();
        Anfang3.entfernen();
        ges1.entfernen();
        ges2.entfernen();
        ges3.entfernen();
        Hauptmenue.entfernen();
        Speed2 = null;
        Speed1 = null;
        Speed3 = null;
        Sprung1 = null;
        Sprung2= null;
        Sprung3= null;
        Anfang1= null;
        Anfang2= null;
        Anfang3= null;
        ges1= null;
        ges2= null;
        ges3= null;
        Speed= null;
        Anfang= null;
        Sprung= null;
        ges= null;
        
    }

    @Override
    public void rufeAuf() {
        // TODO Auto-generated method stub
        GameEngine.ueberschrift.setzeAusgabetext("SHOP");
        Sprung= new Ausgabe();
        Speed= new Ausgabe();
        Anfang= new Ausgabe();
        ges= new Ausgabe();
        Hauptmenue= new Taste();
        Speed1= new Taste();
        Speed2= new Taste();
        Speed3= new Taste();
        Sprung1= new Taste();
        Sprung2= new Taste();
        Sprung3= new Taste();
        Anfang1= new Taste();
        Anfang2= new Taste();
        Anfang3= new Taste();
        ges1= new Taste();
        ges2= new Taste();
        ges3= new Taste();
        Hauptmenue.setzeDimensionen(550,500,200,50);
        Hauptmenue.setzeAusgabetext("Hauptmenü");
        Hauptmenue.setzeHintergrundfarbe("orange");
       
        Speed.setzeAusgabetext("Speedboost");
        Sprung.setzeAusgabetext("Sprungboost");
        Anfang.setzeAusgabetext("Startboost");
        ges.setzeAusgabetext("Geschwindigkeit");
        Speed.setzePosition(100,50);
        Sprung.setzePosition(400,50);
        Anfang.setzePosition(700,50);
        ges.setzePosition(1000,50);
        Speed.setzeGroesse(200,100);
        Anfang.setzeGroesse(200,100);
        ges.setzeGroesse(200,100);
        Sprung.setzeGroesse(200,100);
        Speed1.setzeDimensionen(100,150,200,50);
        Speed2.setzeDimensionen(100,250,200,50);
        Speed3.setzeDimensionen(100,350,200,50);

        Sprung1.setzeDimensionen(400,150,200,50);
        Sprung2.setzeDimensionen(400,250,200,50);
        Sprung3.setzeDimensionen(400,350,200,50);

        Anfang1.setzeDimensionen(700,150,200,50);
        Anfang2.setzeDimensionen(700,250,200,50);
        Anfang3.setzeDimensionen(700,350,200,50);

        ges1.setzeDimensionen(1000,150,200,50);
        ges2.setzeDimensionen(1000,250,200,50);
        ges3.setzeDimensionen(1000,350,200,50);
        Speed1.setzeAusgabetext("Steigerung 1");
        Speed2.setzeAusgabetext("Steigerung 2");
        Speed3.setzeAusgabetext("Steigerung 3");
        Anfang1.setzeAusgabetext("Steigerung 1");
        Anfang2.setzeAusgabetext("Steigerung 2");
        Anfang3.setzeAusgabetext("Steigerung 3");
        ges1.setzeAusgabetext("Steigerung 1");
        ges2.setzeAusgabetext("Steigerung 2");
        ges3.setzeAusgabetext("Steigerung 3");
        Sprung1.setzeAusgabetext("Steigerung 1");
        Sprung2.setzeAusgabetext("Steigerung 2");
        Sprung3.setzeAusgabetext("Steigerung 3");
        Speed1.setzeLink(this, 11);
        Speed2.setzeLink(this, 12);
        Speed3.setzeLink(this, 13);
        Sprung1.setzeLink(this, 14);
        Sprung2.setzeLink(this, 15);
        Sprung3.setzeLink(this, 16);
        Anfang1.setzeLink(this, 17);
        Anfang2.setzeLink(this, 18);
        Anfang3.setzeLink(this, 19);
        ges1.setzeLink(this, 20);
        ges2.setzeLink(this, 21);
        ges3.setzeLink(this, 22);
        Hauptmenue.setzeLink(this, 23);
        steigerung_speed=0;
        steigerung_sprung=0;
        steigerung_ges=0;
        steigerung_anfang=0;

    }

    @Override
    public void tuWas(int ID) {
        // TODO Auto-generated method stub
        switch(ID)
        {
            case 11:
            steigerung_speed=1;
            break;
            case 12:
            steigerung_speed=2;
            break;
            case 13:
            steigerung_speed=3;
            break;
            case 14:
            steigerung_sprung=1;
            break;
            case 15:
            steigerung_sprung=2;
            break;
            case 16:
            steigerung_sprung=3;
            break;
            case 17:
            steigerung_sprung=1;
            break;
            case 18:
            steigerung_anfang=2;
            break;
            case 19:
            steigerung_anfang=3;
            break;
            case 20:
            steigerung_anfang=1;
            break;
            case 21:
            steigerung_ges=2;
            break;
            case 22:
            steigerung_ges=3;
            break;

            case 23:
            this.schliesse();
            GameEngine.screens.get("Mainmenu").rufeAuf();
            break;

        }

    }
}
class SpeedStart extends Upgrade
{
	int[] price;
	double[] menge;
	public SpeedStart( int x, int y, int xsize, int ysize) 
	{
		super(3, x, y, xsize, ysize, "Erhöhe Startgeschwindigkeit");
		price = new int[]{50, 200, 500};
		menge = new double[]{15, 25, 40};
	}

	@Override
	public boolean upgrade() 
	{
		if(GameEngine.coins < price[level])
		{
			return false;
		}
		else
		{
			GameEngine.coins -= price[level];
			GameEngine.startV += menge[level];
			return true;
		}
	}
	
}