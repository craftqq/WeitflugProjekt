import java.util.ArrayList;


public class GameLoop 
{
	public ArrayList<ISpielObjekt> objekte = new ArrayList<ISpielObjekt>();
	public ISpielObjekt spieler = new Spieler();
	
	public GameLoop()
	{
		
	}
	
	public void stop()
	{
		
	}
}
class Spieler implements ISpielObjekt
{

	int xpos;
	int ypos;
	Bild bild;
	@Override
	public boolean beiZusammenstossMit(ISpielObjekt objekt) 
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