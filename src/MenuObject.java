import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuObject extends JComponent implements MouseListener {

    private Image sprite;
    private int x, y, width, height;

    public MenuObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MenuObject(int x, int y, Image sprite, JFrame currentFrame) {
        this.x = x;
        this.y = y;
        this.width = sprite.getWidth(null);
        this.height = sprite.getHeight(null);
        this.sprite = sprite;
        super.setLocation(x, y);
        super.setSize(sprite.getWidth(null), sprite.getHeight(null));
        this.setVisible(true);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        System.out.println("MenuObject Paint");
        g.drawImage(sprite, x - (this.width/2) , y - (this.height/2), null);
        g.drawRect(x - (this.width/2), y - (this.height/2), sprite.getWidth(null), sprite.getHeight(null));
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLick");
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
