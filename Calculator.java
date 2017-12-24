/**
 * Calculator
 */
import java.lang.*;

public class Calculator {
    private Teams[] runko;
    private TeamsGuess[] players;
    private FileOut f = new FileOut();
    protected void sendInfo(Teams[] runko, TeamsGuess[] players){
        this.runko = runko;
        this.players = players;
        calculatorPoints();
    }
    private void calculatorPoints(){
        for(int i=0;i<players.length;i++){
            players[i].setPoints(pointsC(i));
            //System.out.println(players[i].getName()+" "+players[i].getPoints());
        f.FileOutG(players);
        }
    }
    private int pointsC(int i){
    int points = 0;
        for(int t=0;t<runko.length;t++){
            int a = players[i].getTeam(t).givePlace();
            int b = runko[t].givePlace();
            int c = Math.abs(b-a);
            //System.out.println(players[i].getName()+" Et�isyys: "+c);
            if(c<8){
                double d = ((8-c)/8.00)*100;
                //System.out.println("prosentti: "+d);
                points = points+(int)d;
                //System.out.println("Pisteet: "+points);
            }
        }
        return points;
    }
}