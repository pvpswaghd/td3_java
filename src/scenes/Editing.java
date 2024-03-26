package scenes;

import main.Game;
import objects.Tile;
import helpers.LoadSave;
import ui.ActionBar;
import ui.Toolbar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Editing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY;
    private boolean drawSelect = false;
    private Toolbar toolBar;
    public Editing(Game game) {
        super(game);
        loadDefaultLevel();
        toolBar = new Toolbar(0, 640, 640, 100, this);
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
    }
    @Override
    public void render(Graphics g) {
        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
    }
    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x*32, y*32, null);
            }
        }
    }
    private BufferedImage getSprite(int spriteID) {
        return game.getTileManager().getSprite(spriteID);
    }
    public void saveLevel() {
        LoadSave.SaveLevel("new_level", lvl);
        game.getPlaying().setLevel(lvl);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }
    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }
    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;
            if (lastTileX == tileX && lastTileY == tileY)
                return;
            lastTileX = tileX;
            lastTileY = tileY;
            lvl[tileY][tileX] = selectedTile.getId();
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            toolBar.mouseClicked(x, y);
        }
        else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            toolBar.mouseMoved(x, y);
            drawSelect = false;
        }
        else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
            drawSelect = true;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640) {

        }
        else {
            changeTile(x, y);
        }

    }
}
