package gui.doctor;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

public class InsertDietController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbPlayers;

    @FXML
    private TextArea tfDiet;

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
        String title = null;
        String message = null;
        boolean error = false;
        try{
            Main main = new Main();
            if(tfDiet.getText().isEmpty() ||
            cmbPlayers.getSelectionModel().getSelectedItem()==null){
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
            throw new RuntimeException();
        }
    }

    @FXML
    void initialize() {
        cmbPlayers.setItems(getPlayers());
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'InsertDietView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'InsertDietView.fxml'.";
        assert cmbPlayers != null : "fx:id=\"cmbPlayers\" was not injected: check your FXML file 'InsertDietView.fxml'.";
        assert tfDiet != null : "fx:id=\"tfDiet\" was not injected: check your FXML file 'InsertDietView.fxml'.";

    }

}

