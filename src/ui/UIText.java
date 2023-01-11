package ui;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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

    Font minecraftia;
    InputStream is = getClass().getClassLoader().getResourceAsStream("Minecraftia.ttf");


    public UIText(String text, GamePanel gamePanel) {
        super(gamePanel);

        this.text = text;
        this.fontSize = 20;
        this.fontStyle = Font.PLAIN;
        this.fontFamily = "Minecraftia";
        this.color = Color.RED;

        this.dropShadow = false;
        this.dropShadowOffset = 2;
        this.shadowColor = Color.black;

        try {
            minecraftia = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            getFont();
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
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
        font = minecraftia;

    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDropShadow(boolean dropShadow) {
        this.dropShadow = dropShadow;
    }

    public void setDropShadowOffset(int dropShadowOffset) {
        this.dropShadowOffset = dropShadowOffset;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void getFont () throws IOException, FontFormatException {

    }
}
