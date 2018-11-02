package se.william;

public class MovingWall {

    private int x;
    private int y;
    private char symbol;
    private int previousX;
    private int previousY;

    public MovingWall(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.previousX = x;
        this.previousY = y;
    }

    public void moveLeft(){
        previousX = x;
        previousY = y;
        x--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    @Override
    public String toString() {
        return "MovingWall{" +
                "x=" + x +
                ", y=" + y +
                ", symbol=" + symbol +
                ", previousX=" + previousX +
                ", previousY=" + previousY +
                '}';
    }
}
