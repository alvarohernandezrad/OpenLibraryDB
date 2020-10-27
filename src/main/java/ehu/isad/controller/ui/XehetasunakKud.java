package ehu.isad.controller.ui;

import ehu.isad.Liburuak;

import ehu.isad.controller.db.AukerakKud;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class XehetasunakKud implements Initializable {

  private Liburuak mainApp;


  public void setMainApp(Liburuak main){
    this.mainApp = main;
  }

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Label lbl_xehetasunak;

  @FXML
  private Label lbl_izenburua;

  @FXML
  private Label lbl_argitaletxea;

  @FXML
  private Label lbl_orriKop;

  @FXML
  private Label lbl_irudia;

  @FXML
  private Button btn_atzera;

  @FXML
  private Text txt_izenburua;

  @FXML
  private Text txt_argitaletxea;

  @FXML
  private Text txt_orriKop;

  @FXML
  private ImageView irudia;


  @FXML
  void onClick(ActionEvent event) {
    mainApp.mainErakutsi();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources){
    txt_izenburua.setWrappingWidth(500);
  }

  public void datuakErakutsi(String isbn){
    String book = AukerakKud.getInstance().lortuDatuak(isbn); //liburuaren isbn-arekin datuak jaitsiko ditugu datu basetik
    String[] zatitu = book.split(","); //datuak ',' batekin daude banatuta. Lehenik izenburua, gero orri kopurua eta azkenik irudiaren izena.
    txt_izenburua.setText(zatitu[0]);
    txt_orriKop.setText(String.valueOf(zatitu[1]));
    List<String> argitaletxeak = AukerakKud.getInstance().lortuArgitaletxeak(isbn); //berdina egingo dugu argitaletxeekin. List<String> batean gordeko ditugu
    for(int i=0; i<argitaletxeak.size(); i++){
      //txt_argitaletxea.setText(txt_argitaletxea.getText()+", "+argitaletxeak.get(i)); //buelta bakoitzeko argitaletxea kargatuko du. Gauza da getText-ak beste iburu batentzat erabilitako argitaletzea jasoko duela. Zeozer egin beharko litzateke berreraziarazteko
      txt_argitaletxea.setText(argitaletxeak.get(i));
    }
    this.irudiaKargatu(zatitu[2]);
  }

  private void irudiaKargatu(String irudiIzena){
    String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+irudiIzena+".png";
    try{
      irudia.setImage(new Image(new FileInputStream(imagePath)));
    }catch(FileNotFoundException fileNotFoundException){
      fileNotFoundException.printStackTrace();
    }
  }

}
