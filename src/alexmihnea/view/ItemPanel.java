package alexmihnea.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemPanel extends JPanel {

    private JLabel image;
    private JLabel text;
    private JLabel itemName;
    private JLabel itemsSubName;

    public ItemPanel(JLabel image, String name, String subName) {
        this.image = image;
        itemName = new JLabel(name);
        itemName.setHorizontalAlignment(JLabel.CENTER);
        itemsSubName = new JLabel(subName);
        itemsSubName.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        configurePanel();
    }

    private void configurePanel() {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(image, BorderLayout.CENTER);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        add(imagePanel);
        add(itemName);
        add(itemsSubName);
    }


}
