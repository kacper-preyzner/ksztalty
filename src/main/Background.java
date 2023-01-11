package main;

import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background extends Entity {

    final int maxBGState = 17;

    public Background(int x, int y,GamePanel gamePanel)
    {

        this.gamePanel = gamePanel;
        getBackgroundImage();
        setDefaultPosition(x,y);
    }

    public void setDefaultPosition (int x, int y)
    {
        this.x = x;
        this.y = y;
        state = 1;

    }

    public void getBackgroundImage()
    {
        System.out.println("Background image loading started");
        String path;
        try
        {
            for (int i = 1; i <= maxBGState; i++)
            {
                path = ("background/background_" + i + ".png");
                bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream(path)));
            }

             System.out.println("Background image loading ended");
        } catch (IOException e)
        {
            System.out.println("ERROR!");
        }
    }

    public void draw (Graphics2D g2)
    {
        BufferedImage image = bufferedImages.get(state - 1);


        if (alive)
        {
            g2.drawImage(image, x,y,gamePanel.getScreenWidth(),gamePanel.getScreenHeight(),null);
        }

    }

    public void setBgState(int state)
    {
        this.state = state;
    }
}
