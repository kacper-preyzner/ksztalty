package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIText extends UIComponent{

    protected String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color color;

    private boolean dropShadow;
    private int dropShadowOffset;
    private Color shadowColor;

    private Font font;

    public UIText(String text) {

        this.text = text;
        this.fontSize = 20;
        this.fontStyle = Font.PLAIN;
        this.fontFamily = "Minecraftia";
        this.color = Color.RED;

        this.dropShadow = false;
        this.dropShadowOffset = 2;
        this.shadowColor = Color.black;
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(font);

        if(dropShadow) {
            graphics.setColor(shadowColor);
            graphics.drawString(text, padding.getLeft() + dropShadowOffset, fontSize + 15 + padding.getTop() + dropShadowOffset);
        }

        graphics.setColor(color);
        graphics.drawString(text, padding.getLeft(), fontSize + 15 + padding.getTop());

        graphics.dispose();
        return image;
    }

    @Override
    public void update() {
        createFont();
        calculateSize();
    }

    private void calculateSize() {
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        size = new Size(
                fontMetrics.stringWidth(text) + padding.getHorizontal(),
                fontMetrics.getHeight() + padding.getVertical()
        );
    }

    private void createFont() {
        font = new Font(fontFamily, fontStyle, fontSize);
    }
}
