package entity;

import main.FPS;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Entity {

    private final int speed;

    private Enemy_Spawner enemy_spawner;


    public Enemy (GamePanel gamePanel,int defaultX, int defaultY, int speed, Enemy_Spawner enemy_spawner)
    {
        this.gamePanel = gamePanel;
        this.speed = speed;
        this.enemy_spawner = enemy_spawner;

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
           // System.out.println("enemy image loading started");
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/1E.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/2E.png")));
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/3E.png")));

           // System.out.println("enemy image loading ended");
        } catch (IOException e)
        {
            System.out.println("ERROR!");
        }
    }



    public void update ()
    {


    }


    public void Move()
    {
        this.x -= speed;
        // System.out.println("Position : " + this.x);
        if (this.x == 100)
        {
            gamePanel.playerAttacked(this);
        }

    }

    private Timer timer = new Timer();
    private TimerTask task = new TimerTask()
    {

        @Override
        public void run()
        {
            Kill();
        }
    };

    public void startDying (int timeBetweenSpawn, boolean isKilledByPlayer)
    {
        int delay = timeBetweenSpawn / 2;
        timer.schedule(task,delay);
        //if (!isKilledByPlayer) return;
        gamePanel.addScore();
    }


}
