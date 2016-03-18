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

public class Controller {
    private ArrayList<Media> mediaArrayList;
    private LibraryFrame frame;
    private ArrayList<Film> films;
    private ArrayList<Music> tracks;
    private ArrayList<Item> unknown;

    public Controller() {
        mediaArrayList = MediaGenerator.getMedia("/resources/film");
        films = new ArrayList<>();
        tracks = new ArrayList<>();
        unknown = new ArrayList<>();
        parseData();
        constructFrame();
        constructView();
        sortFilm();
        sortMusic();
    }


    public void parseData() {

        for (Media media : mediaArrayList) {
            String fileName = media.getName();
            Pattern extensionPattern = Pattern.compile(".*\\.(?<extension>[^\\d]+$)");
            Matcher extensionMatcher = extensionPattern.matcher(fileName);


            if (extensionMatcher.find()) {
                String fileTitle = "";
                String typeOfFile = checkTypeOfFile(extensionMatcher.group("extension"));

                Pattern filePattern = Pattern.compile("^(?<name>[^\\.]+\\.)[^\\d]+$");
                Matcher fileMatcher = filePattern.matcher(fileName);

                if (fileMatcher.find()) {
                    fileTitle = fileMatcher.group("name");
                }

                if (typeOfFile.equals("Film")) {

                    Film film = new Film(fileTitle, media.getImage());
                    films.add(film);

                } else if (typeOfFile.equals("Music")) {

                    Pattern musicFilePattern = Pattern.compile("^(?<name>.*)\\.(aiff|aac|aax|oog|wav|wma)$");
                    Matcher musicFileMatcher = musicFilePattern.matcher(fileName);

                    if (musicFileMatcher.find()) {
                        String musicFileTitle = musicFileMatcher.group("name");

                        Music music = new Music(musicFileTitle, media.getImage());
                        tracks.add(music);
                    }

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

    private String checkTypeOfFile(String extension) {
        if (isFilm(extension)) {
            return "Film";
        } else if (isMusic(extension)) {
            return "Music";
        } else {
            return "Unclassified";
        }
    }

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

    private void constructFrame() {
        frame = new LibraryFrame(films.size(), tracks.size(), unknown.size());
        frame.setVisible(true);
        frame.setSize(1000, 1000);
    }

    private void sortFilm() {
        JComboBox<String> comboBox = frame.getFilmPanel().getSort();
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

    private void sortMusic() {
        JComboBox<String> comboBox = frame.getMusicPanel().getSort();
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
