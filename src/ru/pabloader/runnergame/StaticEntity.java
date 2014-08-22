package ru.pabloader.runnergame;

import java.awt.Color;

/**
 *
 * @author P@bloid
 */
public class StaticEntity extends Entity {

    public StaticEntity(double x, double y, int w, int h, Color color) {
        super(x, y, w, h, color);
    }

    @Override
    public void tick(double dt) {
        x -= dt / MainPanel.SPEED;
    }
}
