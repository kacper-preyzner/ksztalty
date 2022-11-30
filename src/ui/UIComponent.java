package ui;


import java.awt.*;

public abstract class UIComponent {

    protected Position position;
    protected Size size;
    protected Spacing margin;
    protected Spacing padding;

    public UIComponent()
    {
        this.position = new Position(0,0);
        this.size = new Size(1,1);
        this.margin = new Spacing(0);
        this.padding = new Spacing(5);
    }

    public abstract void update ();

    public abstract Image getSprite();


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Spacing getMargin() {
        return margin;
    }

    public void setMargin(Spacing margin) {
        this.margin = margin;
    }

    public Spacing getPadding() {
        return padding;
    }

    public void setPadding(Spacing padding) {
        this.padding = padding;
    }
}
