package Edition;

import javax.swing.*;
import java.awt.*;

public class InventairesWindow extends JFrame {

    public InventairesWindow() {
        setTitle("Istore - Edition Inventaires");
        setSize(600, 600);
        setLocation(1400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Edition Inventaires");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(30));
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        add(panel);

    }
}
