package scenes;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

import java.awt.*;

public class Settings extends GameScene implements SceneMethods {
    private MyButton bMenu;
    public Settings(Game game) {
        super(game);
        bMenu = new MyButton("Menu", 5, 5, 75, 35);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0,0, 640, 640);
        bMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (bMenu.getBounds().contains(x,y)) {
            bMenu.setMouseOver(true);
        }
        else {
            bMenu.setMouseOver(false);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x,y)) {
            bMenu.setMousePressed(true);
        }
        else {
            bMenu.setMousePressed(false);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }
}
