


public class Hintergrund extends SpielObjekt
{
    Bild[] himmel = new Bild[3];
    Bild[] mitte = new Bild[3];
    Bild[] boden = new Bild[3];
    int xPos;
    int yPos;
    int xSize;
    int ySize;
    
    public Hintergrund(String bildNameBoden, String bildNameMitte, String bildNameHimmel, int xpos, int ypos, int xsize, int ysize)
    {
    	super(xpos, ypos, xsize, ysize, bildNameMitte);
        for(int i = 0; i < 3; i++)
        {
            mitte[i] = new Bild(xpos + xsize * (i-1), ypos, xsize, ysize, bildNameMitte);
            boden[i]= new Bild(xpos + xsize * (i-1), ypos + ysize, xsize, ysize, bildNameBoden);
            himmel[i] = new Bild(xpos + xsize * (i-1), ypos - ysize, xsize, ysize, bildNameHimmel);
        }
        xPos = xpos;
        yPos = ypos;
        xSize = xsize;
        ySize = ysize;
        this.bildC.entfernen();
        this.bildC = null;
    }
    
    @Override
    public boolean beiZusammenstossMit(SpielObjekt objekt) 
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
        
        for(int i = 0; i < 3; i++)
        {
            mitte[i].setzePosition(xPos + (i - 1) * xSize, yPos);
            himmel[i].setzePosition(xPos + (i - 1) * xSize, yPos - ySize);
            boden[i].setzePosition(xPos + (i - 1) * xSize, yPos + ySize);
        }
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
    
    public void entfernen()
    {
    	for(Bild bild : himmel)
    	{
    		bild.entfernen();
    		bild = null;
    	}
    	for(Bild bild : boden)
    	{
    		bild.entfernen();
    		bild = null;
    	}
    	for(Bild bild : mitte)
    	{
    		bild.entfernen();
    		bild = null;
    	}
    }

}
