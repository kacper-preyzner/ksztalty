package main;

import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background extends Entity {

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
        try
        {
            System.out.println("Background image loading started");
            s1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background.png"));

             System.out.println("Background image loading ended");
        } catch (IOException e)
        {
            System.out.println("ERROR!");
        }
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
            g2.drawImage(image, x,y,gamePanel.screenWidth,gamePanel.screenHeight,null);
        }



    }
}
