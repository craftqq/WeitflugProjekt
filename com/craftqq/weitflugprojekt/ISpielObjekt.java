package com.craftqq.weitflugprojekt;

/**
 * Tragen Sie hier eine Beschreibung des Interface ISpielObjekt ein.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public interface ISpielObjekt
{
    public void beiZusammenstossMit( ISpielObjekt objekt);
    public void setzePosition(int x, int y);
    public void bewege(int x, int y);
    public void gibHitBox();
    public void gibPosition();
}
