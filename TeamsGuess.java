

/*
TeamsGuess on arvaajan joukkue olio. Olio perii team olion ominaisuuksia Teams
*/
public class TeamsGuess{
    final String plauerName;
    final Teams[] teams;
    int points;
    public TeamsGuess(String name,Teams[] teams){
        plauerName = name;
        this.teams = teams;
    }
    protected void setPoints(int points) {
        this.points = points;
    }
    protected int getPoints() {
        return points; 
    }
    protected Teams[] getTeams(){
        return teams;
    }
    protected String getName(){
        return plauerName;
    }
}