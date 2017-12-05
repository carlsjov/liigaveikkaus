public class Liigajoukkueet {
    public Teams[] getTeams(){
        Teams[] liiga = new Teams[15];
        liiga[0] = new Teams("Ässät");
        liiga[1] = new Teams("Lukko");
        liiga[2] = new Teams("TPS");
        liiga[3] = new Teams("Kärpät");
        liiga[4] = new Teams("HPK");
        liiga[5] = new Teams("JYP");
        liiga[6] = new Teams("HIFK");
        liiga[7] = new Teams("Sport");
        liiga[8] = new Teams("Jukurit");
        liiga[9] = new Teams("KooKoo");
        liiga[10] = new Teams("SaiPa");
        liiga[11] = new Teams("Pelicans");
        liiga[12] = new Teams("KalPa");
        liiga[13] = new Teams("Tappara");
        liiga[14] = new Teams("Ilves");
        System.out.println("joukkueet tehty");
        return liiga;
    }
    public static void main(String args[]){
        Liigajoukkueet o = new Liigajoukkueet();
        SetTeams t = new SetTeams();
        Teams[] liiga = o.getTeams();
        for (int i = 0;i < liiga.length; i++){
            System.out.println(liiga[i]);
        }
        System.out.println(liiga[5].givePlace());
        System.out.println(liiga[14].giveName());
        System.out.println(liiga[5].givePlace());
        liiga[14] = new Teams("karhu");
        System.out.println(liiga[14].giveName());
        liiga = t.set();
        System.out.println("Liigajoukkueet päivitetty");
        for (int i = 0;i < liiga.length; i++){
            System.out.println(liiga[i]);
        }
    }
}
