

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
				GameEngine.musicEngine.stoppeAlle();
				GameEngine.musicEngine = new SoundEngine();
				GameEngine.musik = false;
			}
			else
			{
				GameEngine.musicEngine = new ActiveSoundEngine();
				GameEngine.musicEngine.spieleAudioEndlos("mainMenu.wav");
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
		GameEngine.musicEngine.spieleAudioEndlos("mainMenu.wav");
		
	}

	@Override
	public void schliesse() 
	{
		exit.entfernen();
		musik.entfernen();
		musik = null;
		exit = null;
		GameEngine.musicEngine.stoppeAudio("mainMenu.wav");
	}

}
