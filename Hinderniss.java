
public class Hinderniss extends SpielObjekt 
{


	public Hinderniss(int x, int y) 
	{
		super(x, y, 20, 30, "hinderniss.png");
	}

	@Override
	public boolean beiZusammenstossMit(SpielObjekt objekt) 
	{
		if(objekt == GameEngine.loop.spieler)
		{
			GameEngine.loop.v_x *= GameEngine.WiderstandV;
		}
		return false;
	}

}
