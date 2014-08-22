package ru.pabloader.runnergame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author P@bloid
 */
public class MainPanel extends JPanel implements KeyListener {
    public static final int SPEED = 5;
    private static MainPanel instance;
    private int fps = 0, ifps;
    private long fpsTs = System.currentTimeMillis();
    private long prevFrameTs = System.nanoTime();
    private double dt = 0;
    private Entity player, obstacle;
    private int floor;
    private int height, width;
    private final int strokesWidth = 100;
    private final World world = new World();
    public static boolean keyPressed = false;

    private MainPanel() {
    }

    public static MainPanel getInstance() {
        if (instance == null)
            instance = new MainPanel();
        return instance;
    }

    public void init() {
        height = getHeight();
        width = getWidth();
        floor = height * 3 / 4;
        player = new Entity(100, floor - 40, 20, 40, Color.ORANGE);
        world.addEntity(player);
        obstacle = new StaticEntity(700, floor - 30, 30, 30, Color.RED);
        world.addEntity(obstacle);
    }

    private void drawBackground(Graphics g) {
        int offset = (int) (System.currentTimeMillis() / SPEED) % strokesWidth;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);
        g.drawLine(0, floor, width, floor);
        for (int i = -offset; i < width + strokesWidth * 2; i += strokesWidth)
            g.drawLine(i, 0, i, height);
    }

    private void timing() {
        if (fpsTs <= System.currentTimeMillis() - 1000) {
            fps = ifps;
            ifps = 0;
            fpsTs = System.currentTimeMillis();
        } else
            ifps++;
        dt = (System.nanoTime() - prevFrameTs) / 1e6;//in ms
        prevFrameTs = System.nanoTime();
    }

    @Override
    public void paint(Graphics g) {
        timing();
        drawBackground(g);
        g.drawString(String.format("%d    %.2fms", fps, dt), 5, 15);
        world.tick(dt);
        world.draw(g);
        if (!obstacle.alive) {
            obstacle = new StaticEntity(width + 100, floor - 40, 30, 30, Color.RED);
            world.addEntity(obstacle);
        }
        if (player.collides(obstacle)){
            player.alive = false;
            player = new StaticEntity(player.x, player.y, player.w, player.h, Color.PINK);
            world.addEntity(player);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        player.jump();
        keyPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keyPressed = false;
    }
}
