package ui;

import main.GamePanel;

import java.awt.*;

public class StartScreen extends UIText
{
    public StartScreen(String text, GamePanel gamePanel)
    {
        super(text, gamePanel);
        this.setFontSize(30);
    }

    @Override
    public void update()
    {

        super.update();
        if (gamePanel.getGameState() != 1)
        {
            this.setColor(new Color(1,1,1,0));
            this.setDropShadow(false);
        }
        else
        {
            this.setColor(Color.WHITE);
            this.setDropShadow(true);
            System.out.println("WIDZISZ MIE?");
        }
    }
}
