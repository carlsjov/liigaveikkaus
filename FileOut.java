import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.IIOException;

public class FileOut{
    private FileWriter fw;
    private PrintWriter pw;
    private FileWriter fw2;
    private PrintWriter pw2;
    
    public void FileOut (Teams[] teams){
        Reader();
        //try{
            for(int i=0;i<15;i++){
                pw.println(teams[i].giveName());
                pw.println(teams[i].givePlace());
            }
            pw2.println(0);
            pw2.close();
            pw.close();
        /*}
        catch (IOException e){
           System.out.println("Ei toimikkaa...");
        }*/
    }

    public void FileOut (Teams[] teams, TeamsGuess[] teamGuess){
        Reader();
        //try{
            for(int i=0;i<15;i++){
                pw.println(teams[i].giveName());
                pw.println(teams[i].givePlace());
            }
            pw2.println(teamGuess.length);
            if(teamGuess.length>0){
                for(int k=0;k<teamGuess.length;k++){
                    pw2.println(teamGuess[k].getName());
                    pw2.println(teamGuess[k].getPoints());
                    Teams[] teamsG = teamGuess[k].getTeams();
                    for(int t=0;t<15;t++){
                        pw2.println(teamsG[t].giveName());
                        pw2.println(teamsG[t].givePlace());
                    }
                }
            }
            pw2.close();
            pw.close();
        /*}
        catch (IOException e){
           System.out.println("Ei toimikkaa...");
        }*/
    }
    private void Reader(){
        try{
            fw = new FileWriter("liiga.txt");
            pw = new PrintWriter(fw);
            fw2 = new FileWriter("liigaArvaukset.txt");
            pw2 = new PrintWriter(fw2);
        }
        catch (IOException e){
            System.out.println("Tiedostoa ei l�ytynyt");
        }
    }

    public static void main(String[] args) {
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