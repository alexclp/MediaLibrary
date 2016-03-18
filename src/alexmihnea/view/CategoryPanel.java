package alexmihnea.view;

import javax.swing.*;
import java.awt.*;

/**
 * Subclass of JPanel for the sliding category panel.
 */

public class CategoryPanel extends JPanel {

    private JLabel title;
    private JPanel centerPanel;
    private JComboBox<String> sortComboBox;

    /**
     * Constructor for the CategoryPanel class.
     * Creates a new category based on the category name, the number of items inside and a combo box for sorting (yes or no).
     *
     * @param category
     * @param nrOfItems
     * @param hasComboBox
     */

    public CategoryPanel(String category, int nrOfItems, boolean hasComboBox) {
        title = new JLabel(category);
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 2, 100, 10));
        northPanel.add(title);
        centerPanel = new JPanel();
        setLayout(new BorderLayout());
        centerPanel.setLayout(new GridLayout(1, nrOfItems, 10, 10));
        add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        if (hasComboBox) {
            setupComboBox();
            northPanel.add(sortComboBox);
        }
        add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Method to clear the content of the panel.
     */

    public void clear() {
        centerPanel.removeAll();
    }

    /**
     * Method to add a new ItemPanel inside.
     *
     * @param panel
     */

    public void addElement(ItemPanel panel) {
        centerPanel.add(panel);
    }

    /**
     * Method to setup the combo box used for selecting the sorting filter.
     */

    public void setupComboBox() {
        sortComboBox = new JComboBox<>();
        String NOT_SELECTABLE_OPTION = " Sort by ";
        sortComboBox.setModel(new DefaultComboBoxModel<String>() {
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });
        sortComboBox.addItem(NOT_SELECTABLE_OPTION);
    }

    /**
     * Method to add options of sorting films to the combo box.
     */

    public void configureFilmSortComboBox() {
        sortComboBox.addItem("Title");
        sortComboBox.addItem("Release Year");
        sortComboBox.addItem("Quality");
    }

    /**
     * Method to add options of sorting music to the combo box.
     */

    public void configureMusicSortComboBox() {
        sortComboBox.addItem("Track Name");
        sortComboBox.addItem("Artist");
    }

    /**
     * Getter for the sorting options combo box.
     *
     * @return
     */

    public JComboBox<String> getSortComboBox() {
        return sortComboBox;
    }
}
