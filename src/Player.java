import java.util.Scanner;

/**
 * Created by igor on 13.09.15.
 */
public class Player {
    String name;

    int  getShoot() {
        Scanner scanner = new Scanner(System.in);
        int shoot;
        do {
            if (scanner.hasNextInt()) {
                shoot = scanner.nextInt();
                break;
            }
            scanner.nextLine();
        } while (true);

        return shoot;
    }
}
