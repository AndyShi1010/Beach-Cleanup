import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameTimer extends JComponent implements ActionListener {

    private int minutes;
    private int seconds;
    private int totalTime;
    private Timer timer;
    private GameFrame frame;

    public GameTimer (int minutes, int seconds, GameFrame frame) {
        this.minutes = minutes;
        this.seconds = (60 * minutes) + seconds;
        this.totalTime = minutes * seconds * 1000;
        timer = new Timer (1000, this);
        this.frame = frame;
    }

    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        System.out.println("Painting Timer");
        try {
            switch (minutes) {
                case '3' :
                    g.drawImage(ImageIO.read(new File("Assets/3.png")), 600, 50, null);
                case '2' :
                    g.drawImage(ImageIO.read(new File("Assets/2.png")), 600, 50, null);
                case '1' :
                    g.drawImage(ImageIO.read(new File("Assets/1.png")), 600, 50, null);
                default :
                    g.drawImage(ImageIO.read(new File("Assets/0.png")), 600, 50, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            this.repaint();
            this.frame.repaint();
            seconds--;
            if (seconds % 60 == 0) {
                minutes--;
            }
            System.out.println(minutes + ":" + seconds);
            if (seconds <= 0 && minutes <= 0) {
                timer.stop();
            }
        }
    }
}
