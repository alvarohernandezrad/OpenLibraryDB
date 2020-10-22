package ehu.isad.controller.ui;


import ehu.isad.Book;
import ehu.isad.Liburuak;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
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
        Book book = (Book)comboLiburuak.getValue();
        mainApp.mainErakutsi();
        mainApp.xehetasunakErakutsi();

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
