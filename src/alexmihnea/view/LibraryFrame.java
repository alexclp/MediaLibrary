package alexmihnea.view;

import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame {

    private CategoryPanel filmPanel;
    private CategoryPanel musicPanel;
    private CategoryPanel unknownPanel;

    public LibraryFrame(int nrOfFilms, int nrOfMusic, int nrOfUnknown) {
        filmPanel = new CategoryPanel("Film", nrOfFilms);
        musicPanel = new CategoryPanel("Music", nrOfMusic);
        unknownPanel = new CategoryPanel("Unclassified", nrOfUnknown);
        setLayout(new GridLayout(3, 1, 10, 10));
        add(filmPanel);
        add(musicPanel);
        add(unknownPanel);
        setSize(500, 500);
    }


}
