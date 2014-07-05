public class Coin extends SpielObjekt
{
	public int wert;
	
	public Coin(int x, int y) 
	{
		super(x, y, 16, 16, "coin.png");
		wert = 10;
	}
	
	public Coin(int x, int y, int werT) 
	{
		super(x, y, 16, 16, "coin.png");
		wert = werT;
	}

	@Override
	public boolean beiZusammenstossMit(SpielObjekt objekt) 
	{
		if(objekt == GameEngine.physEngine.spieler)
		{
			GameEngine.coins = wert * GameEngine.coinsMultiplier;
			return true;
		}
		return false;
	}
	
}