package gui.player;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class PlayerMainController {
    @FXML
    private ComboBox<?> cmbCalendar;
    @FXML
    private Label lbHello;

    @FXML
    private ImageView imgAccount;

    private ObservableList<String> options = FXCollections.observableArrayList(
            "Month","Year");


    @FXML
    void onSelectCmbCalendar(ActionEvent event) {

    }
    @FXML
    void onClickAccountProfile(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("player\\PlayerPersonalDataView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        try {
            Main main = new Main();
            lbHello.setText("Hello " + main.getModelManager().getNameLogged() + "!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
