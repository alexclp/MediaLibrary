package alexmihnea;

import alexmihnea.generator.Media;
import alexmihnea.generator.MediaGenerator;
import alexmihnea.view.LibraryFrame;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Media> mediaArrayList = MediaGenerator.getMedia("/resources/film");

        for (Media media : mediaArrayList) {
            System.out.println(media);
        }

        LibraryFrame libraryFrame = new LibraryFrame(3, 4, 1);
        libraryFrame.setVisible(true);
    }
}
