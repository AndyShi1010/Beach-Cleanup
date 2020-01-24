import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Dumpster extends GameObject {
    public Dumpster(int x, int y, GameFrame gameFrame) throws IOException {
        super(x, y, ImageIO.read(new File("Assets/dumpster.png")), gameFrame);
        this.setHitbox(new Rectangle(x, y , this.getWidth(), this.getHeight()));
    }

    public void paint(Graphics g) {
        super.paint(g);
//        g.drawRect(this.getHitbox().x, this.getHitbox().y, this.getHitbox().width, this.getHitbox().height);
    }
}
