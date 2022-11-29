package main;

import entity.Enemy_Spawner;

import java.util.Timer;
import java.util.TimerTask;

public class SpawnerCountdown {


    private Enemy_Spawner enemy_spawner;
    public SpawnerCountdown (Enemy_Spawner enemy_spawner)
    {
        this.enemy_spawner = enemy_spawner;
    }

    private Timer timer = new Timer();
    TimerTask task = new TimerTask()
    {
        @Override
        public void run()
        {
            System.out.println("Spawning enemy");
            enemy_spawner.spawnEnemy();
        }
    };


    public void Start (int timeBetweenSpawn)
    {
        timer.scheduleAtFixedRate(task,timeBetweenSpawn, timeBetweenSpawn);
    }
}
