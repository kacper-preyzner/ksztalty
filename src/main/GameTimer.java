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

    private final Timer timer1 = new Timer();
    TimerTask task1 = new TimerTask()
    {
        @Override
        public void run()
        {
            //System.out.println("Spawning enemy");
            enemy_spawner.spawnEnemy();
            enemy_spawner.moveEnemies();
        }
    };

    private final Timer timer2 = new Timer();
    TimerTask task2 = new TimerTask()
    {
        @Override
        public void run()
        {
            //System.out.println("Spawning enemy");
            enemy_spawner.spawnEnemy();
            enemy_spawner.moveEnemies();
        }
    };

    private final Timer timer3 = new Timer();
    TimerTask task3 = new TimerTask()
    {
        @Override
        public void run()
        {
            //System.out.println("Spawning enemy");
            enemy_spawner.spawnEnemy();
            enemy_spawner.moveEnemies();
        }
    };

    private final Timer timer4 = new Timer();
    TimerTask task4 = new TimerTask()
    {
        @Override
        public void run()
        {
            //System.out.println("Spawning enemy");
            enemy_spawner.spawnEnemy();
            enemy_spawner.moveEnemies();
        }
    };
    private final Timer timer5 = new Timer();
    TimerTask task5 = new TimerTask()
    {
        @Override
        public void run()
        {
            //System.out.println("Spawning enemy");
            enemy_spawner.spawnEnemy();
            enemy_spawner.moveEnemies();
        }
    };


    public void Start1 (int timeBetweenSpawn)
    {
        timer1.scheduleAtFixedRate(task1,timeBetweenSpawn, timeBetweenSpawn);

    }

    public Timer getTimer1()
    {
        return timer1;
    }

    public void Start2 (int timeBetweenSpawn)
    {
        timer2.scheduleAtFixedRate(task2,timeBetweenSpawn, timeBetweenSpawn);

    }

    public Timer getTimer2()
    {
        return timer2;
    }

    public void Start3 (int timeBetweenSpawn)
    {
        timer3.scheduleAtFixedRate(task3,timeBetweenSpawn, timeBetweenSpawn);

    }

    public Timer getTimer3()
    {
        return timer3;
    }

    public void Start4 (int timeBetweenSpawn)
    {
        timer4.scheduleAtFixedRate(task4,timeBetweenSpawn, timeBetweenSpawn);

    }

    public Timer getTimer4()
    {
        return timer4;
    }

    public void Start5 (int timeBetweenSpawn)
    {
        timer5.scheduleAtFixedRate(task5,timeBetweenSpawn, timeBetweenSpawn);

    }

    public Timer getTimer5()
    {
        return timer5;
    }
}

