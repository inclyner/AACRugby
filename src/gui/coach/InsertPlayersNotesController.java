package gui.coach;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InsertPlayersNotesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbGames;

    @FXML
    private ComboBox<String> cmbPlayers;

    @FXML
    private TextArea tfGameNotes;

    @FXML
    void onClickBackBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\CoachMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickSaveBtn(ActionEvent event) {

    }

    @FXML
    void onSelectGames(ActionEvent event) {

    }

    @FXML
    void onSelectPlayers(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";
        assert cmbGames != null : "fx:id=\"cmbGames\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";
        assert cmbPlayers != null : "fx:id=\"cmbPlayers\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";
        assert tfGameNotes != null : "fx:id=\"tfGameNotes\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";

    }

}

