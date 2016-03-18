package alexmihnea.controller;

import alexmihnea.generator.Media;
import alexmihnea.generator.MediaGenerator;
import alexmihnea.model.Film;
import alexmihnea.model.Item;
import alexmihnea.model.Music;
import alexmihnea.view.ItemPanel;
import alexmihnea.view.LibraryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller class.
 * Deals with view updates, events and getting data from model.
 */

public class Controller {
    private ArrayList<Media> mediaArrayList;
    private LibraryFrame frame;
    private ArrayList<Film> films;
    private ArrayList<Music> tracks;
    private ArrayList<Item> unknown;

    /**
     * Constructor for the controller.
     * Allocates memory for the arrays that hold the data and gets data from the generator.
     * Calls functions to parse the data and creates the view widgets along with event listeners.
     */

    public Controller() {
        mediaArrayList = MediaGenerator.getMedia();
        films = new ArrayList<>();
        tracks = new ArrayList<>();
        unknown = new ArrayList<>();
        parseData();
        constructFrame();
        constructView();
        configureFilmSortComboBox();
        configureMusicSortComboBox();
    }

    /**
     * Method to parse data received from the generator.
     * Will firstly create a regular expression to detect the file type and will check it.
     * Then it will check with another regular expression what the title is and will send it to the model.
     */

    public void parseData() {

        for (Media media : mediaArrayList) {
            String fileName = media.getName();
            Pattern extensionPattern = Pattern.compile(".*\\.(?<extension>[^\\d]+$)");
            Matcher extensionMatcher = extensionPattern.matcher(fileName);


            if (extensionMatcher.find()) {
                String fileTitle = "";
                String typeOfFile = checkTypeOfFile(extensionMatcher.group("extension"));

                Pattern filePattern = Pattern.compile("^(?<name>.*)\\.(aiff|aac|aax|oog|wav|wma|flv|gif|mkv|mpeg|mpg|mov)$");
                Matcher fileMatcher = filePattern.matcher(fileName);

                if (fileMatcher.find()) {
                    fileTitle = fileMatcher.group("name");
                }

                if (typeOfFile.equals("Film")) {

                    Film film = new Film(fileTitle, media.getImage());
                    films.add(film);

                } else if (typeOfFile.equals("Music")) {

                    Music music = new Music(fileTitle, media.getImage());
                    tracks.add(music);

                } else {
                    Item item = new Item(fileName, media.getImage());
                    unknown.add(item);
                }
            } else {
                Item item = new Item(fileName, media.getImage());
                unknown.add(item);
            }

        }

    }

    /**
     * Method to check the file type give some known extensions.
     *
     * @param extension String of the file extension
     * @return - String representation of the file type.
     */

    private String checkTypeOfFile(String extension) {
        if (isFilm(extension)) {
            return "Film";
        } else if (isMusic(extension)) {
            return "Music";
        } else {
            return "Unclassified";
        }
    }

    /**
     * Method that if the file is a film.
     *
     * @param extension String of the file extension
     * @return boolean (true if it's a film and vice versa)
     */

    private boolean isFilm(String extension) {

        if (extension.equals("mpeg") ||
                extension.equals("mpg") ||
                extension.equals("mov") ||
                extension.equals("mkv") ||
                extension.equals("flv") ||
                extension.equals("gif")) {
            return true;
        }

        return false;
    }

    /**
     * Method that if the file is a music file.
     *
     * @param extension String of the file extension
     * @return boolean (true if it's a film and vice versa)
     */

    private boolean isMusic(String extension) {

        if (extension.equals("aiff") ||
                extension.equals("aax") ||
                extension.equals("aac") ||
                extension.equals("oog") ||
                extension.equals("wav") ||
                extension.equals("wma")) {
            return true;
        }

        return false;
    }

    /**
     * Method that creates the view's frame
     */

    private void constructFrame() {
        frame = new LibraryFrame(films.size(), tracks.size(), unknown.size());
        frame.setVisible(true);
        frame.setSize(1000, 1000);
    }

    /**
     * Method to create the view's combo box to deal with sorting.
     * Will create an action listener for it and will sort the films.
     */

    private void configureFilmSortComboBox() {
        JComboBox<String> comboBox = frame.getFilmPanel().getSortComboBox();
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem().equals("Title")) {
                    films.sort(new Comparator<Film>() {
                        @Override
                        public int compare(Film o1, Film o2) {
                            String o1name = o1.getTitle();
                            String o2name = o2.getTitle();
                            Pattern pattern = Pattern.compile("The\\s(?<name>.*)");
                            Matcher matcher1 = pattern.matcher(o1.getTitle());
                            Matcher matcher2 = pattern.matcher(o2.getTitle());
                            if (matcher1.find())
                                o1name = matcher1.group("name");
                            if (matcher2.find())
                                o2name = matcher2.group("name");
                            if (o1name.equals(o2name))
                                return 0;
                            else if (o1name.compareTo(o2name) > 0)
                                return 1;
                            return -1;
                        }
                    });
                } else if (comboBox.getSelectedItem().equals("Release Year")) {
                    films.sort(new Comparator<Film>() {
                        @Override
                        public int compare(Film o1, Film o2) {
                            if (o1.getYear().equals(o2.getYear()))
                                return 0;
                            else if (o1.getYear().compareTo(o2.getYear()) > 0)
                                return -1;
                            return 1;
                        }
                    });
                } else {
                    films.sort(new Comparator<Film>() {
                        @Override
                        public int compare(Film o1, Film o2) {
                            String quality1 = new String();
                            String quality2 = new String();
                            Pattern pattern = Pattern.compile("\\w\\w,\\s(?<quality>[\\d]{3,4})p");
                            Matcher matcher1 = pattern.matcher(o1.getQuality());
                            Matcher matcher2 = pattern.matcher(o2.getQuality());
                            if (matcher1.find())
                                quality1 = matcher1.group("quality");
                            if (matcher2.find())
                                quality2 = matcher2.group("quality");
                            if (quality1.equals(quality2))
                                return 0;
                            else if (Integer.parseInt(quality1) > Integer.parseInt(quality2))
                                return -1;
                            return 1;
                        }
                    });
                }
                constructView();
            }
        });
    }

    /**
     * Method to create the view's combo box to deal with sorting.
     * Will create an action listener for it and will sort the music files.
     */

    private void configureMusicSortComboBox() {
        JComboBox<String> comboBox = frame.getMusicPanel().getSortComboBox();
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem().equals("Track Name")) {
                    tracks.sort(new Comparator<Music>() {
                        @Override
                        public int compare(Music o1, Music o2) {
                            String o1song = o1.getSong();
                            String o2song = o2.getSong();
                            Pattern pattern = Pattern.compile("The\\s(?<name>.*)");
                            Matcher matcher1 = pattern.matcher(o1.getSong());
                            Matcher matcher2 = pattern.matcher(o2.getSong());
                            if (matcher1.find())
                                o1song = matcher1.group("name");
                            if (matcher2.find())
                                o2song = matcher2.group("name");
                            if (o1song.equals(o2song))
                                return 0;
                            else if (o1song.compareTo(o2song) > 0)
                                return 1;
                            return -1;
                        }
                    });
                } else {
                    tracks.sort(new Comparator<Music>() {
                        @Override
                        public int compare(Music o1, Music o2) {
                            String o1artist = o1.getArtist();
                            String o2artist = o2.getArtist();
                            Pattern pattern = Pattern.compile("The\\s(?<name>.*)");
                            Matcher matcher1 = pattern.matcher(o1.getArtist());
                            Matcher matcher2 = pattern.matcher(o2.getArtist());
                            if (matcher1.find())
                                o1artist = matcher1.group("name");
                            if (matcher2.find())
                                o2artist = matcher2.group("name");
                            if (o1artist.equals(o2artist))
                                return 0;
                            else if (o1artist.compareTo(o2artist) > 0)
                                return 1;
                            return -1;
                        }
                    });
                }
                constructView();
            }
        });
    }

    /**
     * Method to send the data to the view's panels.
     */

    private void constructView() {
        frame.empty();
        for (Film film : films) {
            ItemPanel item = new ItemPanel(film.getImage(), film.getTitle(), film.getYear() + " - " + film.getQuality());
            frame.getFilmPanel().addElement(item);
        }
        for (Music track : tracks) {
            ItemPanel item = new ItemPanel(track.getImage(), track.getSong(), track.getArtist());
            frame.getMusicPanel().addElement(item);
        }
        for (Item unclassified : unknown) {
            ItemPanel item = new ItemPanel(unclassified.getImage(), unclassified.getFileName(), "Unclassified");
            frame.getUnknownPanel().addElement(item);
        }
        frame.reconstruct();
    }
}
