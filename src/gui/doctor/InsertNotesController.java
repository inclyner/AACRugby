package gui.doctor;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Users.Player;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.MedicalAppointment;

public class InsertNotesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbAptitude;

    @FXML
    private ComboBox<String> cmbPlayers;

    @FXML
    private TextArea tfNotes;

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
    void onClickSaveBtn(ActionEvent event){
        String title = null;
        String message = null;
        boolean error = false;
        try{
            Main main = new Main();
            if(cmbAptitude.getSelectionModel().getSelectedItem()==null ||
            cmbPlayers.getSelectionModel().getSelectedItem()==null ||
            tfNotes.getText().isEmpty()){
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

        }catch(SQLException e){
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

    private ObservableList<String> AptitudeList = FXCollections.observableArrayList(
            "Fit","Not Fit");
    @FXML
    void initialize() {
        cmbPlayers.setItems(getPlayers());
        cmbAptitude.setItems(AptitudeList);

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

        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'InsertNotesView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'InsertNotesView.fxml'.";
        assert cmbAptitude != null : "fx:id=\"cmbAptitude\" was not injected: check your FXML file 'InsertNotesView.fxml'.";
        assert cmbPlayers != null : "fx:id=\"cmbPlayers\" was not injected: check your FXML file 'InsertNotesView.fxml'.";

    }

}

