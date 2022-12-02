package ui;

import main.GamePanel;

import java.awt.*;

public class GameOverScreen extends UIText
{
    public GameOverScreen(String text, GamePanel gamePanel)
    {
        super(text, gamePanel);
        this.setFontSize(30);
    }

    @Override
    public void update()
    {

        super.update();
        if (gamePanel.getGameState() != 3)
        {
            this.setColor(new Color(1,1,1,0));
        }
        else
        {
            this.setColor(Color.RED);
            System.out.println("WIDZISZ MIE?");
        }
    }
}
