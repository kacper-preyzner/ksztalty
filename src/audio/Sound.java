package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {



    Clip clip;

    public Sound(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        fileName = fileName;

        final URL soundFile = Sound.class.getResource("/sounds/" + fileName);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile))
        {
            clip = AudioSystem.getClip();

            clip.setMicrosecondPosition(0);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        }
    }

    public void playSound()
    {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void stopSound()
    {
        clip.stop();

    }

}
