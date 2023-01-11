package ui;

import main.GamePanel;

public class VerticalContainer extends UIContainer {


    public VerticalContainer(GamePanel gamePanel)
    {
        super(gamePanel);
    }

    @Override
    protected Size calcContentSize()
    {

        int combinedChildHeight = 0;
        int widestChildWidth = 0;

        for (UIComponent uiComponent : children)
        {
            combinedChildHeight += uiComponent.getSize().getWidth() + uiComponent.getMargin().getVertical();

            if (uiComponent.getSize().getWidth() > widestChildWidth)
            {
                widestChildWidth = uiComponent.getSize().getWidth();
            }
        }
        return new Size(widestChildWidth, combinedChildHeight);

    }

    @Override
    protected void calcContentPosition()
    {
        int currentY = padding.getTop();

        for (UIComponent uiComponent : children)
        {
            currentY += uiComponent.getMargin().getTop();
            uiComponent.setPosition(new Position(padding.getLeft(), currentY));
            currentY += uiComponent.getSize().getHeight();
            currentY += uiComponent.getMargin().getBottom();
        }


    }
}
