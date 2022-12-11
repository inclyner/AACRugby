package gui.coach;
// not done not tested
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ReportNonAttendanceController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbPlayer;

    @FXML
    private ComboBox<String> cmbPractice;

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
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'ReportNonAttendanceView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'ReportNonAttendanceView.fxml'.";
        assert cmbPlayer != null : "fx:id=\"cmbPlayer\" was not injected: check your FXML file 'ReportNonAttendanceView.fxml'.";
        assert cmbPractice != null : "fx:id=\"cmbPractice\" was not injected: check your FXML file 'ReportNonAttendanceView.fxml'.";

    }

}

