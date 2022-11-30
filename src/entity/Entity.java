package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    protected GamePanel gamePanel;

    public int x,y;

    public int state;

    protected boolean alive = true;

    public BufferedImage s1, s2, s3;

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
            case 1 -> s1;
            case 2 -> s2;
            case 3 -> s3;
            default -> null;
        };

        if (alive)
        {
            g2.drawImage(image, x,y,gamePanel.tileSize,gamePanel.tileSize,null);
        }

    }
}
