import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame implements ActionListener, KeyListener {
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int UNIT_SIZE = 20;
    public static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    public static final int DELAY = 75;
    public static final int[] x = new int[GAME_UNITS];
    public static final int[] y = new int[GAME_UNITS];
    public static int bodyParts = 6;
    public static int applesEaten = 0;
    public static int appleX;
    public static int appleY;
    public static char direction = 'R';
    public static boolean running = false;
    public static Timer timer;
    public static Random random;
    public static JFrame frame;
    public static JPanel panel;
    
    public SnakeGame() {
        frame = new JFrame("Snake Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setBackground(Color.black);
        panel.setFocusable(true);
        panel.addKeyListener(this);
        frame.add(panel);
        frame.setVisible(true);
        
        startGame();
    }
    
    public static void main(String[] args) {
        new SnakeGame();
    }
    
    public void startGame() {
        random = new Random();
        running = true;
        direction = 'R';
        generateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, WIDTH, i * UNIT_SIZE);
            }
            
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            
            g.setColor(Color.red);
            g.drawString("Score: " + applesEaten, 10, 20);
        } else {
            gameOver(g);
        }
    }
    
    public void generateApple() {
        appleX = random.nextInt((int)(WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }
    
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i]
                    
