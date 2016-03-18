package alexmihnea.view;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {

    private JLabel title;
    private JPanel centerPanel;
    private JComboBox<String> sort;

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
            northPanel.add(sort);
        }
        add(northPanel, BorderLayout.NORTH);
    }

    public void clear() {
        centerPanel.removeAll();
    }

    public void addElement(ItemPanel panel) {
        centerPanel.add(panel);
    }

    public void setupComboBox() {
        sort = new JComboBox<>();
        String NOT_SELECTABLE_OPTION = " Sort by ";
        sort.setModel(new DefaultComboBoxModel<String>() {
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
        sort.addItem(NOT_SELECTABLE_OPTION);
    }

    public void sortFilm() {
        sort.addItem("Title");
        sort.addItem("Release Year");
        sort.addItem("Quality");
    }

    public void sortMusic() {
        sort.addItem("Track Name");
        sort.addItem("Artist");
    }

    public JComboBox<String> getSort() {
        return sort;
    }
}
