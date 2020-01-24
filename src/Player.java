import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject implements KeyListener{

    private double moveSpeed;
    private GameFrame frame;
    private boolean up, down, left, right = false;
    private int bucket;
    private boolean bucketFull = false;
    private boolean disabled;
    private int totalBottles;

    public Player (int x, int y, double moveSpeed, GameFrame frame) throws IOException {

        super(x, y, ImageIO.read(new File("Assets/player0.png")), frame);
        this.setHitbox(new Rectangle(this.getX(),this.getY(),this.getWidth(), this.getHeight()));
        this.moveSpeed = moveSpeed;
        this.bucket = 0;
        this.frame = frame;
        this.disabled = true;
        frame.addKeyListener(this);
        System.out.println("Init: {" + this.getX() + "," + this.getY() + "}");
    }

    public void paint (Graphics g) {
        if (moveSpeed < 1) {
            moveSpeed = 1;
        }
//        System.out.println(bucket);
        if (bucket >= 5) {
            bucketFull = true;
//            System.out.println("Bucket Full");
        }
//        System.out.println("{" + this.getX() + ", " + this.getY() + "}");
        if (bucketFull) {
            try {
                this.setSprite(ImageIO.read(new File("Assets/player2.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (bucket > 0) {
            try {
                this.setSprite(ImageIO.read(new File("Assets/player1.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.setSprite(ImageIO.read(new File("Assets/player0.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(this.getSprite(), this.getX(), this.getY(), null);
//        g.setColor(Color.BLACK);
//        g.drawRect(this.getX(),this.getY(),this.getWidth(), this.getHeight());
//        g.setColor(Color.GREEN);
        this.setHitbox(new Rectangle(this.getX() + 10 ,this.getY() + 10,this.getWidth() - 20, this.getHeight() - 20));
//        g.drawRect(this.getHitbox().x, this.getHitbox().y, this.getHitbox().width, this.getHitbox().height);
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void addToBucket () {
        bucket++;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public boolean isBucketFull() {
        return bucketFull;
    }

    public int getBucket() {
        return bucket;
    }

    public void increaseTotalBottles (int num) {
        totalBottles += num;
    }

    public int getTotalBottles() {
        return totalBottles;
    }

    public void setBucket(int bucket) {
        this.bucket = bucket;
    }

    public void setBucketFull(boolean bucketFull) {
        this.bucketFull = bucketFull;
    }

    public void emptyBucket() {
        this.bucketFull = false;
        this.bucket = 0;
        this.moveSpeed = 8;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.getX() < 0 - (this.getWidth()/2)) { this.setX(0 - (this.getWidth()/2)); }
        if (this.getY() < 0) { this.setY(0); }
        if (disabled != true) {
            if (KeyEvent.getKeyText(e.getKeyCode()).equals("Left")) left = true;
            if (KeyEvent.getKeyText(e.getKeyCode()).equals("Right")) right = true;
            if (KeyEvent.getKeyText(e.getKeyCode()).equals("Up")) up = true;
            if (KeyEvent.getKeyText(e.getKeyCode()).equals("Down")) down = true;

            if (left && up) {
//                System.out.println("Diag LEFT UP");
                this.setX((int)(this.getX() - moveSpeed));
                this.setY((int)(this.getY() - moveSpeed));
            } else if (right && up) {
//                System.out.println("Diag RIGHT UP");
                this.setX((int)(this.getX() + moveSpeed));
                this.setY((int)(this.getY() - moveSpeed));
            } else if (left && down) {
//                System.out.println("Diag LEFT DOWN");
                this.setX((int)(this.getX() - moveSpeed));
                this.setY((int)(this.getY() + moveSpeed));
            } else if (right && down) {
//                System.out.println("Diag RIGHT DOWN");
                this.setX((int)(this.getX() + moveSpeed));
                this.setY((int)(this.getY() + moveSpeed));
            } else if (left) {
//                System.out.println("LEFT");
                this.setX((int)(this.getX() - moveSpeed));
            } else if (right) {
//                System.out.println("RIGHT");
                this.setX((int)(this.getX() + moveSpeed));
            } else if (down) {
//                System.out.println("DOWN");
                this.setY((int)(this.getY() + moveSpeed));
            } else if (up) {
//                System.out.println("UP");
                this.setY((int)(this.getY() - moveSpeed));
            }
            this.frame.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) up = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
    }

}
