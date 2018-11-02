package se.william;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import se.william.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        try {
            startSimulation();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            System.out.println("Simulation over!");
        }

    }

    private static void startSimulation() throws IOException, InterruptedException {
        Terminal terminal = createTerminal();

        simulationLoop(terminal);
    }

    private static Terminal createTerminal() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);
        return terminal;
    }

    private static void simulationLoop(Terminal terminal) throws InterruptedException, IOException {


        Player player = new Player(10, 10, '\u263a');
        List<Enemy> enemies = createEnemy();
        List<Wall> walls = createOuterFrame();
        List<MovingWall> movingWalls = createMovingWall();
        final int timeCounterThreshold = 80;
        int timeCounter = 0;

        while(true){
            KeyStroke keyStroke;
            do {
                // everything inside this loop will be called approximately every ~5 millisec.
                Thread.sleep(5);
                keyStroke = terminal.pollInput();

                timeCounter++;
                if (timeCounter >= timeCounterThreshold){
                    timeCounter = 0;

                    printPlayer(terminal, player);
                    printWall(terminal, walls);
                    printMovingWall(terminal, movingWalls);
                    for (MovingWall movingWall : movingWalls) {
                        movingWall.moveLeft();
                    }


                    terminal.flush(); // don't forget to flush to see any updates!
                }


            } while (keyStroke == null);

            movePlayer(player, keyStroke);
            printPlayer(terminal, player);

            terminal.flush(); // don't forget to flush to see any updates!
        }
    }

    private static void printPlayer(Terminal terminal, Player player) throws IOException {
        terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
        terminal.putCharacter(' ');

        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());
    }

    private static void printWall(Terminal terminal, List<Wall> walls) throws IOException {

        for (Wall wall : walls) {
            terminal.setCursorPosition(wall.getPreviousX(), wall.getPreviousY());
            terminal.putCharacter(' ');

            terminal.setCursorPosition(wall.getX(), wall.getY());
            terminal.putCharacter(wall.getSymbol());
        }
    }

    private static void printMovingWall(Terminal terminal, List<MovingWall> movingWalls) throws IOException {

        for (MovingWall movingWall : movingWalls) {
            terminal.setCursorPosition(movingWall.getPreviousX(), movingWall.getPreviousY());
            terminal.putCharacter(' ');

            terminal.setCursorPosition(movingWall.getX(), movingWall.getY());
            terminal.putCharacter(movingWall.getSymbol());
        }
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

    private static List<Wall> createOuterFrame() {
        List<Wall> walls = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            walls.add(new Wall(80, i, 'X'));
            walls.add(new Wall(0, i, 'X'));
        }

        for (int i = 0; i < 80; i++) {
            walls.add(new Wall(i, 0, 'X'));
            walls.add(new Wall(i, 24, 'X'));
        }
        return walls;
    }

    private static List<MovingWall> createMovingWall() {
        List<MovingWall> walls = new ArrayList<>();
        for (int i = 1; i < 23; i++) {
            walls.add(new MovingWall(70, i, '<'));
        }
        return walls;
    }

    private static List<Enemy> createEnemy() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(40, 10, 'Q'));
        return enemies;
    }
}

