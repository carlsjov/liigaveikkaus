import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.IIOException;

public class FileOut{
    private FileWriter fw;
    private PrintWriter pw;
    
    protected void FileOut (Teams[] teams, TeamGuess[] teamGuess){
        Reader();
        try{
            pw.println("liiga");
            for(int i=0;i<15;i++){
                pw.println(teams[i].giveName());
                pw.println(teams[i].givePlace());
            }
            if(teamGuess.lenght>0){
                for(int k=0;t<teamGuess.lenght;k++){
                    Teams[] teamsG = teamGuess[k].;
                    for(int t=0;t<15;t++){
                        pw.println(teamsG.;
                        pw.println(teamsG.givePlace());
                    }
                }
            }
            pw.close();
        }
        catch (IOException e){
           System.out.println("Ei toimikkaa...");
        }
    }
    private void Reader(){
        try{
            fw = new FileWriter("testi.txt");
            pw = new PrintWriter(fw);
        }
        catch (IOException e){
            System.out.println("Tiedostoa ei l�ytynyt");
        }
    }

    public static void main(String[] args) {
     try{
         FileWriter fw = new FileWriter("testi.txt");
         PrintWriter pw = new PrintWriter(fw);

         pw.println("moikka");
         pw.println("moikka2");
         //pw.println("moikka3");
         
         pw.close();
     }
     catch (IOException e){
        System.out.println("Ei toimikkaa...");
     }   
    }//main

}//FileReader