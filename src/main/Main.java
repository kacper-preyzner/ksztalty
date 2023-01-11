package main;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Main
{

    private static JFrame window;

    public static void exit()
    {
        System.exit(0);
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("CHUJ");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.setIconImage(gamePanel.getLogo());


        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        FPS.calcBeginTime();
        gamePanel.startGameThread();



    }
}
