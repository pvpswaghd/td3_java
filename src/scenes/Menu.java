package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private MyButton bPlaying, bSettings, bQuit, bEdit;
    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;
        bPlaying = new MyButton("Play", x, y, w, h);
        bEdit = new MyButton("Edit", x, y + yOffset, w, h);
        bSettings = new MyButton("Settings", x, y + yOffset * 2, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);
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
        if(bEdit.getBounds().contains(x, y)) {
            SetGameState(EDIT);
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
        if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMouseOver(true);
        }
        else {
            bEdit.setMouseOver(false);
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
        if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMousePressed(true);
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
        bEdit.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

}
