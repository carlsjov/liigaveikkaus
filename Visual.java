/**
* Visual
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Visual extends JFrame implements ActionListener{
   private static final long serialVersionUID = 1L;
   private JPanel p1 = new JPanel();
   private JPanel p2 = new JPanel();
   private JMenuBar bar = new JMenuBar();

   public static void main(String[] args) {
       new Visual().setVisible(true);
   }//main
   
   private Visual() {
       super("LiigaVeikkaus");
       setSize(600, 600);
       setResizable(false);
       //setLayout(new FlowLayout());
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       p1.setBackground(Color.YELLOW);
       p2.setBackground(Color.BLUE);
       p1.setLayout(new FlowLayout());
       p2.setLayout(new GridLayout(15,1));

       p1.setLayout(new FlowLayout());
       String[] columnNames = {"Teams","Place"};
       Object[][] data = {
           {"Lukko", new Integer(1)},
           {"K�rp�t", new Integer(2)}
       };
       JTable table = new JTable(data, columnNames);
       table.setFillsViewportHeight(true);
       table.setPreferredScrollableViewportSize(new Dimension(100, 30));
       JScrollPane scrollPane = new JScrollPane(table);
       p1.add(scrollPane);
       JButton kokeilu = new JButton("Nappi");
       kokeilu.addActionListener(this);
       Bar();
       //p1.add(kokeilu);
       MadeTeams();
       Tabel();
       add(p2,BorderLayout.WEST);
       add(p1);
       setJMenuBar(bar);
   }//Visual()

   public void actionPerformed (ActionEvent e){
       System.out.println("Nappia painettiin");
   }//actionPerformed
   private void MadeTeams() {
        Teams[] joukkueet = new Liigajoukkueet().getTeams();
        JButton[] painikkeet = new JButton[15];
        for(int i = 0; i<15;i++){
            painikkeet[i] = new JButton(joukkueet[i].giveName());
            painikkeet[i].setBackground(Color.red);
            p2.add(painikkeet[i]);
        }
   }
   private void Bar(){
       JMenu file = new JMenu("File");
       JMenuItem open = new JMenuItem("open");
       JMenuItem talo = new JMenuItem("talo");
       JMenuItem uusi = new JMenu("Uusi");
       JMenuItem exit = new JMenuItem("Exit");
       bar.add(file);
       file.add(uusi);
       uusi.add(talo);
       file.add(open);
       file.addSeparator();
       file.add(exit);
   }
   private void Tabel(){
       

   }
}//Visual