package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIContainer extends UIComponent{

    private Color backgroundColor;

    public UIContainer ()
    {
        super();
        backgroundColor = Color.GRAY;

        calcSize();
        calcPosition();
    }



    private void calcSize()
    {
        size = new Size(padding.getHorizontal(), padding.getVertical());
    }

    private void calcPosition ()
    {
        position = new Position(margin.getLeft(), margin.getTop());
    }

    @Override
    public Image getSprite()
    {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(backgroundColor);
        graphics.fillRect(0,0, size.getWidth(), size.getHeight());

        graphics.dispose();
        return image;
    }

    @Override
    public void update()
    {
        calcSize();
        calcPosition();
    }
}
