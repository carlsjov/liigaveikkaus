/*
TeamsGuess on arvaajan joukkue olio. Olio perii team olion ominaisuuksia Teams
*/
public class TeamsGuess extends Teams{
    int points;
    public TeamsGuess(String name,int place){
        super(name);
        this.place = place;
    }
    protected void setPoints(int points) {
        this.points = points;
    }
    private setPlace(int place) {System.out.println("No posible");}
    private int getPoints() {return points;}
}