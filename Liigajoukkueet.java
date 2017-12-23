
public class Liigajoukkueet { 
    public Teams[] getTeams(){
        Teams[] liiga = new Teams[15];
        liiga[0] = new Teams("assat",1);
        liiga[1] = new Teams("Lukko",2);
        liiga[2] = new Teams("TPS",3);
        liiga[3] = new Teams("Karpat",4);
        liiga[4] = new Teams("HPK",5);
        liiga[5] = new Teams("JYP",6);
        liiga[6] = new Teams("HIFK",0);
        liiga[7] = new Teams("Sport",8);
        liiga[8] = new Teams("Jukurit",9);
        liiga[9] = new Teams("KooKoo",10);
        liiga[10] = new Teams("SaiPa",11);
        liiga[11] = new Teams("Pelicans",12);
        liiga[12] = new Teams("KalPa",13);
        liiga[13] = new Teams("Tappara",14);
        liiga[14] = new Teams("Ilves",15);
        System.out.println("joukkueet tehty");
        FileOut tallenneus = new FileOut();
        tallenneus.FileOutL(liiga);
        System.out.println("tallennettu");
        return liiga;
    }
    public static void main(String args[]){
        FileIn f = new FileIn();
        f.liigaURL();
        TeamsGuess[] players = f.ReadGuess();
        Teams[] liiga = f.ReadLiiga();
        System.out.println("Tulostetaan runkosarja");
        for (int i = 0;i < liiga.length; i++){
            System.out.println(liiga[i]);
        }
        for (int i = 0;i <players.length; i++){
            System.out.println(players[i].getName());
            Teams[] guess = players[i].getTeams(); 
            for (int t = 0;t < guess.length; t++){
                System.out.println(guess[t]);
            }
        }
        System.out.println("Tulostetaan runkosarja");
        for (int i = 0;i < liiga.length; i++){
            System.out.println(liiga[i]);
        }
    }
}