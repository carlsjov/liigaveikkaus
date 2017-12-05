
import java.util.Scanner ;
public class SetTeams{
    private static Scanner read = new Scanner(System.in);
    private Teams[] liigaNull = new Teams[15];
    private void nullTema (){
        for (int i=0;i<liigaNull.length;i++){
            liigaNull[i] = new Teams("null");
        }
        System.out.println("null joukkueet tehty");
    }
    protected Teams[] set(){
        Liigajoukkueet lg = new Liigajoukkueet();
        Teams[] liigaTeams = lg.getTeams();
        nullTema();
        int place;
        for (int i=0;i<liigaTeams.length;i++){
            System.out.println("Anna joukkueen:"+liigaTeams[i].giveName()+" sijoitus");
            place = read.nextInt();
            liigaTeams[i].setPlace(place);
            liigaNull[(place-1)] = liigaTeams[i];
            System.out.println("Joukkueen:"+liigaTeams[i].giveName()+" on sijalla:"+liigaTeams[i].givePlace());
        }
        for (int i = 0;i < liigaNull.length; i++){
            System.out.println(liigaNull[i]);
        }
        return liigaTeams;
    }
}