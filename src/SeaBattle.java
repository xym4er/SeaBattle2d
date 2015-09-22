/**
 * Created by ChornyiUA on 15.09.2015.
 */
public class SeaBattle {
    public static void main(String[] args) {
        Player player = new Player();
        Field2d field2d = new Field2d();

        System.out.println("начало игры");

        field2d.init();
        System.out.println();
        do {
            field2d.showField();
            System.out.println();
            field2d.doShoot(player.getShoot(), player.getShoot());
        } while (field2d.isNotGameOver());

        field2d.showField();
        System.out.println("game over");
    }

}
