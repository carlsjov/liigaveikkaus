import java.io.*;
import java.util.*;
import java.net.*;

public class FileIn{
    private Scanner rL;
    private Scanner rG;
    private Teams[] teams = new Teams[15];
    private TeamsGuess[] teamsG;

    protected Teams[] ReadLiiga(){
        Scanners();
        int i = 0;
        while(rL.hasNext()){
            String a = rL.next();
            int b = rL.nextInt();
            teams[i] = new Teams(a,b);
            i++;
        }
        System.out.println("!lataus suoritettu!");
        rL.close();
        rG.close();
        return teams;
    }
    protected TeamsGuess[] ReadGuess(){
        Scanners();
        String name = "";
        int i = 0;
        int g = 0;
        boolean newP = true;
        boolean first = true;
        while(rG.hasNext()){
            if(first){
                teamsG = new TeamsGuess[rG.nextInt()];
                first = false;
            }
            else{
                if(newP){
                    name = rG.next();
                    System.out.println("n"+name);
                    newP = false;
                }
                else{
                    String a = rG.next();
                    System.out.println(a);
                    int b = rG.nextInt();
                    teams[i] = new Teams(a,b);
                    i++;
                    if(i==15){
                        newP = true;
                        teamsG[g] = new TeamsGuess(name,teams);
                        g++;
                        i = 0;
                    }
                }
            }
        }
        System.out.println("!lataus suoritettu!");
        rL.close();
        rG.close();
        return teamsG;
    }
    private void Scanners() {
        try{
            rL = new Scanner(new File("liiga.txt"));
            rG = new Scanner(new File("liigaArvaukset.txt"));
        }
        catch (Exception e){
            System.out.println("Scanners faill");
        }
    }
    public static void main(String[] args) {
        try{
            URL liigaUrl = new URL("https://www.yle.fi/tekstitv/txt/P223_01.html");
            BufferedReader in = new BufferedReader(
            new InputStreamReader(liigaUrl.openStream()));
    
            String inputLine;
            String file = "";
            while ((inputLine = in.readLine()) != null){
                file = file inputLine;
                //System.out.println(inputLine);
            }
            in.close();
            System.out.println(file);
        }
        catch (IOException e){
            System.out.println("faill");
        }
    }
}