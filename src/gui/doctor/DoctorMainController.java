package gui.doctor;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DoctorMainController {

    @FXML
    private Label lbHello;
    @FXML
    private ImageView accountImage;

    @FXML
    private Button btnAppointments;

    @FXML
    private Button btnInnsertNotes;

    @FXML
    private Button btnInsertDiet;

    @FXML
    void onClickAccountIcon(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("UserPersonalDataView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickAppointmentsBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("doctor\\ScheduleAppointmentsView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onClickInsertDietBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("doctor\\InsertDietView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickInsertNotesBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("doctor\\InsertNotesView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        assert btnAppointments != null : "fx:id=\"btnAppointments\" was not injected: check your FXML file 'DoctorMainView.fxml'.";
        assert btnInnsertNotes != null : "fx:id=\"btnInnsertNotes\" was not injected: check your FXML file 'DoctorMainView.fxml'.";
        assert btnInsertDiet != null : "fx:id=\"btnInsertDiet\" was not injected: check your FXML file 'DoctorMainView.fxml'.";

        try {
            Main main = new Main();
            lbHello.setText("Hello doctor " + main.getModelManager().getNameLogged() +"!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
