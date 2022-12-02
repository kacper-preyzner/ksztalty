package ui;

public class Aligment {

    public enum Position
    {
        START, CENTER, END
    }

    private final Position horizontal;
    private final Position vertical;



    public Aligment(Position horizontal, Position vertical)
    {
        this.horizontal = horizontal;
        this.vertical = vertical;

    }

    public Position getHorizontal()
    {
        return horizontal;
    }

    public Position getVertical()
    {
        return vertical;
    }
}
