package ehu.isad.controller.db;

import ehu.isad.Book;
import ehu.isad.utils.Sarea;

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

    public List<String> lortuLiburuak() { //combo boxean liburen izenburuak jarriko ditugu

        String query = "select isbn, izenburua from Liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>(); //izenburuen (STRING) lista bat bueltatuko dugu.
        try {
            while (rs.next()) {
                String izenburua = rs.getString("izenburua");
                long isbn = rs.getLong("isbn");

                System.out.println(izenburua + " : " + isbn);
                emaitza.add(izenburua); //izenburua gordeko dugu bakarrik. Izan ere combo boxean izenburuak agertuko dira bakarrik
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public String lortuIsbn(String liburuIzen) { //izena dugu. Orain datu basetik isbn-a eskuratuko dugu.
        String isbn = null;
        String query = "select ISBN from Liburua where izenburua='" + liburuIzen + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        try {
            while (rs.next()) {
                isbn = rs.getString("isbn");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isbn;
    }

    public Boolean liburuaDago(String liburuIzen) { // liburuaren izena dugula, datu basean kargatuta dagoen begiratuko dugu
        boolean badago = false;
        String query = "select orriKop from Liburua where izenburua='" + liburuIzen + "'"; //hasieratik izenburua eta isbn-a kargatu ditugunez liburuaren orri kopurua atertuko dugu
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        try {
            rs.next();
            badago = rs.getInt("orriKop") != 0; // orri kopurua 0 bada liburua ez dago kargatuta. Bestela bai
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return badago;
    }

    public void liburuaDatuBaseraIgo(String isbn) { //liburua lehen aldiz kargatuko dugu datu basean. Metodo honen bidez datu baseko "Liburua" taulan osatuko dugu.
        Book book = new Book();
        book = book.getBook(isbn);
        String query = "update Liburua set subtitulua='" + book.getDetails().getSubtitle() + "',orriKop='" + book.getDetails().getNumber_of_pages() + "',irudia='" + isbn + "' where isbn='" + book.getIsbn() + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
        this.irudiaKargatu(book);
        this.argitaletxeakDatuBaseraIgo(book);
    }

    private void irudiaKargatu(Book book) {
        Sarea sarea = new Sarea();
        sarea.argazkiaGorde(book.irudiErtainaLortu(), String.valueOf(book.getIsbn()));
    }

    private void argitaletxeakDatuBaseraIgo(Book book){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        for(int i=0; i<book.getDetails().getPublishers().length;i++){
            if(!this.argitaletxeaDBDago(book.getDetails().getPublishers()[i])){
                String query = "insert into Argitaletxea values (\""+book.getDetails().getPublishers()[i]+"\")";
                dbKudeatzaile.execSQL(query);
            }
            String q2 = "insert into Dauka values (\""+book.getDetails().getPublishers()[i]+"\","+book.getIsbn()+"')";
            dbKudeatzaile.execSQL(q2);
        }
    }

    public String lortuDatuak(String isbn) {
        String query = "select izenburua, subtitulua, orriKop, irudia from Liburua where isbn='" + isbn + "'"; //ez dugu select * erabiliko arazoak ematen dituelako
        String datuak = "";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        try {
            while (rs.next()) {
                datuak = rs.getString("izenburua");
                datuak = datuak + "," + rs.getInt("orriKop");
                datuak = datuak + "," + rs.getString("irudia");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return datuak;
    }

    public List<String> lortuArgitaletxeak(String isbn){
        String query = "select argitaletxea from Dauka where isbn='"+isbn+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        List<String> argitaletxeak = new ArrayList<String>();
        try{
            while (rs.next()){
                argitaletxeak.add(rs.getString("argitaletxea"));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return argitaletxeak;
    }

    private boolean argitaletxeaDBDago(String argitaletxe){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String query = "select izena from Argitaletxea where izena=\""+argitaletxe+"\""; //atributu bakarra dugu, beraz konprobaketa horrela egingo dugu *SELECT izena WHERE izena*
        ResultSet rs = dbKudeatzaile.execSQL(query);
        boolean badago = false;
        try{
           badago = rs.next();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return badago;
    }

}