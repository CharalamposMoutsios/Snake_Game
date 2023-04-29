import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import apple.laf.JRSUIConstants.Direction;

public class Main extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private JPanel gameBoard;
    private Timer timer;
    private Snake snake;
    private Food food;
    
    public Main() {
        setTitle("Snake Game");
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameBoard = new JPanel();
        gameBoard.setLayout(null);
        gameBoard.setBackground(Color.black);
        add(gameBoard);
        
        snake = new Snake();
        food = new Food();
        
        timer = new Timer(100, this);
        timer.start();
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    snake.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    snake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(Direction.DOWN);
                    break;
                }
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) {
        snake.move();
        
        if (snake.checkCollision(food)) {
            snake.grow();
            food.generateFood();
        }
        
        if (snake.checkCollision()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over!");
            System.exit(0);
        }
        
        gameBoard.repaint();
    }
    
    public class Snake {
        
        private final int WIDTH = 10;
        private final int HEIGHT = 10;
        private final int INITIAL_SIZE = 5;
        private final int[] x = new int[INITIAL_SIZE];
        private final int[] y = new int[INITIAL_SIZE];
        private int size = INITIAL_SIZE;
        private Direction direction = Direction.RIGHT;
        
        public Snake() {
            for (int i = 0; i < INITIAL_SIZE; i++) {
                x[i] = (INITIAL_SIZE - i - 1) * WIDTH;
                y[i] = 0;
            }
        }
        
        public void setDirection(Direction direction) {
            this.direction = direction;
        }
        
        public void move() {
            for (int i = size - 1; i > 0; i--) {
                x[i] = x[i-1];
                y[i] = y[i-1];
            }
            
            switch (direction) {
            case LEFT:
                x[0] -= WIDTH;
                break;
            case RIGHT:
                x[0] += WIDTH;
                break;
            case UP:
                y[0] -= HEIGHT;
                break;
            case DOWN:
                y[0] += HEIGHT;
                break;
            }
        }
        
        public void grow() {
            size++;
            int[] tempX = new int[size];
            int[] tempY = new int[size];
            System.arraycopy(x, 0, tempX, 0, size - 1);
            System.arraycopy(y, 0, tempY, 0, size - 1);
            x[size - 1] = x[size - 2];
            y[size - 1] = y[size - 2];
            x = tempX;
            y = tempY;
        }
        
        public boolean checkCollision(Food food) {
            if (x[0] == food.getX() &&
public class SnakeGame {
    
}
