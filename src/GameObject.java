import java.awt.*;

public class GameObject {
    private int x, y, width, height;
    private Image sprite;
    private Rectangle hitbox;
    private GameFrame gameFrame;

    public GameObject(int x, int y, Image sprite, GameFrame gameFrame) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.gameFrame = gameFrame;
        this.width = sprite.getWidth(null);
        this.height = sprite.getHeight(null);
        this.hitbox = new Rectangle();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.setHitbox(new Rectangle(x, this.y , this.getWidth(), this.getHeight()));
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public void paint(Graphics g) {
        g.drawImage(this.getSprite(), x , y , null);
    }
}
