package alexmihnea.view;

import javax.swing.*;
import java.awt.*;

/**
 * The main frame of the app. Extends JFrame class.
 * Has 3 main panels: film, music and unclassified.
 */

public class LibraryFrame extends JFrame {

    private CategoryPanel filmPanel;
    private CategoryPanel musicPanel;
    private CategoryPanel unknownPanel;

    /**
     * Constructor for the main frame.
     * Creates the panels for each category based on the number of items.
     *
     * @param nrOfFilms
     * @param nrOfMusic
     * @param nrOfUnknown
     */

    public LibraryFrame(int nrOfFilms, int nrOfMusic, int nrOfUnknown) {
        super("Media Library");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filmPanel = new CategoryPanel("Film", nrOfFilms, true);
        musicPanel = new CategoryPanel("Music", nrOfMusic, true);
        unknownPanel = new CategoryPanel("Unclassified", nrOfUnknown, false);
        setLayout(new GridLayout(3, 1, 10, 10));
        add(filmPanel);
        add(musicPanel);
        add(unknownPanel);
        setSize(500, 500);
        filmPanel.configureFilmSortComboBox();
        musicPanel.configureMusicSortComboBox();
    }

    /**
     * Getter for the film panel
     *
     * @return
     */

    public CategoryPanel getFilmPanel() {
        return filmPanel;
    }

    /**
     * Getter for the music panel
     *
     * @return
     */

    public CategoryPanel getMusicPanel() {
        return musicPanel;
    }

    /**
     * Getter for the unclassified panel
     *
     * @return
     */

    public CategoryPanel getUnknownPanel() {
        return unknownPanel;
    }

    /**
     * Method to clear all content from the frame's panels
     */

    public void empty() {
        filmPanel.clear();
        musicPanel.clear();
        unknownPanel.clear();
    }

    /**
     * Method to redraw all the view's panels
     */

    public void reconstruct() {
        filmPanel.revalidate();
        filmPanel.repaint();
        musicPanel.revalidate();
        musicPanel.repaint();
        unknownPanel.revalidate();
        unknownPanel.repaint();
    }
}
