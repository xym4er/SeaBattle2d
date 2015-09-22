/**
 * Created by ChornyiUA on 15.09.2015.
 */
public class Field2d {
    public static char[][] cells = new char[10][10];
    Ship ship6 = new Ship(4);
    Ship ship1 = new Ship(3);
    Ship ship2 = new Ship(3);
    Ship ship3 = new Ship(2);
    Ship ship4 = new Ship(2);
    Ship ship5 = new Ship(2);
    Ship ship7 = new Ship(1);
    Ship ship8 = new Ship(1);
    Ship ship9 = new Ship(1);
    Ship ship10 = new Ship(1);

    void init() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = '.';
            }
        }
        setShip3(ship6);
        setShip3(ship2);
        setShip3(ship3);
        setShip3(ship4);
        setShip3(ship5);
        setShip3(ship1);
        setShip3(ship7);
        setShip3(ship8);
        setShip3(ship9);
        setShip3(ship10);
    }

    void showField() {
        System.out.println(" 0123456789           0123456789");
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
        }
    }

    void doShoot(int y, int x) {
        System.out.println("ваш выстрел: " + y + " - " + x);
        switch (cells[x][y]) {
            case '.':
                System.out.println("Missed");
                cells[x][y] = '@';
                break;
            case '@':
                System.out.println("Уже стреляли");
                break;
            case 'X':
                System.out.println("Попал!");
                cells[x][y] = '#';
                Ship.allHealth--;
                break;
            default:
                System.out.println("ERROR");
        }
    }

    void setShip3(Ship ship) {
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
            } else return ((ship.positionY + ship.maxHealth > 9) || checkAroundPoint(ship.positionX, ship.positionY) || checkAroundPoint(ship.positionX, ship.positionY + ship.maxHealth));
        } else {
            return (checkAroundPoint(ship.positionX, ship.positionY));
        }
    }

    boolean checkAroundPoint(int x, int y) {
        if (cells[x][y] == 'X') return true;
        try {
            if (cells[x - 1][y - 1] == 'X') return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (cells[x - 1][y] == 'X') return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (cells[x - 1][y + 1] == 'X') return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (cells[x][y - 1] == 'X') return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (cells[x][y + 1] == 'X') return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (cells[x + 1][y - 1] == 'X') return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (cells[x + 1][y] == 'X') return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (cells[x + 1][y + 1] == 'X') return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return false;
    }


    boolean isNotGameOver() {
        return (Ship.allHealth >= 1);
    }
}