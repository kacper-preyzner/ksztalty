package main;

import entity.Enemy_Spawner;

public class GameBalancer {

    private final int defaultTimeBtwSpawn = 1500;
    private int timeBtwSpawn;
    private GamePanel gamePanel;
    private final Background background;
    private Enemy_Spawner enemy_spawner;

    public GameBalancer(Background background, Enemy_Spawner enemy_spawner)
    {
        this.background = background;
        this.enemy_spawner = enemy_spawner;
    }

    public int getDefaultTimeBtwSpawn()
    {
        return defaultTimeBtwSpawn;
    }

    public int getTimeBtwSpawn()
    {
        return timeBtwSpawn;

    }

    public void balanceGame (int score)
    {
        if (score < 10)
        {
            timeBtwSpawn = defaultTimeBtwSpawn;
            background.setBgState(1);
        } else if (score == 10)
        {
            timeBtwSpawn = 1000;
            background.setBgState(2);
        } else if (score == 30)
        {
            timeBtwSpawn = 800;
            background.setBgState(3);
        } else if (score == 40)
        {
            timeBtwSpawn = 700;
            background.setBgState(4);
        }
        else if (score == 70)

        {
            timeBtwSpawn = 500;
            background.setBgState(5);
        }
        else if (score == 90)

        {
            timeBtwSpawn = 500;
            background.setBgState(6);
        }
        else if (score == 110)

        {
            timeBtwSpawn = 500;
            background.setBgState(7);
        }
        else if (score == 130)

        {
            timeBtwSpawn = 500;
            background.setBgState(8);
        }

        enemy_spawner.setTimeBetweenSpawn(timeBtwSpawn);
    }

}
