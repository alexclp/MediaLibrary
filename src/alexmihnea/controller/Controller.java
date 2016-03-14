package alexmihnea.controller;

import alexmihnea.generator.Media;
import alexmihnea.generator.MediaGenerator;
import alexmihnea.model.Film;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    ArrayList<Media> mediaArrayList;
    public Controller() {
        mediaArrayList = MediaGenerator.getMedia("/resources/film");
        //test();
        Film film = new Film("");
    }


    public void test(){
        for(Media media:mediaArrayList){
            String fileName = media.getName();
            System.out.println(fileName);
            Pattern iExtension = Pattern.compile("(\\.\\D{3,4})");
            Matcher matcher = iExtension.matcher(fileName);
            if (matcher.find()) {
                //System.out.println(matcher.group(0));
                Pattern extension = Pattern.compile("(\\w{3,4})");
                Matcher matcher1 = extension.matcher(matcher.group(0));
                if (matcher1.find()) {
                    if(matcher1.group(0).equals("flv") ||
                            matcher1.group(0).equals("gif") ||
                            matcher1.group(0).equals("mkv") ||
                            matcher1.group(0).equals("mpeg") ||
                            matcher1.group(0).equals("mpg") ||
                            matcher1.group(0).equals("mov")) {
                        Pattern pattern = Pattern.compile(".*\\.");
                        Matcher matcher2 = pattern.matcher(fileName);
                        if(matcher2.find()) {
                            Film film = new Film(matcher2.group(0));
                        }
                    }
                }
            }
        }
    }
}
