package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponent{

    protected Color backgroundColor;

    protected ArrayList<UIComponent> children;


    public UIContainer ()
    {
        super();
        backgroundColor = Color.RED;

        if (children != null)
        {
            calcSize();
            calcPosition();
        }
        margin = new Spacing(5);



        children = new ArrayList<UIComponent>();
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
        position = new Position(margin.getLeft(), margin.getTop());
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
}
