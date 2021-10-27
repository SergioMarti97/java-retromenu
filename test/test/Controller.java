package test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import olcPGEApproach.GameContainer;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView imgView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameContainer gc = new GameContainer(new RetroMenuGame(), (int)imgView.getFitWidth(), (int)imgView.getFitHeight(), imgView);
        imgView.setImage(gc.getImg());
        gc.getTimer().start();
    }

}
