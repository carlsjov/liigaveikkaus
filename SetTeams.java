/*
SetTeams järjestelee joukkueet kyselemällä yksitellen joukkueen sijoitusta.
Järjestelyn yhteydessä luo uuden taulukon joka on sijoitusjärjestyksessä ([0]=1. ja [14]=15.)
*/
import java.util.Scanner ;
public class SetTeams{
    //Scanneri jolla saadaan tekstiä komentoriviltä
    private static Scanner read = new Scanner(System.in);
    private Teams[] liigaNull = new Teams[15];
    //Tehdään joukkuelista jossa kaikkien joukkueden nimet on null
    //Tätä käytetään jatkossa varmistamaan päälekkäisyydet
    private void nullTema (){
        for (int i=0;i<liigaNull.length;i++){
            liigaNull[i] = new Teams("null");
        }
        System.out.println("null joukkueet tehty");
    }
    //Järjestelijä
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
        //Tulostetaan oikeass järjestyksessä olevan
        for (int i = 0;i < liigaNull.length; i++){
            System.out.println(liigaNull[i]);
        }
        //plauttaa joukkueessa oikeassa järjestyksessä olevat joukkueet
        return liigaTeams;
    }
    //Plauttaa oikeassa järjestykseää olevan joukkueen
    protected Teams[] rightOrderList(){
        return liigaNull;
    }
}