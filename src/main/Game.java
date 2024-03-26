package main;

import javax.swing.*;
import java.awt.image.BufferedImage;

import helpers.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private BufferedImage img;
    private Thread gameThread;
    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private TileManager tileManager;
    public Game () {
        initClasses();
        createDefaultLevel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Set position in centre
        setResizable(false);
        add(gameScreen);
        pack();
        setVisible(true);
    }
    private void createDefaultLevel() {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 0;
        LoadSave.CreateLevel("new_level", arr);
    }

    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        editing = new Editing(this);
    }

    private void updateGame() {

    }

    private void start() {
        gameThread = new Thread(this) {};
        gameThread.start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        long lastUpdate = System.nanoTime();
        int frames = 0;
        int updates = 0;

        long now;
        while (true) {
            now = System.nanoTime();
            // Render
            if(now - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                repaint();
                frames++;
            }
            // Update
            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = System.nanoTime();
                updates++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    // Getters and Setters

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    public Render getRender() {
        return render;
    }
    public Editing getEditor() { return editing;}
    public TileManager getTileManager() {
        return tileManager;
    }
}
