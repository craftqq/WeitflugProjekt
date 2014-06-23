import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 
 */

/**
 * @author Martin
 *
 */
public class DateiSchreiber 
{
	public String dateiname; //name der Datei
	
	public DateiSchreiber(String fileName)
	{
		dateiname = fileName;
	}
	
	public void schreiben(String[] inhalt)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(dateiname);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			for(String line : inhalt)
			{
				bw.write(line);
				bw.newLine();
			}
			bw.close();
		}
		catch (IOException e)
		{
			
		}
		
	}
}
