package main;

import entity.Enemy;
import entity.Enemy_Spawner;
import entity.Player;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIContainer;
import ui.UIRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 4;
                // Tile size = 64
    final public int tileSize = originalTileSize * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 14;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int defaultTimeBetweenSpawn = 1500; // In milliseconds

    final int enemy_speed = 70;

    private int score;

    private boolean running;
    private final double updateRate = 1.0d; // UPDATES_PER_SECOND;


    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;



    Player player = new Player(this, keyHandler);
    Enemy_Spawner enemy_spawner = new Enemy_Spawner(this, defaultTimeBetweenSpawn, enemy_speed);

    private ArrayList<UIContainer> uiContainers = new ArrayList<UIContainer>();

    public GamePanel () {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }



    private UIRenderer uiRenderer = new UIRenderer();

    public ArrayList<UIContainer> getUiContainers()
    {
        return uiContainers;
    }

    public void startGameThread (){

        gameThread = new Thread(this);
        gameThread.start();
        initializeUI ();

    }

    public void initializeUI ()
    {
        HorizontalContainer container = new HorizontalContainer();

        container.setPadding(new Spacing(50));
        container.setBackgroundColor(Color.DARK_GRAY);
        container.addUIComponent(new HorizontalContainer());
        container.addUIComponent(new HorizontalContainer());
        container.addUIComponent(new HorizontalContainer());
        uiContainers.add(container);
    }


    @Override
    public void run()
    {

        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        while(running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            if(accumulator >= updateRate) {
                while(accumulator >= updateRate) {
                    update();
                    accumulator -= updateRate;
                }
            }
            repaint();
        }

    }

    //update();

    //repaint();

    public void update()
    {
        player.update();
        enemy_spawner.update();
        uiContainers.forEach(UIContainer::update);

    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2  = (Graphics2D) g;

        player.draw(g2);
        enemy_spawner.draw(g2);
        enemy_spawner.drawEnemies(g2);


        uiRenderer.renderUI(this, g);

        g2.dispose();

    }

    public void playerAttacked (Enemy enemy)
    {
        if (player.getState() == enemy.getState())
        {
           enemy.startDying(enemy_spawner.getTimeBetweenSpawn());
        } else
        {
            System.out.println("GAME OVER");
        }
    }

    public void addScore ()
    {
        score++;
        System.out.println("Score : " + score);
    }

    public int getScore()
    {
        return score;
    }
}
