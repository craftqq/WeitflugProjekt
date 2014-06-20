


public class Hintergrund implements ISpielObjekt 
{
	Bild hintergrund;
	Bild hintergrund2;
	int xPos;
	int yPos;
	int xSize;
	int ySize;
	
	public Hintergrund(String bildName, int xpos, int ypos, int xsize, int ysize)
	{
		hintergrund = new Bild(bildName);
		hintergrund.setzeDimensionen(xpos, ypos, xsize, ysize);
		hintergrund2 = new Bild(bildName);
		hintergrund2.setzeDimensionen(xpos + xsize, ypos, xsize, ysize);
		xPos = xpos;
		yPos = ypos;
		xSize = xsize;
		ySize = ysize;
	}
	
	@Override
	public boolean beiZusammenstossMit(ISpielObjekt objekt) 
	{
		return false;
	}

	@Override
	public void setzePosition(int x, int y) 
	{
		xPos = x;
		yPos = y;
		if(xPos > 0)
		{
			while(xPos > 0)
			{
				xPos -= xSize;
			}
			if(xPos < -xSize)
			{
				while(xPos < -xSize)
				{
					xPos += xSize;
				}
			}
		}
		hintergrund.setzePosition(xPos, yPos);
		hintergrund2.setzePosition(xPos + xSize, yPos);
	}

	@Override
	public void bewege(int x, int y) 
	{
		setzePosition(x + xPos, y + yPos);
	}

	@Override
	public HitBox gibHitBox() 
	{
		return null;
	}

	@Override
	public int gibPositionX() 
	{
		return xPos;
	}

	@Override
	public int gibPositionY() 
	{
		return yPos;
	}

}
