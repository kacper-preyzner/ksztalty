package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Entity {

    private final int speed;

    private static int counter1 = 0;
    private static int counter2 = 0;
    private static int counter3 = 0;

    Random random = new Random();


    public Enemy(GamePanel gamePanel, int defaultX, int defaultY, int speed)
    {
        this.gamePanel = gamePanel;
        this.speed = speed;

        setDefaultPosition(defaultX, defaultY);
        getEnemyImage();

        state = getRandomState();
    }

    private int getRandomState ()
    {
        int s;
        s = random.nextInt(1,3+1);
        int maxInARow = 3;
        switch (s) {
            case 1 -> {

                if (counter1 < maxInARow) {
                    counter1++;
                    counter2 = 0;
                    counter3 = 0;
                    return 1;

                }
                counter1 = 0;
                counter2 = 0;
                counter3 = 1;
                return 3;

            }
            case 2 -> {

                if (counter2 < maxInARow) {

                    counter1 = 0;
                    counter2++;
                    counter3 = 0;
                    return 2;
                } else {
                    counter1++;
                    counter2 = 0;
                    counter3 = 0;
                    return 1;

                }
            }
            case 3 -> {
                if (counter3 < maxInARow) {

                    counter1 = 0;
                    counter2 = 0;
                    counter3++;
                    return 3;
                } else {
                    counter1 = 0;
                    counter2++;
                    counter3 = 0;
                    return 2;

                }
            }

        }
        return 0;

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
            bufferedImages.add(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("enemy/1E.png"))));
            bufferedImages.add(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("enemy/2E.png"))));
            bufferedImages.add(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("enemy/3E.png"))));

           // System.out.println("enemy image loading ended");
        } catch (IOException e)
        {
            System.out.println("ERROR!");
        }
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

    private final Timer timer = new Timer();
    private final TimerTask task = new TimerTask()
    {

        @Override
        public void run()
        {
            Kill();
        }
    };

    public void startDying(int timeBetweenSpawn)
    {
        int delay = timeBetweenSpawn / 2;
        timer.schedule(task,delay);
        //if (!isKilledByPlayer) return;
        gamePanel.addScore();
    }


}
