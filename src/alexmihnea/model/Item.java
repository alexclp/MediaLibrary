package alexmihnea.model;

import javax.swing.*;

/**
 * Created by alexclp on 14/03/2016.
 */
public class Item {
    protected String fileName;
    protected JLabel image;
    public Item(String fileName,JLabel image) {
        this.fileName = fileName;
        this.image = image;
    }
}
