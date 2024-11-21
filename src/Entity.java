import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public double worldX, worldY;
    public double speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, hdleft2, hdleft3, hdright2, hdright3, hdleft1, hdright1, hdleft4, hdright4;
    public String direction;
    public int playerCounter = 0;
    public int playerNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
