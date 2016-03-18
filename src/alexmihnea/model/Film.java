package alexmihnea.model;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for the Film item. Extends the Item class.
 * Has year, quality, title (String fields).
 */

public class Film extends Item {

    private String year;
    private String quality;
    private String title;

    /**
     * Constructor for the Film class. Calls methods to extract info for the fields.
     *
     * @param fileName
     * @param image
     */

    public Film(String fileName, JLabel image) {
        super(fileName, image);
        extractTitle();
        extractQuality();
        extractYear();
    }

    /**
     * Method to extract film's title using regex.
     * The regular expressions are divided into 3 cases, depending on how the title looks like (parentheses placement)
     */

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

    /**
     * Method to extract film's quality using regular expressions.
     */

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

    /**
     * Method to extract the film's year using regular expressions.
     */

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

    /**
     * Getter for the quality field.
     *
     * @return String of the film's quality.
     */

    public String getQuality() {
        return quality;
    }

    /**
     * Getter for the year field.
     *
     * @return String of the film's year.
     */

    public String getYear() {
        return year;
    }

    /**
     * Getter for the title field.
     *
     * @return String of the film's title.
     */

    public String getTitle() {
        return title;
    }

    /**
     * Overriding toString method for debugging purposes.
     *
     * @return
     */

    @Override
    public String toString() {
        return year + " " + title + " " + quality;
    }
}
