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
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_1.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_2.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_3.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_4.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_5.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_6.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_7.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("background/background_8.png")));

             System.out.println("Background image loading ended");
        } catch (IOException e)
        {
            System.out.println("ERROR!");
        }
    }

    public void draw (Graphics2D g2)
    {
        BufferedImage image = switch (state) {
            case 1 -> bufferedImages.get(0);
            case 2 -> bufferedImages.get(1);
            case 3 -> bufferedImages.get(2);
            case 4 -> bufferedImages.get(3);
            case 5 -> bufferedImages.get(4);
            case 6 -> bufferedImages.get(5);
            case 7 -> bufferedImages.get(6);
            case 8 -> bufferedImages.get(7);
            default -> null;
        };

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
