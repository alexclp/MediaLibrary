package alexmihnea.model;

import javax.swing.*;

/**
 * Main class for the library's items.
 * Has a fileName and an image.
 */

public class Item {
    protected String fileName;
    protected JLabel image;

    /**
     * Constructor for Item class.
     *
     * @param fileName String of the file name
     * @param image
     */

    public Item(String fileName, JLabel image) {
        this.fileName = fileName;
        this.image = image;
    }

    /**
     * Getter for the image field.
     *
     * @return JLabel of the image.
     */

    public JLabel getImage() {
        return image;
    }

    /**
     * Getter for the file name field.
     *
     * @return String of the file name.
     */

    public String getFileName() {
        return fileName;
    }
}
