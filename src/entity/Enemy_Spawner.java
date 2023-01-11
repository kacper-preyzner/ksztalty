package entity;

import main.GameTimer;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Enemy_Spawner extends Entity {


    private int defaultTimeBetweenSpawn;
    private int timeBetweenSpawn;

    private int enemySpawnLocationX;
    private int enemySpawnLocationY;

    private final int enemy_speed;

    private final GamePanel gamePanel;

    private GameTimer gameTimer;

    public Enemy_Spawner (GamePanel gamePanel, int defaultTimeBetweenSpawn, int enemy_speed)
    {
        this.gamePanel = gamePanel;
        this.defaultTimeBetweenSpawn = defaultTimeBetweenSpawn;
        this.timeBetweenSpawn = this.defaultTimeBetweenSpawn;
        this.enemy_speed = enemy_speed;

        GameTimer gameTimer = new GameTimer(this, gamePanel);
        this.gameTimer = gameTimer;
        gameTimer.startTimer();

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
            bufferedImages.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy_spawner.png")));

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
        enemies.add(new Enemy(gamePanel, enemySpawnLocationX, enemySpawnLocationY, enemy_speed));
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


    public int getTimeBetweenSpawn()
    {
        return timeBetweenSpawn;
    }


    public void draw (Graphics2D g2)
    {
        BufferedImage image = switch (state) {
            case 1 -> bufferedImages.get(0);
            default -> null;
        };

        if (alive)
        {
            g2.drawImage(image, x - gamePanel.getTileSize(),y,gamePanel.getTileSize() * 2,gamePanel.getTileSize(),null);
        }
    }

    public GamePanel getGamePanel()
    {
        return gamePanel;
    }

    public GameTimer getGameTimer()
    {

        return gameTimer;
    }

    public void killAllEnemies ()
    {
        //enemies.forEach(enemy -> enemy.startDying(timeBetweenSpawn,false));
        enemies.forEach(enemy -> enemy.setAlive(false));
    }
}
