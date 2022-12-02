package main;

import entity.Enemy;
import entity.Enemy_Spawner;

import java.util.Timer;
import java.util.TimerTask;



public class GameTimer {

    private Enemy enemy;
    private GamePanel gamePanel;

    private final Enemy_Spawner enemy_spawner;
    public GameTimer(Enemy_Spawner enemy_spawner, GamePanel gamePanel)
    {
        this.enemy_spawner = enemy_spawner;
    }



    public void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                enemy_spawner.spawnEnemy();
                enemy_spawner.moveEnemies();
                System.out.println("TimeBtwSpawn = " + enemy_spawner.getTimeBetweenSpawn());
                timer.cancel();

                if (enemy_spawner.getGamePanel().getGameState() == 2) startTimer();
            }
        }, enemy_spawner.getTimeBetweenSpawn(), enemy_spawner.getTimeBetweenSpawn());
    }


}

