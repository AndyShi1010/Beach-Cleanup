public class Runner{
    public static void main (String[] args) {
        GameFrame frame = new GameFrame();
        MenuPanel menuPanel = new MenuPanel(frame);
//        Renderer mainRenderer = new Renderer(1000, frame);
        frame.setCurrentPanel(menuPanel);
//        frame.repaint();
    }
}
