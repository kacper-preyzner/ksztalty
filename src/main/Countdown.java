package main;

import java.util.Timer;
import java.util.TimerTask;

public class Countdown {


    private Timer timer = new Timer();
    TimerTask task = new TimerTask()
    {
        @Override
        public void run()
        {
            System.out.println("Spawning enemy");
        }
    };


    public void Start (int timeBetweenSpawn)
    {
        timer.scheduleAtFixedRate(task,timeBetweenSpawn, timeBetweenSpawn);
    }
}
