package alexmihnea.model;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Music extends Item {
    private String artist;
    private String song;

    public Music(String fileName, JLabel image) {
        super(fileName, image);
//        System.out.println("Filename: " + fileName);
        extractArtist();
        extractSong();
    }

    public void extractSong() {
        Pattern pattern = Pattern.compile("^(?<song>[^\\-]+)\\s+\\-.*");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            song = matcher.group("song");
        }
    }

    public void extractArtist() {
        Pattern pattern = Pattern.compile("^.*\\-\\s+(?<artist>([^\\-]+)).*$");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            artist = matcher.group("artist");
        }
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    @Override
    public String toString() {
        return "Music{" +
                "artist='" + artist + '\'' +
                ", song='" + song + '\'' +
                '}';
    }
}
