/**
 * Created by ChornyiUA on 15.09.2015.
 */
public class SeaBattle {
    public static void main(String[] args) {
        Player player = new Player();
        Field2d field2d = new Field2d();
        Window window = new Window();
        Window.jTextArea.append("начало игры\n");
        field2d.init();
        Window.jTextArea.append("\n");
        do {
            field2d.showField();
            Window.jTextArea.append("\n");
            field2d.doShoot(player.getShoot(), player.getShoot());
        } while (field2d.isNotGameOver());

        field2d.showField();
        Window.jTextArea.append("game over\n");
    }

}
