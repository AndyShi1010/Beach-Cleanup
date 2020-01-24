import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {

    private GameFrame frame;
    private Image background;
//    private ArrayList<GameObject> panelObjects = new ArrayList<GameObject>();
    private Player player;
    private Dumpster dumpster;
    private int countdown = 3;

    private Timer gameStartDelay = new Timer (1000, this);
    private Timer gameStart = new Timer (1000 * countdown, this);
    private Timer gamePlayTimer = new Timer (45000, this);
    private int spawnAmount = 25;

    private String[] factArray = new String[]{"Scientists have recently discovered microplastics embedded deep in the Arctic ice.",
                                                "In 2016, a global population of more than 7 billion people produced over 320 million tons of plastic",
                                                "Every day approximately 8 million pieces of plastic pollution find their way into our oceans.",
                                                "There may now be around 5.25 trillion macro and microplastic pieces floating in the open ocean.",
                                                "Over 150 plastic bottles litter each mile of UK beaches.",
                                                "Recent studies have revealed marine plastic pollution in 100% of marine turtles, 59% of whales, 36% of seals and 40% of seabird species examined."};

    private ArrayList<Trash> trash = new ArrayList<Trash>();

    public GamePanel (GameFrame frame) {
        try {
            background = ImageIO.read(new File("Assets/gamebkg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Call Player constructor");
            Player player = new Player(50, 50, 8, frame);
            this.player = player;
            dumpster = new Dumpster(640/2, 50, frame);
            dumpster.setX((frame.getWidth()/2) - (dumpster.getWidth()/2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.frame = frame;
        frame.repaint();
        gameStart.start();
        gameStartDelay.start();
    }

//    public ArrayList<GameObject> getPanelObjects() {
//        return panelObjects;
//    }
//
//    public void addPanelObjects(GameObject object) {
//        this.panelObjects.add(object);
//    }

    public Image getBackgroundImage() {
        return background;
    }

    public void setBackgroundImage(Image background) {
        this.background = background;
    }

    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        g.drawImage(this.getBackgroundImage(), 0,0, null);
        dumpster.paint(g);
        if (countdown > 0) {
            try {
                g.drawImage(ImageIO.read(new File("Assets/" + countdown + ".png")), 300, 100, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < trash.size(); i++) {
            trash.get(i).paint(g);
        }
        player.paint(g);
//        if (trash.size() <= 0 && gameStart.isRunning() == false) {
//            spawnAmount += 10;
//            spawnTrash();
//            gamePlayTimer.restart();
//        }
        if (player.isBucketFull() == false) {
            for (int i = 0; i < trash.size(); i++) {
                if (player.getHitbox().intersects(trash.get(i).getHitbox())) {
                    trash.remove(i);
                    player.addToBucket();
                    player.setMoveSpeed(player.getMoveSpeed() - 1);
                }
            }
        }
        if (trash.size() <= 0 && player.getBucket() == 0 && gamePlayTimer.isRunning()) {
            gamePlayTimer.stop();
            JOptionPane.showMessageDialog(frame, "You Win! :) \nDid you know: " + getRandomFact());
            player.setDisabled(true);
            MenuPanel menuPanel = new MenuPanel(frame);
            frame.setCurrentPanel(menuPanel);
        }
        if (player.getHitbox().intersects(dumpster.getHitbox())) {
            player.increaseTotalBottles(player.getBucket());
            player.emptyBucket();
        }

    }

    public void spawnTrash() {
        for (int i = 0; i < spawnAmount; i++) {
            int x = (int)(Math.random() * (((frame.getWidth() - 50) - 50) + 1) + 50);
            int y = (int)(Math.random() * (((frame.getHeight() - 50) - 100) + 1) + 100);
            try {
                trash.add(new Trash(x, y, ImageIO.read(new File("Assets/bottle.png")), frame));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getRandomFact () {
        int num = (int) (Math.random() * ((factArray.length - 1) + 1));
        return factArray[num];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameStartDelay) {
            if (countdown > 0) {
                player.setDisabled(true);
                System.out.println(countdown);
                frame.repaint();
                countdown--;
                gameStartDelay.restart();
            } else {
                gameStartDelay.stop();
                player.setDisabled(false);
            }
        }
        if (e.getSource() == gameStart) {
            spawnTrash();
            gameStart.stop();
            gamePlayTimer.start();
        }
        if (e.getSource() == gamePlayTimer) {
            JOptionPane.showMessageDialog(frame, "Game Over :( \nYou collected " + player.getTotalBottles() + " bottles. \nDid you know: " + getRandomFact());
            gamePlayTimer.stop();
            player.setDisabled(true);
            MenuPanel menuPanel = new MenuPanel(frame);
            frame.setCurrentPanel(menuPanel);
        }
    }
}
