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
            timeBtwSpawn = 600;
            background.setBgState(5);
        }
        else if (score == 90)

        {
            timeBtwSpawn = 600;
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
        else if (score == 160)

        {
            timeBtwSpawn = 500;
            background.setBgState(9);
        }
        else if (score == 180)

        {
            timeBtwSpawn = 500;
            background.setBgState(10);
        }
        else if (score == 200)

        {
            timeBtwSpawn = 400;
            background.setBgState(11);
        }
        else if (score == 220)

        {
            timeBtwSpawn = 400;
            background.setBgState(12);
        }
        else if (score == 240)

        {
            timeBtwSpawn = 400;
            background.setBgState(13);
        }
        else if (score == 260)

        {
            timeBtwSpawn = 400;
            background.setBgState(14);
        }
        else if (score == 269)

        {
            timeBtwSpawn = 350;
            background.setBgState(15);
        }
        else if (score == 280)

        {
            timeBtwSpawn = 350;
            background.setBgState(16);
        }
        else if (score == 300)

        {
            timeBtwSpawn = 300;
            background.setBgState(17);
        }

        enemy_spawner.setTimeBetweenSpawn(timeBtwSpawn);
    }

}
