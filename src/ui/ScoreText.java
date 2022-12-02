package ui;

import main.GamePanel;

public class ScoreText extends UIText{

    int score;
    private GamePanel gamePanel;

    public ScoreText(String text, GamePanel gamePanel) {
        super(text, gamePanel);
        this.gamePanel = gamePanel;
    }

    @Override
    public void update()
    {
        super.update();
        score = gamePanel.getScore();
        this.text = ("SCORE : " + score);
    }
}
