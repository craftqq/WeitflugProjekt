

/**
 * 
 * 
 * @author Martin 
 * @version 1.0
 */

public interface ISpielObjekt
{
	/**
	 * 
	 * @param objekt = das objekt mit dem das Spielobjekt zusammenstoesst
	 * @return true falls das Objekt beim zusammenstoss entfernt wird; ansonsten false
	 */
    public boolean beiZusammenstossMit( ISpielObjekt objekt);
    
    public void setzePosition(int x, int y);
    
    public void bewege(int x, int y);
    
    public HitBox gibHitBox();
    
    public int gibPositionX();
    
    public int gibPositionY();
    
}
