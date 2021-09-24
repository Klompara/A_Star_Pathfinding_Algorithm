import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

import Felder.Field;

public class Renderer extends Canvas implements Runnable {

    private JFrame frame;

    private final int WIDTH = 1080, HEIGHT = WIDTH;

    private Board board;

    private boolean running = true;

    public Renderer(Board board) {
        this.board = board;
        createFrame("A* Pathfinding");
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.scale(WIDTH / Double.valueOf(board.getWidth() + 50), HEIGHT / Double.valueOf(board.getHeight() + 50));
        g.translate(10, 10);

        for (ArrayList<Field> x : board.getBoard()) {
            for (Field field : x) {
                field.render(g);
            }
        }

        g.dispose();
        bs.show();
    }

    public void createFrame(String titel) {
        frame = new JFrame(titel);
        frame.setUndecorated(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        while (running) {
            if (frame != null)
                render();
        }
    }

}
