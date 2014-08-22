package ru.pabloader.runnergame;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author P@bloid
 */
public class World {
    private final List<Entity> entities = new ArrayList<>();

    public boolean addEntity(Entity e) {
        e.world = this;
        return entities.add(e);
    }

    public boolean removeEntity(Entity o) {
        o.world = null;
        return entities.remove(o);
    }

    public void tick(double dt) {
        for (Iterator<Entity> it = entities.iterator(); it.hasNext();) {
            Entity ent = it.next();
            ent.tick(dt);
            if (ent.x + ent.w < 0)
                ent.alive = false;
            if (!ent.alive) {
                ent.world = null;
                it.remove();
            }
        }
    }

    public void draw(Graphics g) {
        for (Entity ent : entities)
            ent.draw(g);
    }
}
