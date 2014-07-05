
public class PowerObjekt implements ITastatur 
{
	Tastatur tastatur;
	int power;
	int x;
	int y;
	int xSize;
	int ySize;
	ITuWas link;
	int id;
	
	public PowerObjekt(int x_, int y_, int xsize, int ysize, ITuWas linkObjekt, int ID) 
	{
		x = x_;
		y = y_;
		xSize = xsize;
		ySize = ysize;
		tastatur = new Tastatur();
		tastatur.setzeLink(this);
		tastatur.meldeAnTaste("ENTER", "Enter");
		link = linkObjekt;
		id = ID;
	}
	
	public PowerObjekt(int x_, int y_, int xsize, int ysize) 
	{
		x = x_;
		y = y_;
		xSize = xsize;
		ySize = ysize;
		tastatur = new Tastatur();
		tastatur.setzeLink(this);
		tastatur.meldeAnTaste("ENTER", "Enter");
	}
	
	public void setzeLink(ITuWas linkObjekt, int ID)
	{
		link = linkObjekt;
		id = ID;
	}

	public void setzeLink(ITuWas linkObjekt)
	{
		link = linkObjekt;
		id = 1;
	}
	
	@Override
	public void tastenAktion(String rueckgabe) 
	{
		if(rueckgabe.equalsIgnoreCase("Enter"))
		{
			link.tuWas(id);
		}
	}
}
