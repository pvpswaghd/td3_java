package scenes;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private Random random;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        loadSprites();
    }

    @Override
    public void render(Graphics g) {
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                g.drawImage(sprites.get(getRndInt()), i * 32, j * 32, null);
            }
        }
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

    private Color getRndColor() {
        int r = random.nextInt(256), g = random.nextInt(256), b = random.nextInt(256);
        return new Color(r, g, b);
    }

    private int getRndInt() {
        return random.nextInt(100);
    }
}
