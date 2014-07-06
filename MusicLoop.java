
public class MusicLoop extends AudioPlayer 
{
	public AdvAudio loop;
	
	@Override
	public void fuegeDateiHinzu(String fileName)
	{
		if (loop == null)
		{
			loop = new AdvAudio(fileName);
		}
		loop.stop();
		loop = new AdvAudio(fileName);
	}
	
	@Override
	public void spieleAudio(String fileName)
	{
		if (loop == null)
		{
			loop = new AdvAudio(fileName);
		}
		if(loop.gibName().equalsIgnoreCase(fileName))
		{
			loop.play();
		}
		else
		{
			loop.stop();
			loop = new AdvAudio(fileName);
			loop.play();
		}
	}
	
	@Override
	public void spieleAudioEndlos(String fileName)
	{
		if (loop == null)
		{
			loop = new AdvAudio(fileName);
		}
		if(loop.gibName().equalsIgnoreCase(fileName))
		{
			loop.playEndlos();
		}
		else
		{
			loop.stop();
			loop = new AdvAudio(fileName);
			loop.playEndlos();
		}
	}
	
	@Override
	public void stoppeAudio(String fileName)
	{
		if (loop == null)
		{
			loop = new AdvAudio(fileName);
		}
		loop.stop();
		loop = new AdvAudio(fileName);
	}
	
	@Override
	public void stoppeAlle()
	{
		loop.stop();
	}
}
