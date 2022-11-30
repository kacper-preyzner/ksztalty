package main;

import entity.Enemy;
import entity.Enemy_Spawner;

import java.util.Timer;
import java.util.TimerTask;



public class GameTimer {

    private Enemy enemy;


    private final Enemy_Spawner enemy_spawner;
    public GameTimer(Enemy_Spawner enemy_spawner)
    {
        this.enemy_spawner = enemy_spawner;
    }

    private final Timer timer = new Timer();
    TimerTask task = new TimerTask()
    {
        @Override
        public void run()
        {
            //System.out.println("Spawning enemy");
            enemy_spawner.spawnEnemy();
            enemy_spawner.moveEnemies();
        }
    };


    public void Start (int timeBetweenSpawn)
    {
        timer.scheduleAtFixedRate(task,timeBetweenSpawn, timeBetweenSpawn);
    }



}
