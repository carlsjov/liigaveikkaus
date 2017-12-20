/**
* Visual
*/
import java.awt.BorderLayout;
import java.awt.Color;
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
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       p1.setBackground(Color.YELLOW);
       p2.setBackground(Color.BLUE);
       p1.setLayout(new FlowLayout());
       p2.setLayout(new GridLayout(15,1));

       JButton kokeilu = new JButton("Nappi");
       kokeilu.addActionListener(this);
       Bar();
       p1.add(kokeilu);
       MadeTeams();
       add(p1,BorderLayout.WEST);
       add(p2);
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
}//Visual