

public class Zufall
{
 
    public static int gibZufallszahl(int min, int max){
        return (int) (Math.random()*(1+max-min)+min);
    }
    public static void test(){
        for(int i=1;i<=100;i++){
            System.out.println(gibZufallszahl(0,10));
        }
    }
}
