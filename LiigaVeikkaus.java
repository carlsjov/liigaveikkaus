/**
 * LiigaVeikkaus
 */

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import javafx.scene.layout.FlowPane;

import java.io.*;
import java.util.*;
import java.util.Locale.Category;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.IIOException;

public class LiigaVeikkaus extends JFrame implements ActionListener{

    private JPanel a = new JPanel();
    private JPanel b = new JPanel();
    private JPanel c = new JPanel();
    private JPanel d = new JPanel();
    private JPanel e = new JPanel();
    public Teams[] liiga = new Teams[15];
    public TeamsGuess[] arvaukset;
    public TeamsGuess[] suurinarvaus;
    protected Boolean lataus = true; 
    public Scanner kk;

    private LiigaVeikkaus(){
        super("LiigaVeikkaus");
        //frame.setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //a JPanel
        a.setLayout(new FlowLayout());
        a.add(sarjataulukkoJPanel());

        //b JPanel
        b.setLayout(new FlowLayout());
        b.add(pisteJPanel());

        //c JPanel
        c.setLayout(new FlowLayout());
        JLabel cl;
        if(lataus){
            cl = new JLabel("Download done");
            cl.setForeground(Color.WHITE);
            c.add(cl);
            c.setBackground(Color.blue);
        }
        else{
            cl = new JLabel("Download fail");
            cl.setForeground(Color.WHITE);
            c.add(cl);
            c.setBackground(Color.red);
            lataus = true;
        }

        d.add(taulukko());
        d.setLayout(new FlowLayout());
        d.setOpaque(false);

        JButton r = new JButton("Refresh");
        r.addActionListener(this);

        //Add frame
        add(c,BorderLayout.NORTH);
        add(a,BorderLayout.WEST);
        add(b,BorderLayout.EAST);
        add(r,BorderLayout.SOUTH);
        add(d,BorderLayout.CENTER);
        setSize(new Dimension(1000,korkeus()));
        JFrame jf = new JFrame("taulukko");
        jf.setVisible(true);
        jf.setSize(new Dimension(1300,korkeus()));
        e.setLayout(new GridLayout(arvaukset.length,0));
        for(int g=0;g<arvaukset.length;g++){
            e.add(tarkemmatpisteet(g));
        }
        JScrollPane scrollPane = new JScrollPane(e);
        jf.add(scrollPane,BorderLayout.CENTER);
        JLabel l = new JLabel("Pisteet arvauksesta = Pa, Play-off pisteet = Po, Kuuden joukossa = Kj, Kuusi oikein = Ko, Runkosarjan 1. = R1, Runkosarjan viimeinen = Rv");
        jf.add(l,BorderLayout.SOUTH);
        //frame.add(b);
        //frame.add(draw(),BorderLayout.CENTER);
    }
    private int korkeus(){
        if(suurinarvaus.length>liiga.length){
            return(suurinarvaus.length*25);
        }
        else{
            return(liiga.length*25);
        }
    }
    protected JPanel tarkemmatpisteet(int p){
        JPanel t = new JPanel();
        Object rowData[][] = new Object[8][17];
        for (int i = 0; i<15;i++) {
            rowData[0][i+1] = liiga[i].givePlace();
            rowData[1][i+1] = arvaukset[p].teams[i].givePlace();
            for (int k=0;k<6;k++) {
                if (arvaukset[p].teamsPoints[k][i]==0&&k>0)
                    rowData[k+2][i+1] = "--";
                else
                    rowData[k+2][i+1] = arvaukset[p].teamsPoints[k][i];
            }
        }
        for (int k=0;k<6;k++) {
            rowData[k+2][16] = arvaukset[p].pointsList[k];
        }
        rowData[0][0] ="Liiga";
        rowData[1][0] ="Arvaus";
        rowData[2][0] ="Pa";
        rowData[3][0] ="Po";
        rowData[4][0] ="Kj";
        rowData[5][0] ="Ko";
        rowData[6][0] ="R1";
        rowData[7][0] ="Rv";
        rowData[0][16] ="--";
        rowData[1][16] ="--";
        Object columnNames[] = new Object[17];
        for(int f=0;f<15;f++){
            columnNames[f+1] = liiga[f].giveName();
        }
        columnNames[16] = "Yhteensä";
        columnNames[0] = " ";
        JTable table = new JTable(rowData, columnNames);
        JLabel pl = new JLabel(arvaukset[p].getName()+" Pisteet:"+arvaukset[p].getPoints());
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(650,128));
        JLabel l = new JLabel(" ");
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setSize(20,20);
        t.setLayout(new BorderLayout());
        t.add(pl,BorderLayout.NORTH);
        t.add(scrollPane,BorderLayout.CENTER);
        t.add(l,BorderLayout.SOUTH);
        return t;
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("refresh");
        sarjataulukkoJPanel();
        a.removeAll();
        b.removeAll();
        c.removeAll();
        d.removeAll();


        b.add(pisteJPanel());
        a.add(sarjataulukkoJPanel());
        JLabel cl;
        if(lataus){
            cl = new JLabel("Download done");
            cl.setForeground(Color.WHITE);
            c.add(cl);
            c.setBackground(Color.blue);
        }
        else{
            cl = new JLabel("Download fail");
            cl.setForeground(Color.WHITE);
            c.add(cl);
            c.setBackground(Color.red);
            lataus = true;
        }
        d.add(taulukko());

        d.setLayout(new FlowLayout());
        b.setLayout(new FlowLayout());
        a.setLayout(new FlowLayout());

        a.revalidate();
        a.repaint();

        b.revalidate();
        b.repaint();

        d.revalidate();
        d.repaint();

        c.revalidate();
        c.repaint();

        //a.revalidate();
    }//actionPerformed
    
    public static void main(String[] args) {
        new LiigaVeikkaus().setVisible(true);
    }//main

    //SarjataulukkoPanel
    private JPanel sarjataulukkoJPanel(){
        JPanel p = new JPanel();
        new Sarjataulukko().lataa();
        new Arvaukset().laskenta();
        new Arvaukset().johto();
        //p.setSize(200, 100);
        p.setSize(new Dimension());
        p.setLayout(new GridLayout(16,0));
        p.setVisible(true);
        //System.out.println("noni");
        String[] s = new Sarjataulukko().oikein();
        //System.out.println("Tassa");
        JLabel b = new JLabel("Sarjataulukko");
        p.add(b);
        for(int i = 0;i<s.length;i++){
            JLabel a = new JLabel(s[i]);
            if((10>i)&&(i>4)){
                a.setForeground(Color.blue);
            }
            //a.setLayout(new GridLayout(1,1));
            //a.setVisible(true);
            p.add(a);
            //System.out.println(s[i]);
        }
        return p;
    }
    //pistePanel()
    private JPanel pisteJPanel(){
        JPanel p = new JPanel();
        Arvaukset al = new Arvaukset();
        al.laskenta();
        al.johto();
        p.setSize(new Dimension());
        p.setLayout(new GridLayout(arvaukset.length+1,0));
        //p.setVisible(true);
        String[] s = al.johto();
        JLabel b = new JLabel("Pistetaulukko");
        p.add(b);
        for(int i = 0;i<s.length;i++){
            JLabel a = new JLabel(s[i]);
            p.add(a);
            //System.out.println(s[i]);
        }
        return p;
    }

    //Arvaukset
    protected class Arvaukset{
        private Scanner rG;
        protected void lataa() {
            try{
                rG = new Scanner(new File("./liigaArvaukset.txt"));
                String name = "";
                int points;
                int g = 0;
                boolean newP = true;
                int h = rG.nextInt();
                arvaukset = new TeamsGuess[h];
                while(rG.hasNext()){
                    name = rG.next();
                    points = rG.nextInt();
                    Teams[] tG = TG();
                    
                    arvaukset[g] = new TeamsGuess(name,tG);
                    arvaukset[g].setPoints(points);
                    g++;
                }
                rG.close();
            }
            catch (Exception e){
                System.out.println("Arvausten lataus epäonnistui");
            }
        }//lataus
        private Teams[] TG(){
            Teams[] tG = new Teams[15];
            for(int i=0;i<15;i++){
                String a = rG.next();
                //System.out.println(a);
                int b = rG.nextInt();
                tG[i] = new Teams(a,b);
            }
            return tG;
        }//TG
        protected void tallennus(){
            try{
                PrintWriter pw = new PrintWriter(new FileWriter("./liigaArvaukset.txt"));
                pw.println(arvaukset.length);
                if(arvaukset.length>0){
                    for(int k=0;k<arvaukset.length;k++){
                        pw.println(arvaukset[k].getName()+" "+arvaukset[k].getPoints());
                        Teams[] teamsG = arvaukset[k].getTeams();
                        for(int t=0;t<15;t++){
                            pw.println(teamsG[t].giveName()+" "+teamsG[t].givePlace());
                        }
                    }
                }
                pw.close();
            }catch (Exception e){
                System.out.println("Arvausten tallenus epäonnistui");
            }
        }//Tallennus
        protected void laskenta(){
            lataa();
            for(int i=0;i<arvaukset.length;i++){
                arvaukset[i].setPoints(pisteet(i));
                //System.out.println(arvaukset[i].getName()+" "+arvaukset[i].getPoints());
            }
            tallennus();
        }//laskenta
        protected int pisteet(int i){
            int points = 0;
            int arvaus = 0;
            int off = 0;
            int kj = 0;
            int ko = 0;
            int ry = 0;
            int rv = 0;
                System.out.println(arvaukset[i].getName());
                for(int t=0;t<liiga.length;t++){
                    int a = arvaukset[i].getTeam(t).givePlace();
                    int b = liiga[t].givePlace();
                    int c = Math.abs(b-a);
                    int kuusi = 0;
                    //System.out.println(liiga[t].giveName()+" "+((c*(-1))+3));
                    arvaus += (c*(-1))+3;
                    arvaukset[i].pointsList[0] += (c*(-1))+3;
                    arvaukset[i].teamsPoints[0][t] = (c*(-1))+3;
                    points = points+((c*(-1))+3);
                    //Play-off
                    if((a<11)&&(b<11)){
                        points++;
                        arvaukset[i].teamsPoints[1][t]++;
                        arvaukset[i].pointsList[1]++;
                        off++;
                    }
                    //kuuden joukossa
                    if((a<7)&&(b<7)){
                        points++;
                        kuusi++;
                        kj++;
                        arvaukset[i].teamsPoints[2][t]++;
                        arvaukset[i].pointsList[2]++;
                        if(kuusi==6){
                            arvaukset[i].teamsPoints[3][t]++;
                            arvaukset[i].pointsList[3]++;
                            ko++;
                            points++;
                        }
                    }
                    //viimeinen oikein
                    if((a==15)&&(b==15)){
                        points++;
                        arvaukset[i].teamsPoints[5][t]++;
                        arvaukset[i].pointsList[5]++;
                        rv++;
                    }
                    //1. oikein
                    if((a==1)&&(b==1)){
                        points++;
                        ry++;
                        arvaukset[i].teamsPoints[4][t]++;
                        arvaukset[i].pointsList[4]++;
                    }
                }
                System.out.println("Arvaus:"+arvaus+" Play-off:"+off+" Kuudenjoukossa:"+kj+" Kuusioikein:"+ko+" eka:"+ry+" vika:"+rv+"\n");
        return points;
        }//pisteet
        protected String[] johto(){
            suurinarvaus = new TeamsGuess[arvaukset.length];
            ArrayList<TeamsGuess> ja = new ArrayList<TeamsGuess>();
            for(int x = 0;x<arvaukset.length;x++){
                ja.add(arvaukset[x]);
            }
            String[] list = new String[arvaukset.length];
            int a = 0;
            for(int i = 0;i<arvaukset.length;i++){
                a=0;
                for(int p =0;p<ja.size();p++){
                    //System.out.println(p);
                    if(ja.get(a).getPoints()<=ja.get(p).getPoints()){
                        a = p;
                        //System.out.println(p);
                    }
                }
                list[i] = (i+1)+". "+ja.get(a).getName()+" "+ja.get(a).getPoints();
                suurinarvaus[i]=ja.get(a);
                //System.out.println((i+1)+". "+ja.get(a).getName()+" "+ja.get(a).getPoints());
                ja.remove(a);
                for(int x = 0;x<ja.size();x++){
                    //System.out.println(ja.get(x).getName());
                    
                }
            }
            return list;
        }//johtaja
    }//Arvaukset

    //Sarjataulukko
    protected class Sarjataulukko{
        protected void lue(){
            try{
                Scanner rL = new Scanner(new File("./liiga.txt"));
                int i = 0;
                while(rL.hasNext()){
                    String a = rL.next();
                    int b = rL.nextInt();
                    liiga[i] = new Teams(a,b);
                    i++;
                }
                rL.close();
            }
            catch (Exception e){
                System.out.println("Sarjataulukon lukeminen ep�onnistui");
            }
        }//Lue
        private void find(String a, int t, String[] list){
            String alku = "class="+'"'+"ta-l"+'"';
            for(int i=0;i<15;i++){
                if(a.equals(alku+list[i])){
                    liiga[i].setPlace(t);
                    System.out.println(liiga[i].giveName()+liiga[i].givePlace());
                }
            }
        }
        protected void lataa(){
            lue();
            String a="";
            String b="";
            kk = new Scanner(a);
            String alku = "class="+'"'+"ta-l"+'"';
            String num1 = "class="+'"'+"index"+'"'+">";
            String num2 = ".</td>";
            int t = 1;
            int o = 0;
            String[] list = new String[15];
            try{
                PrintWriter lr = new PrintWriter(new FileWriter("./liigaURL.txt"));

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
                Scanner tL = new Scanner(new File("./haku.txt"));
                while(tL.hasNext()){
                    list[o]=tL.next();
                    o++;
                }
                Scanner lR = new Scanner(new File("./liigaURL.txt"));
                while(lR.hasNext()){
                    a = lR.next();
                    //System.out.println(a.equals(num1+1+num2)+" "+a);
                    if(a.equals(num1+t+num2)){
                        //System.out.println(a);
                        lR.next();
                        a = lR.next();
                        //System.out.println(a);
                        for(int i=0;i<15;i++){
                            //System.out.println(alku+list[i]);
                            if(a.equals(alku+list[i])){
                                System.out.print("Find: ");
                                liiga[i].setPlace(t);
                                System.out.println(liiga[i].giveName()+" "+liiga[i].givePlace());
                            }
                        }
                        t++;
                    }
                }
                in.close();
                tL.close();
                for(int i=0;i<15;i++){
                    //System.out.println(liiga[i].giveName());
                }
                PrintWriter kk = new PrintWriter(new FileWriter("./liigaURL.txt"));
                kk.println(" ");
                kk.close();
                tallenna();
            }
            catch (Exception e){
                System.out.println("lataus epäonnistui");
                lataus = false;
                lue();
            }
        }//Lataa
        protected void tallenna(){
            try{
                PrintWriter pw = new PrintWriter(new FileWriter("./liiga.txt"));
                for(int i=0;i<15;i++){
                    //System.out.println(liiga[i].giveName()+" "+liiga[i].givePlace());
                    pw.println(liiga[i].giveName()+" "+liiga[i].givePlace());
                }
                pw.close();
            }
            catch (Exception e){
                System.out.println("Sarjataulukon tallennus epäonnistui");
            }
        }//Tallennus
        protected String[] oikein(){
            String[] list = new String[15];
            for (int i = 1;i<16;i++) {
                for (int k=0;k<15;k++) {
                    if(liiga[k].givePlace()==i){
                        String a = i+". "+liiga[k].giveName();
                        //System.out.println(a);
                        list[i-1]=a;
                    }
                }
            }
            return list;
        }//oikein
    }//Sarjataulukko
    protected JPanel taulukko(){
        JPanel t = new JPanel();
        Object rowData[][] = new Object[suurinarvaus.length][9];
        for (int i = 0; i<suurinarvaus.length;i++) {
            rowData[i][0] = (i+1)+".";
            rowData[i][1] = suurinarvaus[i].getName();
            for (int k=0;k<6;k++) {
                    rowData[i][k+2] = suurinarvaus[i].pointsList[k];
            }
            rowData[i][8] = suurinarvaus[i].getPoints();
        }
        Object columnNames[] = {"Sijoitus","Pelaaja","Pa", "Po", "Kj", "Ko", "R1", "Rv", "Pisteet"};
        JTable table = new JTable(rowData, columnNames);
        JLabel l = new JLabel("Pisteet arvauksesta = Pa, Play-off pisteet = Po, Kuuden joukossa = Kj, Kuusi oikein = Ko, Runkosarjan 1. = R1, Runkosarjan viimeinen = Rv");
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(650,16*suurinarvaus.length));
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setSize(20,20);
        t.setLayout(new BorderLayout());
        t.add(scrollPane,BorderLayout.CENTER);
        t.add(l,BorderLayout.NORTH);
        return t;
    }
    //Teams
    public class Teams {
        final String name;
        int place;
        public Teams(String name) {this.name = name;}
        public Teams(String name, int place) {this.name = name; this.place=place;}
        //Asetetaan int place
        protected void setPlace(int place) {this.place = place;}
        //Annetaan name ja place
        protected String giveName() {return name;}
        protected int givePlace() {return place;}
        //Tulostus
        public String toString() {return "Team name: "+name+" Place: "+place;}
    }

    //TeamsGuess
    public class TeamsGuess{
        final String plauerName;
        final Teams[] teams;
        int points;
        int[] pointsList = new int[6];
        int[][] teamsPoints = new int[6][15];
        public TeamsGuess(String plauerName, Teams[] teams){
            this.plauerName = plauerName;
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
        protected Teams getTeam(int i){
            return teams[i];
        }
    }
}//LiigaVeikkaus