package alexmihnea.controller;

import alexmihnea.generator.Media;
import alexmihnea.generator.MediaGenerator;
import alexmihnea.model.Film;
import alexmihnea.model.Item;
import alexmihnea.model.Music;
import alexmihnea.view.ItemPanel;
import alexmihnea.view.LibraryFrame;

import javax.swing.*;
import java.util.ArrayList;
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
        constructView();
    }


    public void parseData() {

        for (Media media : mediaArrayList) {
            String fileName = media.getName();
            JLabel fileImage = media.getImage();

            Pattern extensionPattern = Pattern.compile("\\.(?<extension>[^\\d]+$)");
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

                    Film film = new Film(fileTitle,media.getImage());
                    films.add(film);

                } else if (typeOfFile.equals("Music")) {

                    Music music = new Music(fileTitle,media.getImage());
                    tracks.add(music);
                } else {
                    Item item = new Item(fileName,media.getImage());
                    unknown.add(item);
                }
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

    private void constructView(){
        frame = new LibraryFrame(films.size(),tracks.size(),unknown.size());
        frame.setVisible(true);
        frame.setSize(1000,1000);
        for(Film film:films) {
            ItemPanel item = new ItemPanel(film.getImage(),film.getTitle(),film.getYear()+" - " + film.getQuality());
            frame.getFilmPanel().addElement(item);
        }
        for(Music track:tracks) {
            ItemPanel item = new ItemPanel(track.getImage(),track.getSong(),track.getArtist());
            frame.getMusicPanel().addElement(item);
        }
        for(Item unclassified:unknown) {
            ItemPanel item = new ItemPanel(unclassified.getImage(),unclassified.getFileName(),"Unclassified");
            frame.getUnknownPanel().addElement(item);
        }
    }
}
