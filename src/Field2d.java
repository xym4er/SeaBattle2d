import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChornyiUA on 15.09.2015.
 */
public class Field2d {
    public static List<List<Cell>> cellss = new ArrayList<>();
    Map<Integer, Ship> ships = new HashMap<>();

    void init() {
        for (int i = 0; i < 10; i++) {
            cellss.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                cellss.get(i).add(j, new Cell());
            }
        }
        int n = 1;
        for (int i = 4; i > 0; i--) {
            for (int j = i; j < 5; j++) {
                ships.put(n, new Ship(i));
                setShip(ships.get(n));
                n++;
            }
        }
    }

    void showField() {
        Window.jTextArea.setText("   0123456789           0123456789\n");
        for (int i = 0; i < 10; i++) {
            Window.jTextArea.append(i + "");
            for (int j = 0; j < 10; j++) {
                if (cellss.get(i).get(j).cell == 'X') Window.jTextArea.append(". ");
                else Window.jTextArea.append(cellss.get(i).get(j).cell+" ");
            }
            Window.jTextArea.append("          " + i);
            for (int j = 0; j < 10; j++) {
                Window.jTextArea.append(cellss.get(i).get(j).cell+" ");
            }
            Window.jTextArea.append("\n");
        }
    }

    void doShoot(int y, int x) {
        Window.jTextArea.append("ваш выстрел: " + y + " - " + x+"\n");
        switch (cellss.get(x).get(y).cell) {
            case '.':
                Window.jTextArea.append("Missed\n");
                cellss.get(x).get(y).cell = '@';
                break;
            case '@':
                Window.jTextArea.append("Уже стреляли\n");
                break;
            case 'X':
                if (cellss.get(x).get(y).ship.health == 0) {
                    Window.jTextArea.append("Убил!\n");
                    cellss.get(x).get(y).cell = '#';
                    cellss.get(x).get(y).ship.boooom();
                    Ship.allHealth--;
                } else {
                    System.out.println("Попал!");
                    cellss.get(x).get(y).cell = '#';
                    cellss.get(x).get(y).ship.health--;
                    Ship.allHealth--;
                }
                break;
            default:
                Window.jTextArea.append("ERROR\n");
        }
    }

    void setShip(Ship ship) {
        do {
            ship.positionX = (int) (Math.random() * 10);
            ship.positionY = (int) (Math.random() * 10);
            ship.isVert = Math.random() * 2 <= 1;
        } while (checkShip(ship));
        ship.drawShip();
    }

    boolean checkShip(Ship ship) {
        if (ship.maxHealth > 0) {
            if (ship.isVert) {
                return ((ship.positionX + ship.maxHealth > 9) || checkAroundPoint(ship.positionX, ship.positionY) || checkAroundPoint(ship.positionX + ship.maxHealth, ship.positionY));
            } else
                return ((ship.positionY + ship.maxHealth > 9) || checkAroundPoint(ship.positionX, ship.positionY) || checkAroundPoint(ship.positionX, ship.positionY + ship.maxHealth));
        } else {
            return (checkAroundPoint(ship.positionX, ship.positionY));
        }
    }

    static void missAroundPoint(int x, int y) {

        if (x > 0 && y > 0) {
            if (cellss.get(x - 1).get(y - 1).cell == '.') cellss.get(x - 1).get(y - 1).cell = '@';
        }
        if (x > 0) {
            if (cellss.get(x - 1).get(y).cell == '.') cellss.get(x - 1).get(y).cell = '@';
        }
        if (x > 0 && y < 9) {
            if (cellss.get(x - 1).get(y + 1).cell == '.') cellss.get(x - 1).get(y + 1).cell = '@';
        }
        if (y > 0) {
            if (cellss.get(x).get(y - 1).cell == '.') cellss.get(x).get(y - 1).cell = '@';
        }
        if (y < 9) {
            if (cellss.get(x).get(y + 1).cell == '.') cellss.get(x).get(y + 1).cell = '@';
        }
        if (x < 9 && y > 0) {
            if (cellss.get(x + 1).get(y - 1).cell == '.') cellss.get(x + 1).get(y - 1).cell = '@';
        }
        if (x < 9) {
            if (cellss.get(x + 1).get(y).cell == '.') cellss.get(x + 1).get(y).cell = '@';
        }
        if (x < 9 && y < 9) {
            if (cellss.get(x + 1).get(y + 1).cell == '.') cellss.get(x + 1).get(y + 1).cell = '@';
        }
    }

    boolean checkAroundPoint(int x, int y) {
        if (cellss.get(x).get(y).cell == 'X') return true;
        if (x > 0 && y > 0) {
            if (cellss.get(x - 1).get(y - 1).cell == 'X') return true;
        }
        if (x > 0) {
            if (cellss.get(x - 1).get(y).cell == 'X') return true;
        }
        if (x > 0 && y < 9) {
            if (cellss.get(x - 1).get(y + 1).cell == 'X') return true;
        }
        if (y > 0) {
            if (cellss.get(x).get(y - 1).cell == 'X') return true;
        }
        if (y < 9) {
            if (cellss.get(x).get(y + 1).cell == 'X') return true;
        }
        if (x < 9 && y > 0) {
            if (cellss.get(x + 1).get(y - 1).cell == 'X') return true;
        }
        if (x < 9) {
            if (cellss.get(x + 1).get(y).cell == 'X') return true;
        }
        if (x < 9 && y < 9) {
            if (cellss.get(x + 1).get(y + 1).cell == 'X') return true;
        }
        return false;
    }


    boolean isNotGameOver() {
        return (Ship.allHealth >= 1);
    }
}