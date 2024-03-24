package scenes;

import java.awt.Graphics;
import java.awt.desktop.QuitEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private MyButton bPlaying, bSettings, bQuit;
    public Menu(Game game) {
        super(game);
        importImg();
        loadSprites();
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;
        bPlaying = new MyButton("Play", x, y, w, h);
        bSettings = new MyButton("Settings", x, y + yOffset, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
    }

    @Override
    public void render(Graphics g) {
      drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        System.out.println(bPlaying.getBounds().getX() + " " + x);
        if(bPlaying.getBounds().contains(x, y)) {
            SetGameState(PLAYING);
        }
        if(bSettings.getBounds().contains(x, y)) {
            SetGameState(SETTINGS);
        }
        if(bQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        }
        else {
            bPlaying.setMouseOver(false);
        }
        if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        }
        else {
            bSettings.setMouseOver(false);
        }
        if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
        else {
            bQuit.setMouseOver(false);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        }
        if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        }
        if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bPlaying.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void loadSprites() {
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sprites.add(img.getSubimage(i*32, j*32, 32, 32));
            }
        }
    }
}
