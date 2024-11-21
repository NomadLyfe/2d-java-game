import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        solidArea = new Rectangle((int)(gp.tileSize*0.1875), (int)(gp.tileSize*0.625), (int)(gp.tileSize*0.625), (int)(gp.tileSize*0.375));
        setDefaultvalues();
        getPlayerImage();
    }

    public void setDefaultvalues() {
        worldX = gp.tileSize * 17;
        worldY = gp.tileSize * 19;
        speed = gp.worldWidth/800;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/pics/Up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/pics/Up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/pics/Down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/pics/Down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/pics/Left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/pics/Left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/pics/Right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/pics/Right2.png"));
            hdleft1 = ImageIO.read(getClass().getResourceAsStream("/pics/HDleft1.png"));
            hdleft2 = ImageIO.read(getClass().getResourceAsStream("/pics/HDleft2.png"));
            hdleft3 = ImageIO.read(getClass().getResourceAsStream("/pics/HDleft3.png"));
            hdleft4 = ImageIO.read(getClass().getResourceAsStream("/pics/HDleft4.png"));
            hdright1 = ImageIO.read(getClass().getResourceAsStream("/pics/HDright1.png"));
            hdright2 = ImageIO.read(getClass().getResourceAsStream("/pics/HDright2.png"));
            hdright3 = ImageIO.read(getClass().getResourceAsStream("/pics/HDright3.png"));
            hdright4 = ImageIO.read(getClass().getResourceAsStream("/pics/HDright4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            }else if (keyH.rightPressed == true) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            playerCounter ++;
            if (playerCounter > 10) {
                if (playerNum == 1) {
                    playerNum = 2;
                } else if (playerNum == 2) {
                    playerNum = 3;
                } else if (playerNum == 3) {
                    playerNum = 4;
                } else {
                    playerNum = 1;
                }
                playerCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction) {
            case "up":
                if (playerNum == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
            if (playerNum == 1) {
                image = down1;
            } else {
                image = down2;
            }
                break;
                case "left":
                if (playerNum == 1) {
                    image = hdleft1;
                } else if (playerNum == 2) {
                    image = hdleft2;
                } else if (playerNum == 3) {
                    image = hdleft3;
                } else {
                    image = hdleft4;
                }
                    break;
                case "right":
                if (playerNum == 1) {
                    image = hdright1;
                } else if (playerNum == 2) {
                    image = hdright2;
                } else if (playerNum == 3) {
                    image = hdright3;
                } else {
                    image = hdright4;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
