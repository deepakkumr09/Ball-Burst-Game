package code;
import javax.swing.JFrame;

public class MainClass {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Ball Burst Game");
        f.setSize(700,600);
        f.setLocationRelativeTo(null);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePlay gameplay = new GamePlay();   
        f.add(gameplay);

        f.setResizable(false);
        f.setVisible(true);
    }
}

