package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {

    protected GamePanel gamePanel;

    protected int x,y;

    protected int state = 1;

    protected boolean alive = true;

    protected ArrayList <BufferedImage> bufferedImages = new ArrayList <BufferedImage>();

    public int getState()
    {
        return state;
    }

    public void Kill ()
    {
        this.alive = false;
    }

    public void draw (Graphics2D g2)
    {
        BufferedImage image = switch (state) {
            case 1 -> bufferedImages.get(0);
            case 2 -> bufferedImages.get(1);
            case 3 -> bufferedImages.get(2);
            default -> null;
        };

        if (alive)
        {
            g2.drawImage(image, x,y,gamePanel.getTileSize(),gamePanel.getTileSize(),null);
        }

    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public boolean isAlive()
    {
        return alive;
    }
}
