package ehu.isad;

import java.util.Arrays;

public class Details {
    String[] publishers;
    String title;
    int number_of_pages;

    @Override
    public String toString() {
        return "Details{" +
                ", title='" + title + '\'' +
                ", publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                '}';
    }
    public String getTitle() {
        return title;
    }
    public String[] getPublishers() {
        return publishers;
    }
    public int getNumber_of_pages() {
        return number_of_pages;
    }
}