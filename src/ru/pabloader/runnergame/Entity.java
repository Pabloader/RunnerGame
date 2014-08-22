package ru.pabloader.runnergame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author P@bloid
 */
public class Entity {
    public double x;
    public double y;
    public double vy;
    public int floor;
    public int w, h;
    public Color color;
    public World world;
    public boolean alive;

    public Entity(double x, double y, int w, int h, Color color) {
        this.x = x;
        this.y =  y;this.floor = (int)y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.vy = 0;
        this.alive  = true;
    }

    public void tick(double dt) {
        y += vy;
        if (onGround()) {
            vy = 0;
            y = floor;
        } else if(vy<0)
            vy += dt/(MainPanel.SPEED*25);
        else if(vy>=0)
            vy+= dt/(MainPanel.SPEED*250);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        int yy = (int) (y);
        g.fillRect((int)x, yy, w, h);
    }

    public boolean onGround() {
        return (int) y >= (int) floor;
    }

    public void jump() {
        if (!onGround())
            return;
        vy = -2;
    }

    public boolean collides(Entity o) {
        if (o.x + o.w < this.x)
            return false;
        if (o.x > this.x + this.w)
            return false;
        if (o.y + o.h < this.y)
            return false;
        if (o.y > this.y + this.h)
            return false;
        return true;
    }
}
