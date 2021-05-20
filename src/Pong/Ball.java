package Pong;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 3;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);

        random = new Random();
        int randomXDirection = random.nextInt(2);
        int randomYDirection = random.nextInt(2);

        if (randomXDirection == 0) randomXDirection--;
        if (randomYDirection == 0) randomYDirection--;

        setXDirection(randomXDirection * initialSpeed);
        setYDirection(randomYDirection * initialSpeed);
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillOval(x, y, width, height);
    }
}
