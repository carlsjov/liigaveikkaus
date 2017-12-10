import javax.swing.JButton;

public class LiigaJoukkueetVisual extends JButton{

    public JButton[] painikkeet() {
        Liigajoukkueet a = new Liigajoukkueet();
        Teams[] joukkueet = a.getTeams();
        JButton[] painikkeet = new JButton[15];
        for(int i = 0; i<15;i++){
            painikkeet[i] = new JButton(joukkueet[i].giveName());
        }
        return painikkeet;
    }
}