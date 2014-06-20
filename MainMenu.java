

public class MainMenu implements IScreen 
{
	Taste exit;
	Taste musik;
	
	@Override
	public void tuWas(int ID) 
	{
		switch(ID)
		{
		case 0:
			this.schliesse();
			GameEngine.end();
			break;
		case 1:
			if(GameEngine.musik)
			{
				GameEngine.musikEngine.stoppeAlle();
				GameEngine.musikEngine = new SoundEngine();
				GameEngine.musik = false;
			}
			else
			{
				GameEngine.musikEngine = new ActiveSoundEngine();
				GameEngine.musikEngine.spieleAudioEndlos("mainMenu.wav");
				GameEngine.musik = true;
			}
			break;	
		default:
			break;
		}

	}

	@Override
	public void rufeAuf() 
	{
		exit = new Taste("Exit", 50, 50, 200, 50);
		musik = new Taste("musik an / aus", 50, 120, 200, 50);
		exit.setzeLink(this, 0);
		musik.setzeLink(this, 1);
		GameEngine.musikEngine.spieleAudioEndlos("mainMenu.wav");
		
	}

	@Override
	public void schliesse() 
	{
		exit.entfernen();
		musik.entfernen();
		musik = null;
		exit = null;
		GameEngine.musikEngine.stoppeAudio("mainMenu.wav");
	}

}
