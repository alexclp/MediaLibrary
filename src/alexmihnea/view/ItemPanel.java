package alexmihnea.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * ItemPanel class, subclass of JPanel.
 * Panel for each element of a category.
 * Has an image (JLabel), a name (JLabel) and a sub name (JLabel).
 */

public class ItemPanel extends JPanel {

    private JLabel image;
    private JLabel itemName;
    private JLabel itemsSubName;

    /**
     * Constructor that sets the class' fields (image, name and sub name).
     *
     * @param image
     * @param name
     * @param subName
     */

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

    /**
     * Method to create the panel and to add widgets to it.
     */

    private void createPanel() {
        add(image, BorderLayout.CENTER);
        JPanel text = new JPanel();
        text.setLayout(new GridLayout(2, 1, 5, 5));
        text.add(itemName);
        text.add(itemsSubName);
        add(text, BorderLayout.SOUTH);
    }


}
