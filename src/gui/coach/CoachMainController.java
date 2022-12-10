package gui.coach;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CoachMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCallUpPlayers;

    @FXML
    private Button btnInsertNotes;

    @FXML
    private Button btnPunishment;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnSchedulePractice;

    @FXML
    private ImageView imageAccount;

    @FXML
    private Label lbHello;

    @FXML
    void onClickAccountImage(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("UserPersonalDataView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickCallUpBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\CallUpPlayersView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickInsertNotes(ActionEvent event) {

    }

    @FXML
    void onClickPunishmentBtn(ActionEvent event) {

    }

    @FXML
    void onClickReportBtn(ActionEvent event) {

    }

    @FXML
    void onClickScheduleBtn(ActionEvent event) {

    }

    @FXML
    void initialize() {
        try {
            Main main = new Main();
            lbHello.setText("Hello coach " + main.getModelManager().getNameLogged() +"!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

