package ehu.isad.controller.ui;

import ehu.isad.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainKud implements Initializable {

  // Reference to the main application.
  private Main mainApp;

  public void setMainApp(Main main) {
    this.mainApp = mainApp;
  }

  @FXML
  public void onClick(ActionEvent actionEvent) {

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

}
