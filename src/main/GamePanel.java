package main;

import audio.AudioManager;
import entity.Enemy;
import entity.Enemy_Spawner;
import entity.Player;
import ui.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 4;
                // Tile size = 64
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 18;
    private final int maxScreenRow = 14;

    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    private final int defaultTimeBetweenSpawn = 1500; // In milliseconds

    private final int enemy_speed = 70;

    private int score;

    private boolean running;
    private final double updateRate = 1/60d; // UPDATES_PER_SECOND;

    private int gameState = 2;


    KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public boolean isRunning() {
        return running;
    }

    public double getUpdateRate() {
        return updateRate;
    }

    public UIRenderer getUiRenderer() {
        return uiRenderer;
    }

    public GameBalancer getGameBalancer() {
        return gameBalancer;
    }

    private final Player player = new Player(this, keyHandler);
    private final Enemy_Spawner enemy_spawner = new Enemy_Spawner(this, defaultTimeBetweenSpawn, enemy_speed);
    private final Background background = new Background(0,0, this);

    private ArrayList<UIContainer> uiContainers = new ArrayList<UIContainer>();

    private final AudioManager audioManager = new AudioManager();

    BufferedImage logo;


    public GamePanel () throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        logo = (ImageIO.read(getClass().getClassLoader().getResourceAsStream("icon.png")));

    }


    public BufferedImage getLogo() {
        return logo;

    }

    private final UIRenderer uiRenderer = new UIRenderer();

    public ArrayList<UIContainer> getUiContainers()
    {
        return uiContainers;
    }

    public void startGameThread (){

        System.out.println("Screen Width : " + screenWidth);
        System.out.println("Screen Height : " + screenHeight);

        gameState = 1;

        gameThread = new Thread(this);
        gameThread.start();
        initializeUI ();

    }

    GameOverScreen gameOverScreen = new GameOverScreen("GAME OVER! PRESS R TO RETRY", this);
    StartScreen startScreen = new StartScreen("PRESS SPACE TO START", this);

    private final ScoreText scoreText = new ScoreText("SCORE : 0", this);

    public void initializeUI ()
    {
        VerticalContainer container = new VerticalContainer(this);
        HorizontalContainer centerUIContainer = new HorizontalContainer(this);


        container.setPadding(new Spacing(10));
        container.setBackgroundColor(new Color(134,134,137,0));

        uiContainers.add(container);
        container.addUIComponent(scoreText);
        uiContainers.add(centerUIContainer);

        centerUIContainer.setSize(new Size(screenWidth,screenHeight));
        centerUIContainer.setBackgroundColor(new Color(1,1,1,0));
        centerUIContainer.setAligment(new Aligment(Aligment.Position.CENTER, Aligment.Position.CENTER));

        centerUIContainer.addUIComponent(gameOverScreen);
        centerUIContainer.addUIComponent(startScreen);

    }


    @Override
    public void run()
    {

        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis() + 1000;

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


    public void update()
    {

        if (gameState != 3) player.update();

        if (gameState == 2)
        {
            enemy_spawner.update();


        }
        uiContainers.forEach(UIContainer::update);

    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2  = (Graphics2D) g;

        background.draw(g2);

        if (gameState != 3) player.draw(g2);
        if (gameState == 2)
        {

            enemy_spawner.draw(g2);
            enemy_spawner.drawEnemies(g2);
        }



        uiRenderer.renderUI(this, g);

        g2.dispose();

    }

    public void playerAttacked (Enemy enemy)
    {
        if (!enemy.isAlive()) return;

        if (player.getState() == enemy.getState())
        {
           enemy.startDying(enemy_spawner.getTimeBetweenSpawn());
        } else
        {
            System.out.println("GAME OVER");
            gameBalancer.balanceGame(score);
            gameState = 3;

            audioManager.stopMainTheme();
            audioManager.playDeathSound();
        }
    }

    private GameBalancer gameBalancer = new GameBalancer(background, enemy_spawner);


    public void addScore ()
    {
        if (gameState != 2) return;

        score++;
        System.out.println("Score : " + score);
        gameBalancer.balanceGame(score);

        audioManager.playMoveSound();
    }

    public int getScore()
    {
        return score;
    }

    public int getGameState()
    {
        return gameState;
    }

    public void restart()
    {
        this.gameState = 2;
        player.makeAlive();
        enemy_spawner.getGameTimer().startTimer();
        score = 0;
        enemy_spawner.killAllEnemies();
        gameBalancer.balanceGame(score);


        audioManager.playMainTheme();
    }

    public void startGame()
    {
        restart();
    }
}
