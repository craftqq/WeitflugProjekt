import java.util.ArrayList;

public class GameLoop 
{
    public ArrayList<SpielObjekt> objekte = new ArrayList<SpielObjekt>();
    public Spieler spieler;
    public Hintergrund hintergrund;
    public boolean aktiv;
    public double a_x;
    public double v_x;
    public double a_y;
    public double v_y;
    public double s_x;
    public double s_y;

    public long zeitStart;
    public long zeitEnde;
    public long zeitLetzterSchritt;

    public void start()
    {
        aktiv = true;
        int i = 0;
        v_x = GameEngine.startV;
        v_y = 10.0D;
        hintergrund = new Hintergrund("hintergrund_boden.png", "hintergrund_mitte.png", "hintergrund_himmel.png", 0, 0, 500, 500);
        spieler = new Spieler(0, 300);
        //PowerObjekt power = new PowerObjekt(500, 100, 200, 50);
        //int powerInt = power.berechnePower();
        //PowerObjekt winkel = new PowerObjekt(500, 100, 180, 50);
        //int winkelInt = winkel.berechnePower() / 2;
        //double v = GameEngine.startV *((double) (powerInt / 200));
        //double a = GameEngine.startA *((double) (powerInt / 200));
        //v_x = Math.sin(((double) winkelInt) / 180.0D * Math.PI) * v;
        //v_y = Math.cos(((double) winkelInt) / 180.0D * Math.PI) * v;
        //a_x = Math.cos(((double) winkelInt) / 180.0D * Math.PI) * a;
        //a_y = Math.cos(((double) winkelInt) / 180.0D * Math.PI) * a;
        s_x = GameEngine.startX;
        s_y = GameEngine.startY;
        zeitStart = System.currentTimeMillis();
        zeitLetzterSchritt = zeitStart + 50L;
        while(aktiv)
        {
            long unterschied = System.currentTimeMillis() - zeitLetzterSchritt;
            zeitLetzterSchritt = System.currentTimeMillis();
            schritt(((double)unterschied) / 1000);
            if((i > 20) && (objekte.size() <= 20))
            {
                i = 0;
                erzeugeNeuesSpielObjekt();
            }
            i++;
            if((v_x == 0) && (s_y <= 0))
            {
                break;
            }
            System.out.println(s_y);
            System.out.println(v_y);
        }
        zeitEnde = System.currentTimeMillis();
        GameEngine.coins =GameEngine.coins + GameEngine.coinsPerM * s_x + GameEngine.coinsPerS * ((double)(zeitEnde - zeitStart)) / 1000;
        spieler.bildC.entfernen();
        spieler = null;
        hintergrund.entfernen();
        hintergrund = null;
        GameEngine.screens.get("Mainmenu").rufeAuf();
    }

    public void schritt(double delta_t)
    {
        double widerstandV = GameEngine.WiderstandV;
        if(Math.sqrt(v_x * v_x + v_y * v_y) > GameEngine.maxV)
        {
            widerstandV += GameEngine.widerstandZusatzV;
        }
        double a = Math.sqrt(a_x * a_x + (a_y) * (a_y));
        if(a > GameEngine.maxA)
        {
            double j = GameEngine.maxA / a;
            a_x = a_x * j;
            a_y = a_y * j;
        }
        double x = v_x * delta_t + 0.5D * a_x * delta_t * delta_t;
        double y = v_y * delta_t + 0.5D * a_y * delta_t * delta_t + 0.5D * GameEngine.normalG * delta_t * delta_t;
        s_x = s_x + x;
        s_y = s_y + y;
        v_x = v_x * (1.0D - widerstandV) + a_x * delta_t;
        v_y = v_y * (1.0D - widerstandV) + a_y * delta_t + GameEngine.normalG * delta_t;
        a_x = 0;
        a_y = 0;
        if(s_y <= 0)
        {
            s_y = 0;
            v_x = v_x * (1 - GameEngine.widerstandBoden);
            v_y = v_y * (1 - GameEngine.widerstandBoden);
            if(v_y > 0)
            {
                v_y = -v_y;
            }
        }
        if((v_y < 0.1D) && (v_y > -0.1D))
        {
            v_y = 0;
        }
        if((v_x < 0.1D) && (v_x > -0.1D))
        {
            v_x = 0;
        }
        for(SpielObjekt objekt : objekte)
        {
            objekt.bewege((int) x * 10, (int) -y * 10);
            if(objekt.gibHitBox().berechneZusammenstossMit(spieler.gibHitBox()))
            {
                objekt.beiZusammenstossMit(spieler);
            }
            if(objekt.gibPositionX() < 0)
            {
                objekt.bildC.entfernen();
                objekte.remove(objekt);
            }
        }
        hintergrund.bewege((int) x * 10, (int) -y * 10);
    }

    public void stop()
    {
        aktiv = false;
    }

    public void erzeugeNeuesSpielObjekt()
    {
        int objektInt = GameEngine.random.nextInt(1);
        if(objektInt == 1)
        {
            objekte.add(new Coin(500, GameEngine.random.nextInt(250)));
        }
        else
        {
            objekte.add(new Hinderniss(500, GameEngine.random.nextInt(250)));
        }
    }
}

class Spieler extends SpielObjekt
{

    public Spieler(int x, int y) 
    {
        super(x, y, GameEngine.xSize, GameEngine.ySize, "spieler.png");
    }

    @Override
    public boolean beiZusammenstossMit(SpielObjekt objekt) 
    {
        return false;
    }

}