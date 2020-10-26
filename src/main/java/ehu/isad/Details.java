package ehu.isad;

import javafx.beans.property.StringPropertyBase;

import java.util.Arrays;

public class Details {
    private String title;
    private String subtitle = "";
    private String[] publishers;
    private int number_of_pages;

    @Override
    public String toString() {
        return "Details{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                '}';
    }

    public String getTitle() {
        return title;
    }
    public String getSubtitle(){ return subtitle; }
    public String[] getPublishers() {
        return publishers;
    }
    public int getNumber_of_pages() {
        return number_of_pages;
    }
}