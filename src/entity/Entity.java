package entity;

import main.GamePanel;

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
}
