import javax.swing.*;
import java.util.Scanner;

/**
 * Created by ChornyiUA on 23.09.2015.
 */
public class Window extends JFrame {
    public static JTextArea jTextArea;

    public Window() {
        setSize(400, 400);
        setTitle("Sea Battle");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        jTextArea = new JTextArea();
        add(jTextArea);
    }
}
