import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.net.*;

import javax.imageio.IIOException;

public class FileOut{
    private PrintWriter pw;
    private PrintWriter pw2;
    
    public void FileOutL(Teams[] teams){
        try{
            pw = new PrintWriter(new FileWriter("liiga.txt"));
            for(int i=0;i<15;i++){
                pw.println(teams[i].giveName()+" "+teams[i].givePlace());
            }
        }
        catch (IOException e){
            System.out.println("Tiedostoa ei l�ytynyt");
        }

        pw.close();
        System.out.println("liiga.txt done");
    }

    public void FileOutG (TeamsGuess[] teamGuess){
        Reader(1);
        if(teamGuess.length>0){
            for(int k=0;k<teamGuess.length;k++){
                pw.println(teamGuess[k].getName()+" "+teamGuess[k].getPoints());
                Teams[] teamsG = teamGuess[k].getTeams();
                for(int t=0;t<15;t++){
                    pw.println(teamsG[t].giveName()+teamsG[t].givePlace());
                }
            }
        }
        pw.close();
    }
    protected void loadURL(){
        try{
            PrintWriter lr = new PrintWriter(new FileWriter("liigaURL.txt"));

            URL liigaUrl = new URL("http://liiga.fi/sarjataulukko");
            BufferedReader in = new BufferedReader(
            new InputStreamReader(liigaUrl.openStream()));
    
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                //System.out.println(inputLine);
                lr.println(inputLine);
            }
            in.close();
            lr.close();
            System.out.println("!sarjataulukko lataus suoritettu!");
        }
        catch (IOException e){
            System.out.println("fail");
        }
    }
    private void Reader(int t){
        try{
            if(t==0){
                pw = new PrintWriter(new FileWriter("liiga.txt"));
            }
            if(t==1){
                pw = new PrintWriter(new FileWriter("liigaArvaukset.txt"));
            }
        }
        catch (IOException e){
            System.out.println("Tiedostoa ei l�ytynyt");
        }
    }

    public static void main(String[] args) {
        try{
            PrintWriter lr = new PrintWriter(new FileWriter("liigaURL.txt"));

            URL liigaUrl = new URL("http://liiga.fi/sarjataulukko");
            BufferedReader in = new BufferedReader(
            new InputStreamReader(liigaUrl.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null){
                //System.out.println(inputLine);
                lr.println(inputLine);
            }
            in.close();
            lr.close();
            System.out.println("!sarjataulukko lataus suoritettu!");
        }
        catch (IOException e){
            System.out.println("faill");
        }
        try{
            FileWriter testif = new FileWriter("testi.txt");
            PrintWriter testip = new PrintWriter(testif);

            testip.println("moikka");
            testip.println("moikka2");
            //pw.println("moikka3");
            
            testip.close();
        }
        catch (IOException e){
            System.out.println("Ei toimikkaa...");
        }   
    }//main

}//FileReader