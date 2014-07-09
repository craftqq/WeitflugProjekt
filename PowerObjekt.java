
public class PowerObjekt implements ITastatur 
{
	Tastatur tastatur;
	int power;
	int x;
	int y;
	int xSize;
	int ySize;
	boolean rechnet;
	ITuWas link;
	int linkID;
	
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
	
	public void setzeLink(ITuWas linkObj)
	{
		link = linkObj;
		linkID = 0;
	}
	
	public void setzeLink(ITuWas linkObj, int ID)
	{
		link = linkObj;
		linkID = ID;
	}
	
	public void berechnePower()
	{
		Rechteck leiste = new Rechteck(x, y, xSize, ySize);
		leiste.setzeFarbe("gruen");
		int i = -1;
		int breite = xSize;
		while(rechnet)
		{
			breite +=i;
			power = breite;
			leiste.setzeGroesse(breite, ySize);
			if(breite <= 1)
			{
				i = 1;
			}
			else if (breite >= xSize)
			{
				i = -1;
			}
			int farbe = xSize + 1/ breite + 1;
			if(farbe == 1)
			{
				leiste.setzeFarbe("gruen");
			}
			else if (farbe == 2)
			{
				leiste.setzeFarbe("gelb");
			}
			else if(farbe >= 3)
			{
				leiste.setzeFarbe("rot");
			}
			if(tastatur.istgedrueckt("ENTER"))
			{
				break;
			}
			StaticTools.warte(5);
		}
		leiste.entfernen();
		leiste = null;
		link.tuWas(linkID);
		return;
	}
	
	@Override
	public void tastenAktion(String rueckgabe) 
	{
		if(rueckgabe.equalsIgnoreCase("Enter"))
		{
			rechnet = false;
		}
	}
}
