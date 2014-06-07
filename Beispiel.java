
/**
 * Beschreiben Sie hier die Klasse Beispiel.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import java.util.*;

public class Beispiel
{
    public void test()
    {
        Random random = new Random();
        int i = random.nextInt(10);
        IItem item;
        if(i < 5)
        {
            item = new BeispielItem();
        }
        else
        {
            item = new BeispielItem2();
        }
        item.aktiviere(this);
    }
}
