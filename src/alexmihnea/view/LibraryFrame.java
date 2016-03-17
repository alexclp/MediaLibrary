package alexmihnea.view;

import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame {

    private CategoryPanel filmPanel;
    private CategoryPanel musicPanel;
    private CategoryPanel unknownPanel;

    public LibraryFrame(int nrOfFilms, int nrOfMusic, int nrOfUnknown) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filmPanel = new CategoryPanel("Film", nrOfFilms,true);
        musicPanel = new CategoryPanel("Music", nrOfMusic,true);
        unknownPanel = new CategoryPanel("Unclassified", nrOfUnknown,false);
        setLayout(new GridLayout(3, 1, 10, 10));
        add(filmPanel);
        add(musicPanel);
        add(unknownPanel);
        setSize(500, 500);
        filmPanel.sortFilm();
        musicPanel.sortMusic();
    }

    public CategoryPanel getFilmPanel() {
        return filmPanel;
    }

    public CategoryPanel getMusicPanel() {
        return musicPanel;
    }

    public CategoryPanel getUnknownPanel() {
        return unknownPanel;
    }

    public void empty(){
        filmPanel.clear();
        musicPanel.clear();
        unknownPanel.clear();
    }

    public void reconstruct(){
        filmPanel.revalidate();filmPanel.repaint();
        musicPanel.revalidate();musicPanel.repaint();
        unknownPanel.revalidate();unknownPanel.repaint();
    }
}
