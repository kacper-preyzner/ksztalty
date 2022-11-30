package ui;

import main.GamePanel;

import java.awt.*;

public class UIRenderer {

    public void  renderUI (GamePanel gamePanel, Graphics graphics)
    {
        gamePanel.getUiContainers().forEach(uiContainer -> graphics.drawImage(
                uiContainer.getSprite(),
                uiContainer.getPosition().getX(),
                uiContainer.getPosition().getY(),
                null
        ));
    }
}
