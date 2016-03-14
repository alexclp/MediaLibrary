package alexmihnea.model;

import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexclp on 14/03/2016.
 */
public class Film extends Item {
    private String year;
    private String quality;
    private String title;
    public Film(String fileName) {
        super(fileName);
        System.out.println(fileName);
        extractTitle();
        extractQuality();
        extractYear();
    }

    private void extractTitle(){
        Pattern pattern1 = Pattern.compile("(\\w*\\s*)*\\w*");
        Pattern pattern2 = Pattern.compile("\\s(\\w*\\s)*\\w*\\.");
        Pattern pattern3 = Pattern.compile("\\s(\\w*\\s)*");
        Pattern case1 = Pattern.compile("(\\w*\\s*)*\\w*\\(\\.+\\)\\s\\(.+\\)\\.");
        Pattern case2 = Pattern.compile("\\(\\.*\\)\\s\\(\\.*\\)\\s\\.+");
        Matcher case1Matcher = case1.matcher("The Genius Skater Of Paris (1990) (HD, 720p).");
        Matcher case2Matcher = case2.matcher(fileName);
        Matcher matcher1 = pattern1.matcher("The Genius Skater Of Paris (1990) (HD, 720p).");
        Matcher matcher2 = pattern2.matcher(fileName);
        Matcher matcher3 = pattern3.matcher(fileName);
        if(matcher1.find())
            System.out.println(matcher1.group(0));
        if(case1Matcher.find()) {
            System.out.println(case1Matcher.group(0));
        }
//            if(matcher1.find()){
//            title = matcher1.group(0);}}
//        } else if(case2Matcher.find()) {
//            if(matcher2.find()){
//            title = matcher2.group(0);}
//        }
// } else{
//            title = matcher3.group(0);
//        }
        System.out.println(title);
    }
    private void extractQuality() {
        Pattern pattern = Pattern.compile(".(\\p{Upper}\\p{Upper},\\s\\d+p).");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            Pattern pattern1 = Pattern.compile("\\p{Upper}\\p{Upper},\\s\\d+p");
            Matcher matcher1 = pattern1.matcher(matcher.group(0));
            if(matcher1.find()){
                quality = matcher1.group(0);
            }
        }
    }

    private void extractYear() {
        Pattern pattern = Pattern.compile(".(\\d\\d\\d\\d).");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            Pattern pattern1 = Pattern.compile("\\d\\d\\d\\d");
            Matcher matcher1 = pattern1.matcher(matcher.group(0));
            if(matcher1.find()){
                year = matcher1.group(0);
            }
        }
    }

    public String getQuality() {
        return quality;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }
}
