import java.util.HashMap;


public class ActiveSoundEngine extends SoundEngine 
{
	public HashMap<String, Audio> dateien = new HashMap<String, Audio>();
	
	@Override
	public void fuegeDateiHinzu(String fileName)
	{
		Audio datei = dateien.get(fileName);
		if(datei == null)
		{
			datei = new Audio(fileName);
			dateien.put(fileName, datei);
		}
	}
	
	@Override
	public void spieleAudio(String fileName)
	{
		Audio datei = dateien.get(fileName);
		if(datei == null)
		{
			datei = new Audio(fileName);
			dateien.put(fileName, datei);
		}
		datei.play();
	}
	
	@Override
	public void spieleAudioEndlos(String fileName)
	{
		Audio datei = dateien.get(fileName);
		if(datei == null)
		{
			datei = new Audio(fileName);
			dateien.put(fileName, datei);
		}
		datei.playEndlos();
	}
	
	@Override
	public void stoppeAudio(String fileName)
	{
		Audio datei = dateien.get(fileName);
		if(datei == null)
		{
			datei = new Audio(fileName);
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
				((Audio) audio).stop();
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
}
