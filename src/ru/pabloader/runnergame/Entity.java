package ru.pabloader.runnergame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author P@bloid
 */
public class Entity {
    public int x;
    public double y;
    public double vy;
    public int floor;
    public int w, h;
    public Color color;

    public Entity(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = this.floor = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.vy = 0;
    }

    public void tick(double dt) {
        y += vy;
        if (onGround()) {
            vy = 0;
            y = floor;
        } else
            vy += 0.01;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        int yy = (int) (y);
        g.fillRect(x, yy, w, h);
    }

    public boolean onGround() {
        return (int) y >= (int) floor;
    }

    public void jump() {
        if (!onGround())
            return;
        vy = -2;
    }
}
