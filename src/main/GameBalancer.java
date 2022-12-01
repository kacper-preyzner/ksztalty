package main;

import entity.Enemy_Spawner;

public class GameBalancer {

    private int defaultTimeBtwSpawn = 1500;
    private int timeBtwSpawn;
    private GamePanel gamePanel;
    private Background background;

    public GameBalancer(Background background)
    {
        this.background = background;
    }

    public int getDefaultTimeBtwSpawn()
    {
        return defaultTimeBtwSpawn;
    }

    public int getTimeBtwSpawn()
    {
        return timeBtwSpawn;

    }

    public void balanceGame (int score, Enemy_Spawner enemy_spawner)
    {
        if (score < 10)
        {
            timeBtwSpawn = defaultTimeBtwSpawn;
        } else if (score == 10)
        {
            timeBtwSpawn = 1000;
            terminateTimer1(enemy_spawner);
            enemy_spawner.getGameTimer().Start2(timeBtwSpawn);
            background.nextBgState();
        } else if (score == 30)
        {
            timeBtwSpawn = 800;
            terminateTimer2(enemy_spawner);
            enemy_spawner.getGameTimer().Start3(timeBtwSpawn);
            background.nextBgState();
        } else if (score == 40)
        {
            timeBtwSpawn = 700;
            terminateTimer3(enemy_spawner);
            enemy_spawner.getGameTimer().Start4(timeBtwSpawn);
            background.nextBgState();
        }
        else if (score == 70)

        {
            timeBtwSpawn = 500;
            terminateTimer4(enemy_spawner);
            enemy_spawner.getGameTimer().Start5(timeBtwSpawn);
            background.nextBgState();
        }

        enemy_spawner.setTimeBetweenSpawn(timeBtwSpawn);
    }

    private void terminateTimer1 (Enemy_Spawner enemy_spawner)
    {
        enemy_spawner.getTimer1().cancel();
    }

    private void terminateTimer2 (Enemy_Spawner enemy_spawner)
    {
        enemy_spawner.getGameTimer().getTimer2().cancel();
    }

    private void terminateTimer3 (Enemy_Spawner enemy_spawner)
    {
        enemy_spawner.getGameTimer().getTimer3().cancel();
    }

    private void terminateTimer4 (Enemy_Spawner enemy_spawner)
    {
        enemy_spawner.getGameTimer().getTimer4().cancel();
    }
    private void terminateTimer5 (Enemy_Spawner enemy_spawner)
    {
        enemy_spawner.getGameTimer().getTimer5().cancel();
    }


}
