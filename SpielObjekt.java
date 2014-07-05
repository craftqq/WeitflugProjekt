
public abstract class SpielObjekt
{

	public int xC;
	public int yC;
	public int xSize;
	public int ySize;
	public Bild bildC;
	
	public SpielObjekt(int x, int y, int xsize, int ysize, Bild bild)
	{
		xC = x;
		yC = y;
		xSize = xsize;
		ySize = ysize;
		bildC = bild;
	}
	
	public SpielObjekt(int x, int y, int xsize, int ysize, String bildName)
	{
		xC = x;
		yC = y;
		xSize = xsize;
		ySize = ysize;
		bildC = new Bild(x, y, xsize, ysize, bildName);
	}
	
	public abstract boolean beiZusammenstossMit(SpielObjekt objekt);

	public void setzePosition(int x, int y) 
	{
		bildC.setzePosition(x, y);

	}

	public void bewege(int x, int y) 
	{
		bildC.setzePosition(xC + x, yC + y);

	}

	public HitBox gibHitBox() 
	{
		return new HitBox(xC, yC, xSize, ySize);
	}

	public int gibPositionX() 
	{
		return xC;
	}

	public int gibPositionY() 
	{
		return yC;
	}
}
