
public abstract class Upgrade implements ITuWas 
{
	Taste taste;
	Bild[] upgrades;
	int level = 0;
	int maxLevel;
	
	public Upgrade(int amount, int x, int y, int xsize, int ysize, String label) 
	{
		taste = new Taste(label, x, y, xsize, ysize);
		taste.setzeLink(this, 0);
		maxLevel = amount;
		upgrades = new Bild[amount];
		for(int i = 0; i < upgrades.length; i++)
		{
			upgrades[i] = new Bild(x, y, xsize, ysize, "upgrade_empty.png");
		}
	}

	public abstract boolean upgrade();
	
	public void tuWas(int ID)
	{
		if(ID == 0)
		{
			if((this.upgrade()) && (level < maxLevel))
			{
				upgrades[level].wechsleBild("upgrade_full.png");
				level++;
			}
		}
	}
}
