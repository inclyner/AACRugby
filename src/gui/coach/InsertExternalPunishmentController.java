package gui.coach;
// not done not tested
import java.awt.image.AreaAveragingScaleFilter;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.Game;
import logic.Practise;

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

        Main main = null;
        try {
            main = new Main();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Stage stage = main.getStg();
        stage.setResizable(false);
        stage.setWidth(620);
        stage.setHeight(510);

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

