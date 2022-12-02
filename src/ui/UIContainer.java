package ui;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponent{

    protected Color backgroundColor;

    protected Aligment aligment;
    protected Size windowSize;

    protected ArrayList<UIComponent> children;


    public UIContainer (GamePanel gamePanel)
    {
        super(gamePanel);
        this.aligment = new Aligment(Aligment.Position.START, Aligment.Position.START);
        windowSize = new Size(gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        backgroundColor = Color.RED;

        children = new ArrayList<UIComponent>();

        calcSize();
        calcPosition();
    }

    protected abstract Size calcContentSize();
    protected abstract void calcContentPosition();

    private void calcSize()
    {
        Size calculatedContentSize = calcContentSize();
        size = new Size(padding.getHorizontal() + calculatedContentSize.getWidth(),
                padding.getVertical() + calculatedContentSize.getHeight());
    }

    private void calcPosition ()
    {
        int x = padding.getLeft();
        if (aligment.getHorizontal().equals(Aligment.Position.CENTER))
        {
            x = windowSize.getWidth() / 2 - size.getWidth() / 2;
        }
        if (aligment.getHorizontal() == Aligment.Position.END)
        {
            x = windowSize.getWidth() - size.getWidth() - margin.getRight();
        }

        int y = padding.getTop();
        if (aligment.getVertical().equals(Aligment.Position.CENTER))
        {
            y = windowSize.getHeight() / 2 - size.getHeight() / 2;
        }
        if (aligment.getVertical() == Aligment.Position.END)
        {
            y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
        }

        this.position = new Position(x,y);

        calcContentPosition();
    }

    @Override
    public Image getSprite()
    {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(backgroundColor);
        graphics.fillRect(0,0, size.getWidth(), size.getHeight());

        children.forEach(uiComponent -> graphics.drawImage(
                uiComponent.getSprite(),
                uiComponent.getPosition().getX(),
                uiComponent.getPosition().getY(),
                null
                ));

        graphics.dispose();
        return image;
    }

    @Override
    public void update()
    {
        children.forEach(uiComponent -> uiComponent.update());
        calcSize();
        calcPosition();

    }

    public void addUIComponent (UIComponent uiComponent)
    {
        children.add(uiComponent);
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public void setAligment(Aligment aligment)
    {
        this.aligment = aligment;
    }

    public ArrayList<UIComponent> getChildren()
    {
        return children;
    }
}
