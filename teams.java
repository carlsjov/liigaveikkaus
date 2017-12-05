import java.io.*;
public class teams {
String name;
int place;
int guess;

public teams(String name) {
    this.name = name;
}
public void teamsPlace(int teamsPlace) {
    teamsPlace = place;
}
public void teamsGuess(int teamsGuess){
    teamsGuess = guess;
}
public void setGuess(int guess) {
    try {
        cin = new InputStreamReader(System.in);
        System.out.println("Enter your guess for "+ name +":");
        do {
            guess = (int) cin.read();
            System.out.print(guess);
        } while(guess >0 && guess<16);
    }finally {
        if (cin!= null) {
            cin.close();
        }
    }
}
}