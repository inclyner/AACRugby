package gui.doctor;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.DateTimeStringConverter;

public class ScheduleAppointmentsController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbPlayers;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField tfTime;

    @FXML
    void onClickBackBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("doctor\\DoctorMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickSaveBtn(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'ScheduleAppointmentsView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'ScheduleAppointmentsView.fxml'.";
        assert cmbPlayers != null : "fx:id=\"cmbPlayers\" was not injected: check your FXML file 'ScheduleAppointmentsView.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'ScheduleAppointmentsView.fxml'.";
        assert tfTime != null : "fx:id=\"tfTime\" was not injected: check your FXML file 'ScheduleAppointmentsView.fxml'.";

        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            tfTime.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
        } catch (ParseException e){
            System.err.println(e);
        }
    }

}

