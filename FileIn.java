import java.io.*;
import java.util.*;

import java.net.*;

public class FileIn{
    private Scanner rL;
    private Scanner rG;
    private TeamsGuess[] teamsG;

    protected Teams[] ReadLiiga(){
        Scanners(0);
        Teams[] tG = new Teams[15];
        int i = 0;
        while(rL.hasNext()){
            String a = rL.next();
            int b = rL.nextInt();
            tG[i] = new Teams(a,b);
            i++;
        }
        //System.out.println("!lataus rL suoritettu!");
        rL.close();
        return tG;
    }
    protected TeamsGuess[] ReadGuess(){
        Scanners(1);
        String name = "";
        int points;
        int g = 0;
        boolean newP = true;
        int h = rG.nextInt();
        teamsG = new TeamsGuess[h];
        try{
            while(rG.hasNext()){
                name = rG.next();
                //System.out.println(name);
                points = rG.nextInt();
                Teams[] a = TG();
                teamsG[g] = new TeamsGuess(name,a);
                teamsG[g].setPoints(points);
                g++;
            }
        } catch (Exception e){
            System.out.println("ReadGuess() fail");
        }
        //System.out.println("!lataus rG suoritettu!");
        rG.close();
        return teamsG;
    }
    private Teams[] TG(){
        Teams[] tG = new Teams[15];
        for(int i=0;i<15;i++){
            String a = rG.next();
            //System.out.println(a);
            int b = rG.nextInt();
            tG[i] = new Teams(a,b);
        }
        return tG;
    }
    private void Scanners(int i) {
        try{
            if(i==0)
            rL = new Scanner(new File("liiga.txt"));
            if(i==1)
            rG = new Scanner(new File("liigaArvaukset.txt"));
        }
        catch (Exception e){
            System.out.println("Scanners fail");
        }
    }
    private String[] teamsList() {
        String[] list = new String[15];
        int i = 0;
        try{
            Scanner tL = new Scanner(new File("haku.txt"));
            while(tL.hasNext()){
                list[i]=tL.next();
                i++;
            }
            //System.out.println("Luettu");
        }
        catch (Exception e){
            System.out.println("Scanners fail");
        }
        return list;
    }
    protected void liigaURL() {
        FileOut fO = new FileOut();
        fO.loadURL();
        Teams[] liiga = ReadLiiga();
        String[] list = teamsList();
        String a="";
        String alku = "class="+'"'+"ta-l"+'"';
        String num1 = "class="+'"'+"index"+'"'+">";
        String num2 = ".</td>";
        int t = 1;
        try{
            Scanner lR = new Scanner(new File("liigaURL.txt"));
            while(lR.hasNext()){
                a = lR.next();
                //System.out.println(a.equals(num1+1+num2)+" "+a);
                if(a.equals(num1+t+num2)){
                    lR.next();
                    a = lR.next();
                    //System.out.println(a);
                    for(int i=0;i<15;i++){
                        if(a.equals(alku+list[i])){
                            liiga[i].setPlace(t);
                            //System.out.println(liiga[i]);
                            t++;
                        }
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Scanners fail");
        }
        /*System.out.println("Tulostetaan n�yte");
        for(int i=0;i<15;i++){
            System.out.println(liiga[i]);
        }
        System.out.println("Tulostetaan n�yte Loppu");*/
        fO.FileOutL(liiga);
    }
    public static void main(String[] args) {
    }
}