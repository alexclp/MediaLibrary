package alexmihnea.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private void extractTitle() {
//        ^(?<name>[^\(]+)\s+\(
        Pattern firstCasePattern = Pattern.compile("^(?<name>[^\\(]+)\\s+\\(");
        Matcher firstCaseMatcher = firstCasePattern.matcher("The Genius Skater Of Paris (1990) (HD, 720p).");
        Pattern finalTitlePattern = Pattern.compile("\\D+\\s");

        if (firstCaseMatcher.find()) {
            Matcher finalMatcher = finalTitlePattern.matcher(firstCaseMatcher.group("name"));
            System.out.println(firstCaseMatcher.group("name"));
            title = finalMatcher.group("name");
        }

        System.out.println(title);
    }


    private void extractQuality() {
        Pattern pattern = Pattern.compile(".(\\p{Upper}\\p{Upper},\\s\\d+p).");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            Pattern pattern1 = Pattern.compile("\\p{Upper}\\p{Upper},\\s\\d+p");
            Matcher matcher1 = pattern1.matcher(matcher.group(0));
            if (matcher1.find()) {
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
            if (matcher1.find()) {
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
