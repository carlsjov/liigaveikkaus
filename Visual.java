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

   public static void main(String[] args) {
       new Visual().setVisible(true);
   }//main
   
   private Visual() {
       super("LiigaVeikkaus");
       setSize(600, 600);
       setResizable(false);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       JPanel p1 = new JPanel();
       JPanel p2 = new JPanel();
       p1.setBackground(Color.YELLOW);
       p2.setBackground(Color.BLUE);
       p1.setLayout(new FlowLayout());
       p2.setLayout(new GridLayout(15,1));

       JButton kokeilu = new JButton("Nappi");
       kokeilu.addActionListener(this);

       JMenuBar bar = new JMenuBar();
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
       p1.add(kokeilu);
       LiigaJoukkueetVisual a = new LiigaJoukkueetVisual();
       JButton[] joukkueet = a.painikkeet();
       for(int i=0;i<15;i++){
           p2.add(joukkueet[i]);
       }
       add(p1,BorderLayout.WEST);
       add(p2, BorderLayout.EAST);
       setJMenuBar(bar);
   }//Visual()

   public void actionPerformed (ActionEvent e){
       System.out.println("Nappia painettiin");
   }//actionPerformed
}//Visual