import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.IIOException;

public class FileOut{

    protected 

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