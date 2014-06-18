import java.util.ArrayList;


public class PhysEngine 
{
	public float delta_t;
	public float v_x;
	public float v_y;
	public float a_x;
	public ISpielObjekt spieler;
	public float a_y;
	public ArrayList<ISpielObjekt> spielObjekteF;  //Spielobjekte, die sich mit dem Hintergrund bewegen
	public ArrayList<ISpielObjekt> spielObjekteA;  //Spielobjekte, die sich in dieselbe Richtung wie der Spieler "bewegen"
	public ArrayList<ISpielObjekt> spielObjekteB;  //Spielobjekte, die sich in die Gegenrichtung bewegen
	
	public PhysEngine(float deltaT) 
	{
		delta_t = deltaT;
	}
	
	public void start(float vX, float vY)
	{
		v_x = vX;
		v_y = vY;
		a_x = 9.81F;
		a_y = 0;
	}
	
	public void start(float vX, float vY, float aX)
	{
		v_x = vX;
		v_y = vY;
		a_x = aX;
		a_y = 0;
	}
	
	public void berechneSchritt(float delta_t)
	{
		for(ISpielObjekt objekt : spielObjekteF)
		{
			objekt.bewege(((int)(v_x * delta_t)),((int) (v_y * delta_t)));
		}
		for(ISpielObjekt objekt : spielObjekteA)
		{
			objekt.bewege(((int)(v_x * delta_t)),((int) (v_y * delta_t)));
		}
		for(ISpielObjekt objekt : spielObjekteB)
		{
			objekt.bewege(((int)(v_x * delta_t)),((int) (v_y * delta_t)));
		}
		for(ISpielObjekt objekt : spielObjekteF)
		{
			for(ISpielObjekt objektA : spielObjekteA)
			{
				if(objekt.gibHitBox().berechneZusammenstossMit(objektA.gibHitBox()))
				{
					objekt.beiZusammenstossMit(objektA);
				}
			}
			for(ISpielObjekt objektB : spielObjekteB)
			{
				if(objekt.gibHitBox().berechneZusammenstossMit(objektB.gibHitBox()))
				{
					objekt.beiZusammenstossMit(objektB);
				}
			}
			if(objekt.gibHitBox().berechneZusammenstossMit(spieler.gibHitBox()))
			{
				objekt.beiZusammenstossMit(spieler);
			}
		}
		v_x = a_x * delta_t;
		v_y = a_y * delta_t;
	}

	public void stop()
	{
		
	}
}
