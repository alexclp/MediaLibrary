package alexmihnea.controller;

import alexmihnea.generator.Media;
import alexmihnea.generator.MediaGenerator;
import alexmihnea.model.Film;
import alexmihnea.model.Item;
import alexmihnea.model.Music;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    ArrayList<Media> mediaArrayList;

    public Controller() {
        mediaArrayList = MediaGenerator.getMedia("/resources/film");
        parseData();
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

                } else if (typeOfFile.equals("Music")) {

                    Music music = new Music(fileTitle,media.getImage());

                } else {

                    Item item = new Item(fileName,media.getImage());
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
}
