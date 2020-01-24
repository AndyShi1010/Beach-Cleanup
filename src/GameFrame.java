import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private int width, height, x, y;
    private final double screenWidth, screenHeight;
    private JPanel currentPanel;

    public GameFrame () {
        super("Beach Cleanup");

        this.setSize(640, 480);
        this.width = 640;
        this.height = 480;

        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int)(screenWidth/2) - (this.width/2), (int)(screenHeight/2) - (this.height/2));
        this.x = (int)(screenWidth/2) - (this.width/2);
        this.y = (int)(screenHeight/2) - (this.height/2);

        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
        this.setContentPane(currentPanel);
    }

    @Override
    public void paint(Graphics g) {
//        System.out.print(currentPanel.getClass());
        if (this.currentPanel != null ){
//            System.out.println("Current Panel Paint");
            this.currentPanel.paint(g);
        }
    }
}
