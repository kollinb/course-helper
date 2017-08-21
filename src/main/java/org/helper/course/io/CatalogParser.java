package org.helper.course.io;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CatalogParser {

    /**
     * Fetches the courses full name (ex. Exploring Creative Computing)
     * as well as the description and returns them in that order.
     *
     * @param identifier The identifier for the course (ex. CS101).
     * @return A String array containing the full name and description, in that order.
     */
    public String[] getExtraInfo(String identifier) {
        String[] extraInfo = {"", ""};
        String[] components = splitIdentifier(identifier);
        Document catalogSearch = null, courseInfo = null;
        int courseID = -1;

        try {
            catalogSearch = Jsoup.connect("http://catalog.winona.edu/content.php?filter[27]=" + components[0] + "&filter[29]=" + components[1] + "&cur_cat_oid=14&navoid=1150")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36")
                    .followRedirects(true)
                    .get();
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(catalogSearch != null) {
            Elements links = catalogSearch.select("a[href]");
            for(Element link : links) {
                String url = link.attr("href");
                if(url.startsWith("preview_course_nopop.php?catoid=14&coid=")) {
                    courseID = Integer.parseInt(url.substring(40));
                }
            }
        }

        if(courseID != -1) {
            try {
                courseInfo = Jsoup.connect("http://catalog.winona.edu/ajax/preview_course.php?catoid=14&coid=" + courseID + "&show")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36")
                        .followRedirects(true)
                        .get();
            } catch(IOException e) {
                e.printStackTrace();
            }

            if(courseInfo != null) {
                String[] courseData = courseInfo.select("div").get(2).text().split(" S.H.");
                String description = courseData[1].substring(2);
                courseData[0] = courseData[0].substring(0, courseData[0].length()-3);
                String fullName = courseData[0].split(spacedIdentifier(identifier) + " - ")[1];
                extraInfo[0] = fullName;
                extraInfo[1] = description;
            }
        }

        return extraInfo;
    }

    /**
     * Splits the identifier into the prefix and course number.
     * (ex. "CS101" => {"CS", "101"} )
     *
     * @param identifier The original identifier (ex. CS101).
     * @return The String array of prefix and course number, in that order.
     */
    private static String[] splitIdentifier(String identifier) {
        StringBuilder prefix = new StringBuilder();
        StringBuilder number = new StringBuilder();
        for(char ch : identifier.toCharArray()) {
            if(!Character.isDigit(ch))
                prefix.append(ch);
            else
                number.append(ch);
        }

        return new String[] {prefix.toString(), number.toString()};
    }

    /**
     * Adds a space to the identifier.
     * (ex. "CS101" => "CS 101")
     * @param identifier The original identifier (ex. CS101).
     * @return The course identifier with a space added.
     */
    private static String spacedIdentifier(String identifier) {
        String[] parts = splitIdentifier(identifier);

        return parts[0] + " " + parts[1];
    }
}
