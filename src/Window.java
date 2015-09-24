import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by ChornyiUA on 23.09.2015.
 */
public class Window extends JFrame {
    public static JTextArea jTextArea;
    public static JLabel jLabel;

    public Window() {
        setSize(400, 400);
        setTitle("Sea Battle");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setDefaultLookAndFeelDecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        jTextArea = new JTextArea();
        add(jTextArea, BorderLayout.PAGE_START);
        jTextArea.setVisible(true);
        jLabel = new JLabel();
        add(jLabel, BorderLayout.CENTER);
        jLabel.setText("Тестовый текст!!!!!!");
        jLabel.setBounds(100, 50, 100, 50);
        jTextArea.setEditable(false);
        jTextArea.setLocation(0,0);



    }
}
