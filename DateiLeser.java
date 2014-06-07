import java.io.*;

class DateiLeser
{
    private String dateiname;
    private String[] inhalt;
    private int anzahlZeilen;

    public DateiLeser(String dateiname){
        this.dateiname=dateiname;
        inhalt = new String[1];
    }

    public String[] lesen() 
    {
        try{
            FileReader fr = new FileReader(dateiname);
            BufferedReader br = new BufferedReader(fr);
            String zeile = "";
            while( (zeile = br.readLine()) != null )
            {
                anzahlZeilen++;
            }
            br.close();
            fr = new FileReader(dateiname);
            br = new BufferedReader(fr);

            inhalt=new String[anzahlZeilen];
            zeile = "";
            int i=0;
            while( (zeile = br.readLine()) != null )
            {
                inhalt[i]=zeile;
                i++;
            }
            br.close();            
        }
        catch(Exception e){
            System.out.println(e);}        
        return inhalt;
    }

    public void setzeDateiname(String name){
        dateiname=name;
    }

}