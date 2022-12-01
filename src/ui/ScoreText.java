package ui;

import main.GamePanel;

public class ScoreText extends UIText{

    int score;

    public ScoreText(String text) {
        super(text);
    }

    @Override
    public void update()
    {
        super.update();
        score = GamePanel.score;
        this.text = ("SCORE : " + score);
    }
}
