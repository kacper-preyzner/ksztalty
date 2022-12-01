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
            s1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_1.png"));
            s2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_2.png"));
            s3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_3.png"));
            s4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_4.png"));
            s5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_5.png"));

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
            case 4 -> s4;
            case 5 -> s5;
            default -> null;
        };

        if (alive)
        {
            g2.drawImage(image, x,y,gamePanel.getScreenWidth(),gamePanel.getScreenHeight(),null);
        }

    }

    public void nextBgState ()
    {
        state++;
    }
}
