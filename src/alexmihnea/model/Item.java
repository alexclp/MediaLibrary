package alexmihnea.model;

import javax.swing.*;

public class Item {
    protected String fileName;
    protected JLabel image;

    public Item(String fileName, JLabel image) {
        this.fileName = fileName;
        this.image = image;
    }

    public JLabel getImage() {
        return image;
    }

    public String getFileName() {
        return fileName;
    }
}
