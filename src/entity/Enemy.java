package entity;

import main.FPS;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy extends Entity {

    private final int speed;

    public Enemy (GamePanel gamePanel,int defaultX, int defaultY, int speed)
    {
        this.gamePanel = gamePanel;
        this.speed = speed;

        setDefaultPosition(defaultX, defaultY);
        getEnemyImage();

        Random random = new Random();
        state = random.nextInt(1,3+1); // State is equal to random number : 1, 2 or 3
    }

    public void setDefaultPosition (int defaultX, int defaultY)
    {
        x = defaultX;
        y = defaultY;
        state = 1;
    }

    public void getEnemyImage()
    {
        try
        {
            System.out.println("enemy image loading started");
            s1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/1E.png"));
            s2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/2E.png"));
            s3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/3E.png"));

            System.out.println("enemy image loading ended");
        } catch (IOException e)
        {
            System.out.println("ERROR!");
        }
    }



    public void update ()
    {
        ;

    }
    public void draw (Graphics2D g2)
    {
        BufferedImage image = switch (state) {
            case 1 -> s1;
            case 2 -> s2;
            case 3 -> s3;
            default -> null;
        };

        g2.drawImage(image, x,y,gamePanel.tileSize,gamePanel.tileSize,null);

    }

    public void Move()
    {
        this.x -= speed;
    }
}
