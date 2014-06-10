package com.craftqq.weitflugprojekt;

import hilfsklassen.Bild;

public class Hintergrund implements ISpielObjekt 
{
	Bild hintergrund;
	int xPos;
	int yPos;
	
	public Hintergrund(String bildName, int xpos, int ypos, int xSize, int ySize)
	{
		hintergrund = new Bild(bildName);
		hintergrund.setzeDimensionen(xpos, ypos, xSize, ySize);
		xPos = xpos;
		yPos = ypos;
	}
	
	@Override
	public void beiZusammenstossMit(ISpielObjekt objekt) 
	{
		return;
	}

	@Override
	public void setzePosition(int x, int y) 
	{
		hintergrund.setzePosition(x, y);
		xPos = x;
		yPos = y;
	}

	@Override
	public void bewege(int x, int y) 
	{
		xPos += x;
		yPos += y;
		hintergrund.setzePosition(xPos, yPos);
		
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
