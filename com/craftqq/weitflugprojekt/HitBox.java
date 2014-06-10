package com.craftqq.weitflugprojekt;

public class HitBox 
{
	int startX;
	int startY;
	int sizeX;
	int sizeY;
	
	public HitBox(int xStart, int yStart, int xSize, int ySize)
	{
		startX = xStart;
		startY = yStart;
		sizeX = xSize;
		sizeY = ySize;
	}
	
	/**
	 * vergleicht die eingenommene fläche zweier Hitboxen miteinander
	 * 
	 */
	public boolean berechneZusammenstossMit(HitBox hitbox)
	{
		if(hitbox == null)
		return false;
			
		int entfernungX;
		int entfernungY;
		
		if(startX == hitbox.startX && startY == hitbox.startY)
			return true;
		
		if(startX > hitbox.startX)
			entfernungX = hitbox.startX + hitbox.sizeX - startX;
		else
			entfernungX = startX + sizeX - hitbox.startX;
		
		if(startY > hitbox.startY)
			entfernungY = hitbox.startY + hitbox.sizeY -startY;
		else
			entfernungY = startY + sizeY - hitbox.startY;
		
		if(entfernungX >= 0 && entfernungY >= 0)
			return true;
		else
			return false;
	}
}
