import java.util.ArrayList;


public class GameLoop 
{
	public ArrayList<SpielObjekt> objekte = new ArrayList<SpielObjekt>();
	public Spieler spieler = new Spieler(0, 300);
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
		double y = v_y * delta_t + 0.5D * a_y * delta_t * delta_t + 0.5D * GameEngine.normalG;
		s_x = s_x + x;
		s_y = s_y + y;
		v_x = v_x * (1.0D - widerstandV) + a_x * delta_t;
		v_y = v_y * (1.0D - widerstandV) + a_y * delta_t;
		for(SpielObjekt objekt : objekte)
		{
			objekt.bewege((int) x, (int) y);
			if(objekt.gibHitBox().berechneZusammenstossMit(spieler.gibHitBox()))
			{
				objekt.beiZusammenstossMit(spieler);
			}
		}
		hintergrund.bewege((int) x, (int) y);
	}
	
	public void stop()
	{
		aktiv = false;
	}
}

class Spieler extends SpielObjekt
{

	public Spieler(int x, int y) 
	{
		super(x, y, GameEngine.xSize, GameEngine.ySize, "spieler.png");
	}

	int xpos;
	int ypos;
	Bild bild;
	@Override
	public boolean beiZusammenstossMit(SpielObjekt objekt) 
	{
		return false;
	}

	@Override
	public void setzePosition(int x, int y) 
	{
		bild.setzePosition(x, y);
		xpos = x;
		ypos = y;
	}

	@Override
	public void bewege(int x, int y) 
	{
		bild.setzePosition(x, y);
		xpos = x;
		ypos = y;
	}

	@Override
	public HitBox gibHitBox() 
	{
		return new HitBox(xpos, ypos, GameEngine.xSize, GameEngine.ySize);
	}

	@Override
	public int gibPositionX() 
	{
		return xpos;
	}

	@Override
	public int gibPositionY() 
	{
		return ypos;
	}
	
}