package alexmihnea.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Film extends Item {
    private String year;
    private String quality;
    private String title;

    public Film(String fileName) {
        super(fileName);
        extractTitle();
        extractQuality();
        extractYear();
    }

    private void extractTitle() {
        Pattern firstCasePattern = Pattern.compile("^(?<name>[^\\(]+)\\s+\\(");
        Matcher firstCaseMatcher = firstCasePattern.matcher(fileName);

        Pattern secondCasePattern = Pattern.compile("^\\([^\\)]+\\)\\s+(?<name>[^\\(]+)\\s+\\(");
        Matcher secondCaseMatcher = secondCasePattern.matcher(fileName);

        Pattern thirdCasePattern = Pattern.compile("^\\([^\\)]+\\)\\s+\\([^\\)]+\\)\\s+(?<name>[^(\\.]+)");
        Matcher thirdCaseMatcher = thirdCasePattern.matcher(fileName);

        if (firstCaseMatcher.find()) {
            title = firstCaseMatcher.group("name");
        }

        if (secondCaseMatcher.find()) {
            title = secondCaseMatcher.group("name");
        }

        if (thirdCaseMatcher.find()) {
            title = thirdCaseMatcher.group("name");
        }
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

    @Override
    public String toString() {
        return "Film{" +
                "year='" + year + '\'' +
                ", quality='" + quality + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
