package gui.doctor;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Users.Player;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Canceling Operation");
            alert.setContentText("Are you sure you want do cancel the operation?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK)
                main.changeScene("coach\\DoctorMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickSaveBtn(ActionEvent event) {
        String title = null;
        String message = null;
        boolean error = false;
        try{
            Main main = new Main();
            if(cmbPlayers.getSelectionModel().getSelectedItem()==null ||
                    tfTime.getText().isEmpty()){
                error=true;
                title = "Missing data!";
                message = "You must fill all the required fields";
            }
            if(error){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(title);
                alert.setContentText(message);
                alert.showAndWait();
            }

            Long nCC = main.getModelManager().getNCccName(cmbPlayers.getValue());
            String r =main.getModelManager().insertMedicalAppointment(nCC, datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), tfTime.getText());
            if(r.equals("Medical Appointment inserted in database")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(r);
                alert.showAndWait();
                main.changeScene("doctor\\DoctorMainView.fxml");
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(r);
                alert.showAndWait();
            }

        }catch(SQLException | ParseException e){
            throw new RuntimeException(e);
        }
    }

    private ObservableList<String> getPlayers(){
        try {
            Main main = new Main();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ObservableList<String> names = FXCollections.observableArrayList();
            for(Player p: players){
                String name = main.getModelManager().getNameUser(p.getEmail());
                names.add(name);
            }
            return names;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        cmbPlayers.setItems(getPlayers());

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

