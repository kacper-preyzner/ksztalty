package audio;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AudioManager {

    private ArrayList  <Sound> deathSounds = new ArrayList<>();
    private ArrayList  <Sound> moveSounds = new ArrayList<>();
    private Sound mainTheme;

    Random random = new Random();

    public AudioManager() throws UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        System.out.println("Loading audio started");

        deathSounds.add(new Sound("death_1.wav"));
        deathSounds.add(new Sound("death_2.wav"));
        deathSounds.add(new Sound("death_3.wav"));

        moveSounds.add(new Sound("walk_1.wav"));
        moveSounds.add(new Sound("walk_2.wav"));
        moveSounds.add(new Sound("walk_3.wav"));

        mainTheme = new Sound("main_theme.wav");

        System.out.println("Loading audio ended");
    }

    public void playDeathSound()
    {
        int n = random.nextInt(deathSounds.size());
        deathSounds.get(n).playSound();

    }

    public void playMoveSound()
    {
        int n = random.nextInt(3);
        moveSounds.get(n).playSound();

    }

    public void playMainTheme()
    {
        mainTheme.playSound();
    }

    public void stopMainTheme()
    {
        mainTheme.stopSound();
    }



}
