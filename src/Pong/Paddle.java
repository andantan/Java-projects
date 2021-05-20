package Pong;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int id;
    int yVelocity;
    int speed = 10;

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent event) {
        switch (id) {
            case 1 -> {
                if (event.getKeyCode() == KeyEvent.VK_W) setYDirection(-speed);
                if (event.getKeyCode() == KeyEvent.VK_S) setYDirection(speed);

                move();
            }
            case 2 -> {
                if (event.getKeyCode() == KeyEvent.VK_UP) setYDirection(-speed);
                if (event.getKeyCode() == KeyEvent.VK_DOWN) setYDirection(speed);

                move();
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        switch (id) {
            case 1 -> {
                if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) setYDirection(0);

                move();
            }
            case 2 -> {
                if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) setYDirection(0);

                move();
            }
        }
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y += yVelocity;
    }

    public void draw(Graphics graphics) {
        if (id == 1) graphics.setColor(Color.blue);
        else graphics.setColor(Color.red);

        graphics.fillRect(x, y, width, height);
    }
}