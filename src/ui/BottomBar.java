package ui;

import objects.Tile;
import scenes.Playing;

import java.awt.*;
import java.util.ArrayList;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class BottomBar {
    private int x;
    private int y;
    private int width;
    private int height;
    private MyButton bMenu;
    private Playing playing;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;
        initButtons();
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        for(Tile tile : playing.getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getName(), 110, 650, 50, 50));

        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        for(MyButton b : tileButtons) {
            b.draw(g);
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);
        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        if (bMenu.getBounds().contains(x,y)) {
            bMenu.setMouseOver(true);
        }
        else {
            bMenu.setMouseOver(false);
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x,y)) {
            bMenu.setMousePressed(true);
        }
        else {
            bMenu.setMousePressed(false);
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }
}
