package gui.coach;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InsertExternalPunishmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<?> cmbPlayer;

    @FXML
    private Spinner<Integer> tfNumberGames;

    @FXML
    private TextArea tfPunishmentNote;

    @FXML
    void onClickBtnBack(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\CoachMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickBtnSave(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        assert cmbPlayer != null : "fx:id=\"cmbPlayer\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        assert tfNumberGames != null : "fx:id=\"tfNumberGames\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        assert tfPunishmentNote != null : "fx:id=\"tfPunishmentNote\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20);

        valueFactory.setValue(1);
        tfNumberGames.setValueFactory(valueFactory);
    }

}
