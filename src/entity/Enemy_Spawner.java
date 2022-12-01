package entity;

import main.GameTimer;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;


public class Enemy_Spawner extends Entity {


    private int defaultTimeBetweenSpawn;
    private int timeBetweenSpawn;

    private int enemySpawnLocationX;
    private int enemySpawnLocationY;

    private int enemy_speed;

    GameTimer gameTimer = new GameTimer(this);

    public Enemy_Spawner (GamePanel gamePanel, int defaultTimeBetweenSpawn, int enemy_speed)
    {
        this.gamePanel = gamePanel;
        this.defaultTimeBetweenSpawn = defaultTimeBetweenSpawn;
        this.timeBetweenSpawn = this.defaultTimeBetweenSpawn;
        this.enemy_speed = enemy_speed;

        gameTimer.Start1(timeBetweenSpawn);

        setDefaultPosition();
        getSpawnerImage();
    }

    public void setDefaultPosition ()
    {
        x = 900;
        y = 400;
        state = 1;

        enemySpawnLocationX = this.x - 100;
        enemySpawnLocationY = this.y;
    }

    public void getSpawnerImage()
    {
        try
        {
           // System.out.println("spawner image loading started");
            s1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy_spawner.png"));

           // System.out.println("spawner image loading ended");
        } catch (IOException e)
        {
            System.out.println("ERROR!");
        }
    }

    public void update ()
    {
            ;
    }


    public void setDefaultTimeBetweenSpawn (int defaultTimeBetweenSpawn)
    {
        this.defaultTimeBetweenSpawn = defaultTimeBetweenSpawn;
    }

    public void setTimeBetweenSpawn(int timeBetweenSpawn) {
        this.timeBetweenSpawn = timeBetweenSpawn;
    }


    private final ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public void spawnEnemy ()
    {
        enemies.add(new Enemy(gamePanel, enemySpawnLocationX, enemySpawnLocationY, enemy_speed, this));
    }


    public void moveEnemies ()
    {
        int n = enemies.size();
        for (int i = 0; i < n; i++)
        {
            enemies.get(i).Move();
            //System.out.println("updating " + enemies.get(i));
        }
    }

    public void drawEnemies (Graphics2D g2)
    {
        int n = enemies.size();
        for (int i = 0; i < n; i++)
        {
            enemies.get(i).draw(g2);
            // System.out.println(i + " enemy state : " + enemies.get(i).state);
        }
    }

    public GameTimer getGameTimer()
    {
        return gameTimer;
    }

    public int getTimeBetweenSpawn()
    {
        return timeBetweenSpawn;
    }

    public Timer getTimer1 ()
    {
        return gameTimer.getTimer1();
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
            g2.drawImage(image, x,y,gamePanel.tileSize * 2,gamePanel.tileSize,null);
        }

    }


}
