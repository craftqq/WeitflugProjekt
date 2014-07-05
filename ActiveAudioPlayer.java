import java.util.HashMap;


public class ActiveAudioPlayer extends AudioPlayer 
{
	public HashMap<String, AdvAudio> dateien = new HashMap<String, AdvAudio>();
	
	@Override
	public void fuegeDateiHinzu(String fileName)
	{
		AdvAudio datei = dateien.get(fileName);
		if(datei == null)
		{
			datei = new AdvAudio(fileName);
			dateien.put(fileName, datei);
		}
	}
	
	@Override
	public void spieleAudio(String fileName)
	{
		AdvAudio datei = dateien.get(fileName);
		if(datei == null)
		{
			datei = new AdvAudio(fileName);
			dateien.put(fileName, datei);
		}
		datei.play();
	}
	
	@Override
	public void spieleAudioEndlos(String fileName)
	{
		AdvAudio datei = dateien.get(fileName);
		if(datei == null)
		{
			datei = new AdvAudio(fileName);
			dateien.put(fileName, datei);
		}
		datei.playEndlos();
	}
	
	@Override
	public void stoppeAudio(String fileName)
	{
		AdvAudio datei = dateien.get(fileName);
		if(datei == null)
		{
			datei = new AdvAudio(fileName);
			dateien.put(fileName, datei);
		}
		datei.stop();
	}
	
	@Override
	public void stoppeAlle()
	{
		Object[] audios = dateien.values().toArray();
		for(Object audio : audios)
		{
			try
			{
				((AdvAudio) audio).stop();
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
class AdvAudio extends Audio
{
	int zustand = 0;
	public AdvAudio(String filename) 
	{
		super(filename);
	}
	
	@Override
	public void play()
	{
		if(zustand == 0)
		{
			super.play();
			zustand = 1;
		}
		else if (zustand == 2)
		{
			super.stop();
			super.play();
			zustand = 1;
		}
	}
	
	@Override
	public void stop()
	{
		super.stop();
		zustand = 0;
	}
	
	@Override
	public void playEndlos()
	{
		if(zustand == 0)
		{
			super.playEndlos();
			zustand = 2;
		}
		else if (zustand == 1)
		{
			super.stop();
			super.playEndlos();
			zustand = 2;
		}
	}
}
