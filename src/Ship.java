/**
 * Created by igor on 13.09.15.
 */
public class Ship {
    int positionX;
    int positionY;
    boolean isVert;
    int maxHealth;
    int health;
    static int allHealth = 0;

    public Ship(int health) {
        this.health = health;
        this.maxHealth = health - 1;
        allHealth = allHealth + health;

    }

    void drawShip() {
        Field2d.cellss.get(this.positionX).get(this.positionY).cell = 'X';
        if (this.maxHealth > 0) {
            for (int i = 1; i < this.maxHealth + 1; i++) {

                if (this.isVert) {
                    Field2d.cellss.get(this.positionX+i).get(this.positionY).cell = 'X';
                } else {
                    Field2d.cellss.get(this.positionX).get(this.positionY+i).cell = 'X';
                }
            }
        }
    }

    void boooom() {
        Field2d.missAroundPoint(this.positionX, this.positionY);
        if (this.maxHealth > 0) {
            for (int i = 1; i < this.maxHealth + 1; i++) {

                if (this.isVert) {
                    Field2d.missAroundPoint(this.positionX + i, this.positionY);
                } else {
                    Field2d.missAroundPoint(this.positionX, this.positionY + i);
                }
            }
        }

    }
}
