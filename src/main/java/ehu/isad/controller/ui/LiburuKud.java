package ehu.isad.controller.ui;


import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.controller.db.AukerakKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    // Reference to the main application.
    private Liburuak mainApp;

    @FXML
    private ComboBox comboLiburuak;

    @FXML
    private TextField lblHautatu;

    @FXML
    private Button btnIkusi;

    public void setMainApp(Liburuak main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent actionEvent) {
       if(comboLiburuak.getValue()!=null){ //combo boxean aukeraren bat hartzen badugu
           String isbn = AukerakKud.getInstance().lortuIsbn((String)comboLiburuak.getValue()); //liburuaren izena izanda liburu honen isbn-a lortu datu basetik
           if(!AukerakKud.getInstance().liburuaDago((String)comboLiburuak.getValue())){ //datu basean liburua jada kargatuta dagoen begiratuko dugu
               AukerakKud.getInstance().liburuaDatuBaseraIgo(isbn);
               System.out.println("Kargatuta");
           }
           mainApp.xehetasunakErakutsi(isbn);
       }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) { //hasieran liburuen izenburua eta isbna kargtuko ditugu datu basean
        List<String> listaLiburuak = AukerakKud.getInstance().lortuLiburuak();
        ObservableList<String> liburuak = FXCollections.observableArrayList(listaLiburuak);
        comboLiburuak.setItems(liburuak);
    }
}
