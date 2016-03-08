package alexmihnea.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexclp on 08/03/2016.
 */
public class CategoryPanel extends JPanel {

    private JLabel title;
    private JPanel centerPanel;
    private JComboBox<String> sort;

    public CategoryPanel(String category, int nrOfItems) {
        title = new JLabel(category);
        sort = new JComboBox<>();
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 2, 100, 10));
        northPanel.add(title);
        northPanel.add(sort);
        centerPanel = new JPanel();
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        centerPanel.setLayout(new GridLayout(1, nrOfItems, 10, 10));
        add(new JScrollPane(centerPanel), BorderLayout.CENTER);
    }

    public void addElement(ItemPanel panel) {
        centerPanel.add(panel);
    }

}
