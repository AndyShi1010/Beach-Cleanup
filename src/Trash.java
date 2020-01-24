import java.awt.*;

public class Trash extends GameObject {

    public Trash(int x, int y, Image sprite, GameFrame currentFrame) {
        super(x, y, sprite, currentFrame);
        this.setHitbox(new Rectangle(x, y , this.getWidth(), this.getHeight()));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        g.drawRect(this.getHitbox().x , this.getHitbox().y , this.getHitbox().width , this.getHitbox().height);
    }
}
