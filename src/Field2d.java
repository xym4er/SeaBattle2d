import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChornyiUA on 15.09.2015.
 */
public class Field2d {
//    public static char[][] cells = new char[10][10];
    public static List<List<Cell>> cellss = new ArrayList<>();
    Map<Integer, Ship> ships = new HashMap<>();



/*    Ship ship6 = new Ship(4);
    Ship ship1 = new Ship(3);
    Ship ship2 = new Ship(3);
    Ship ship3 = new Ship(2);
    Ship ship4 = new Ship(2);
    Ship ship5 = new Ship(2);
    Ship ship7 = new Ship(1);
    Ship ship8 = new Ship(1);
    Ship ship9 = new Ship(1);
    Ship ship10 = new Ship(1);*/

    void init() {
        for (int i = 0; i <10 ; i++) {
            cellss.add(new ArrayList<>());
            for (int j = 0; j <10 ; j++) {
                cellss.get(i).add(j,new Cell());
            }
        }

/*        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = '.';
            }
        }*/
        int n=1;
        for (int i = 4; i >0 ; i--) {
            for (int j = i; j <5 ; j++) {
                ships.put(n, new Ship(i));
                setShip(ships.get(n));
                n++;
            }
        }

/*        setShip(ship6);
        setShip(ship2);
        setShip(ship3);
        setShip(ship4);
        setShip(ship5);
        setShip(ship1);
        setShip(ship7);
        setShip(ship8);
        setShip(ship9);
        setShip(ship10);*/
    }

    void showField() {
        System.out.println(" 0123456789           0123456789");
        for (int i = 0; i <10 ; i++) {
            System.out.print(i);
            for (int j = 0; j < 10; j++) {
                if (cellss.get(i).get(j).cell == 'X') System.out.print('.');
                else System.out.print(cellss.get(i).get(j).cell);
            }
            System.out.print("          " + i);
            for (int j = 0; j < 10; j++) {
                System.out.print(cellss.get(i).get(j).cell);
            }
            System.out.println();
        }


/*        System.out.println(" 0123456789           0123456789");
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            for (int j = 0; j < 10; j++) {
                if (cells[i][j] == 'X') System.out.print('.');
                else System.out.print(cells[i][j]);
            }
            System.out.print("          " + i);
            for (int j = 0; j < 10; j++) {
                System.out.print(cells[i][j]);
            }
            System.out.println();
        }*/
    }

    void doShoot(int y, int x) {
        System.out.println("ваш выстрел: " + y + " - " + x);
        switch (cellss.get(x).get(y).cell) {
            case '.':
                System.out.println("Missed");
                cellss.get(x).get(y).cell = '@';
                break;
            case '@':
                System.out.println("Уже стреляли");
                break;
            case 'X':
                System.out.println("Попал!");
                cellss.get(x).get(y).cell = '#';
                missAroundPoint(x,y);
                Ship.allHealth--;
                break;
            default:
                System.out.println("ERROR");
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
            if (cellss.get(x-1).get(y-1).cell == '.') cellss.get(x-1).get(y-1).cell = '@';
        }
        if (x > 0) {
            if (cellss.get(x-1).get(y).cell == '.') cellss.get(x-1).get(y).cell = '@';
        }
        if (x > 0 && y < 9) {
            if (cellss.get(x-1).get(y+1).cell == '.') cellss.get(x-1).get(y+1).cell = '@';
        }
        if (y > 0) {
            if (cellss.get(x).get(y-1).cell == '.') cellss.get(x).get(y-1).cell = '@';
        }
        if (y < 9) {
            if (cellss.get(x).get(y+1).cell == '.') cellss.get(x).get(y+1).cell = '@';
        }
        if (x < 9 && y > 0) {
            if (cellss.get(x+1).get(y-1).cell == '.') cellss.get(x+1).get(y-1).cell = '@';
        }
        if (x < 9) {
            if (cellss.get(x+1).get(y).cell == '.') cellss.get(x+1).get(y).cell = '@';
        }
        if (x < 9 && y < 9) {
            if (cellss.get(x+1).get(y+1).cell == '.') cellss.get(x+1).get(y+1).cell = '@';
        }
    }

    boolean checkAroundPoint(int x, int y) {
        if (cellss.get(x).get(y).cell == 'X') return true;
        if (x > 0 && y > 0) {
            if (cellss.get(x-1).get(y-1).cell == 'X') return true;
        }
        if (x > 0) {
            if (cellss.get(x-1).get(y).cell == 'X') return true;
        }
        if (x > 0 && y < 9) {
            if (cellss.get(x-1).get(y+1).cell == 'X') return true;
        }
        if (y > 0) {
            if (cellss.get(x).get(y-1).cell == 'X') return true;
        }
        if (y < 9) {
            if (cellss.get(x).get(y+1).cell == 'X') return true;
        }
        if (x < 9 && y > 0) {
            if (cellss.get(x+1).get(y-1).cell == 'X') return true;
        }
        if (x < 9) {
            if (cellss.get(x+1).get(y).cell == 'X') return true;
        }
        if (x < 9 && y < 9) {
            if (cellss.get(x+1).get(y+1).cell == 'X') return true;
        }
        return false;
    }


    boolean isNotGameOver() {
        return (Ship.allHealth >= 1);
    }
}