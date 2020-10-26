package ehu.isad;

import com.google.gson.Gson;
import ehu.isad.utils.Sarea;
public class Book {
    private String isbn;
    private String thumbnail_url;
    private Details details;

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", thumbnail_url='" + thumbnail_url + '\'' +
                ", details=" + details +
                '}';
    }

    public String getIsbn(){
        return isbn;
    }
    public String getThumbnail_url(){
        return thumbnail_url;
    }
    public Details getDetails(){
        return details;
    }

    public Book getBook(String isbn){
        Sarea sarea = new Sarea();
        String line = sarea.readFromURL(isbn);
        Gson gson = new Gson();
        Book book = gson.fromJson(line,Book.class);
        book.isbn = isbn;
        return book;
    }

    public String irudiErtainaLortu(){
        String irudia = this.thumbnail_url.substring(0,this.thumbnail_url.length()-5);
        irudia = irudia + "M.jpg";
        return irudia;
    }
}