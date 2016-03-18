package alexmihnea.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemPanel extends JPanel {

    private JLabel image;
    private JLabel itemName;
    private JLabel itemsSubName;

    public ItemPanel(JLabel image, String name, String subName) {
        this.image = image;
        image.setHorizontalAlignment(JLabel.CENTER);
        itemName = new JLabel(name);
        itemName.setHorizontalAlignment(JLabel.CENTER);
        itemsSubName = new JLabel(subName);
        itemsSubName.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        createPanel();
    }

    private void createPanel() {
        add(image, BorderLayout.CENTER);
        JPanel text = new JPanel();
        text.setLayout(new GridLayout(2, 1, 5, 5));
        text.add(itemName);
        text.add(itemsSubName);
        add(text, BorderLayout.SOUTH);
    }


}
