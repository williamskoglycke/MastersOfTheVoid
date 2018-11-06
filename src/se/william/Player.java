package se.william;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Player {
    private int x;
    private int y;
    private char symbol;
    private int previousX;
    private int previousY;

    public Player(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.previousX = x;
        this.previousY = y;
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

    public void moveUp(){

        previousX = x;
        previousY = y;
        if (previousY < 3){
            //Sätt y = 2 om du ej vill ha "studs"
            y = 2;
        } else {
            y--;
        }

    }

    public void moveDown(){
        previousX = x;
        previousY = y;
        y++;
    }

    public void moveLeft(){
        previousX = x;
        previousY = y;
        x--;
    }

    public void moveRight(){
        previousX = x;
        previousY = y;
        x++;
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + x +
                ", y=" + y +
                ", symbol=" + symbol +
                ", previousX=" + previousX +
                ", previousY=" + previousY +
                '}';
    }

    private static void movePlayer(Player player, KeyStroke keyStroke) {
        switch (keyStroke.getKeyType()) {
            case ArrowUp:
                player.moveUp();
                break;
            case ArrowDown:
                player.moveDown();
                break;
            case ArrowLeft:
                player.moveLeft();
                break;
            case ArrowRight:
                player.moveRight();
                break;
        }
    }

    private static KeyStroke getUserKeyStroke(Terminal terminal) throws InterruptedException, IOException {
        KeyStroke keyStroke;
        do {
            Thread.sleep(5);
            keyStroke = terminal.pollInput();
        } while (keyStroke == null);
        return keyStroke;
    }

}

