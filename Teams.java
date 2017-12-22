/*
Luokka Teams toimii objektina joukkueille
Vaatii luodessa nimen string muodossa (Nimi voidaan asettaa vain kerran)
*/
import java.io.*;
public class Teams {
    final String name;
    int place;
    public Teams(String name) {this.name = name;}
    //Asetetaan int place
    protected void setPlace(int place) {this.place = place;}
    //Annetaan name ja place
    protected String giveName() {return name;}
    protected int givePlace() {return place;}
    //Tulostus
    public String toString() {return "Team name: "+name+" Place: "+place;}
}