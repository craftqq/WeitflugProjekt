
public class MainMenu implements IScreen 
{
	Taste exit;
	
	
	@Override
	public void tuWas(int ID) 
	{
		switch(ID)
		{
		case 0:
			GameEngine.end();
		}

	}

	@Override
	public void rufeAuf() 
	{
		exit = new Taste("Exit", 50, 50, 200, 50);
		exit.setzeLink(this, 0);
		
	}

	@Override
	public void schliesse() 
	{
		exit.entfernen();
		exit = null;
	}

}
