import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuPanel extends JPanel{

    private GameFrame frame;
    private Image background;
    private ArrayList<JComponent> panelComponents = new ArrayList<JComponent>();

    public MenuPanel(GameFrame frame) {
        Image background = null;
        try {
            background = ImageIO.read(new File("Assets/test.png"));
            PlayButton playButton = new PlayButton(320, 200, frame, this);
            this.background = background;
            this.addPanelComponent(playButton);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.frame = frame;
        frame.repaint();
    }

    public Image getBackgroundImage() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public void addPanelComponent(JComponent component) {
        panelComponents.add(component);
    }

    public void paint(Graphics g) {
        super.paint(g);
//        frame.repaint();
        g.drawImage(this.background, 0,0, null);
        for (int i = 0; i < panelComponents.size(); i++) {
            panelComponents.get(i).paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Did you know", 10, 600);
    }
}
