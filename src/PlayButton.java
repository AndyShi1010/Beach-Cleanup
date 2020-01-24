import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class PlayButton extends JComponent implements MouseListener {

    private GameFrame currentFrame;
    private Image sprite;
    private int x, y, width, height;
    private JPanel currentPanel;

    public PlayButton(int x, int y, GameFrame currentFrame, JPanel panel) throws IOException {
        this.sprite = ImageIO.read(new File("Assets/play.png"));
        this.x = x;
        this.y = y;
        this.width = sprite.getWidth(null);
        this.height = sprite.getHeight(null);
        super.setLocation(x, y);
        super.setSize(sprite.getWidth(null), sprite.getHeight(null));
        this.currentFrame = currentFrame;
        this.currentPanel = panel;
        addMouseListener(this);
        currentFrame.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        System.out.println("MenuObject Paint");
        g.drawImage(sprite, x - (this.width/2) , y - (this.height/2), null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentFrame.removeMouseListener(this);
        currentFrame.remove(currentPanel);
        GamePanel gamePanel = new GamePanel(currentFrame);
        currentFrame.setCurrentPanel(gamePanel);
        gamePanel.repaint();
        System.out.println("[Click]");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
