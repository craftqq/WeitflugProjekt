
public class Coin implements ISpielObjekt 
{
	public int xC;
	public int yC;
	public Bild coin;
	public Coin(int x, int y)
	{
		xC = x;
		yC = y;
		coin = new Bild(x, y, 16, 16, "coin.png");
	}
	
	@Override
	public boolean beiZusammenstossMit(ISpielObjekt objekt) 
	{
		if(objekt == GameEngine.physEngine.spieler)
		{
			GameEngine.coins++;
			return true;
		}
		return false;
	}

	@Override
	public void setzePosition(int x, int y) 
	{
		coin.setzePosition(x, y);

	}

	@Override
	public void bewege(int x, int y) 
	{
		coin.setzePosition(xC + x, yC + y);

	}

	@Override
	public HitBox gibHitBox() 
	{
		return new HitBox(xC, yC, 16, 16);
	}

	@Override
	public int gibPositionX() 
	{
		return xC;
	}

	@Override
	public int gibPositionY() 
	{
		return yC;
	}

}
