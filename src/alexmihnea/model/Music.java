package alexmihnea.model;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for the Music. Subclass of Item.
 * Has an artist name and song name.
 */

public class Music extends Item {

    private String artist;
    private String song;

    /**
     * Constructor for the Music file.
     * Extracts the artist name and the song from the filename.
     *
     * @param fileName
     * @param image
     */

    public Music(String fileName, JLabel image) {
        super(fileName, image);
        extractArtist();
        extractSong();
    }

    /**
     * Method to extract the song name using a regular expression.
     */

    private void extractSong() {
        Pattern pattern = Pattern.compile("^(?<song>[^\\-]+)\\s+\\-.*");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            song = matcher.group("song");
        }
    }

    /**
     * Method to extract the artist name using a regular expression.
     */

    private void extractArtist() {
        Pattern pattern = Pattern.compile("^.*\\-\\s+(?<artist>([^\\-]+)).*$");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            artist = matcher.group("artist");
        }
    }

    /**
     * Getter for the artist field.
     *
     * @return String of the artist's name
     */

    public String getArtist() {
        return artist;
    }

    /**
     * Getter for the song field.
     *
     * @return String of the song's name
     */

    public String getSong() {
        return song;
    }

    /**
     * Overriding toString method for debugging purposes.
     *
     * @return
     */

    @Override
    public String toString() {
        return "Music{" +
                "artist='" + artist + '\'' +
                ", song='" + song + '\'' +
                '}';
    }
}
