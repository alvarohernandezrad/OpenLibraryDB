package ehu.isad.controller.db;

import ehu.isad.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AukerakKud {

    private static final AukerakKud instance = new AukerakKud();

    public static AukerakKud getInstance() {
        return instance;
    }

    private AukerakKud() {
    }

    public List<String> lortuLiburuak() {

        String query = "select isbn,title from liburuak";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String izenburua = rs.getString("izenburua");
                String isbn = rs.getString("isbn");

                System.out.println(izenburua);
                emaitza.add(new Book(isbn,izenburua);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }
}
