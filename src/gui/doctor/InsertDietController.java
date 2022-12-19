package gui.doctor;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Users.Player;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.Game;

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Canceling Operation");
            alert.setContentText("Are you sure you want do cancel the operation?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK)
                main.changeScene("doctor\\DoctorMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickSaveBtn(ActionEvent event) {
        try{
            Main main = new Main();
            ArrayList<Long> nCC = new ArrayList<>();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            if (CheckFields()){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Fields Empty");
                alert1.setContentText("Fill all the fields");
                alert1.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insert diet for player");
            alert.setContentText("Are you sure you want to insert this diet?");
            Optional<ButtonType> option = alert.showAndWait();
            int indexp=cmbPlayers.getSelectionModel().getSelectedIndex();
            Long ncc=players.get(indexp).getnCC(players.get(indexp).getEmail());
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                main.getModelManager().getinsertDiet(ncc, tfDiet.getText());
                main.changeScene("doctor\\DoctorMainView.fxml");
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

    private boolean CheckFields(){
        if (cmbPlayers.getSelectionModel().getSelectedIndex() ==-1 || tfDiet.getText().isEmpty())
            return true;
        return false;
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

        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'InsertDietView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'InsertDietView.fxml'.";
        assert cmbPlayers != null : "fx:id=\"cmbPlayers\" was not injected: check your FXML file 'InsertDietView.fxml'.";
        assert tfDiet != null : "fx:id=\"tfDiet\" was not injected: check your FXML file 'InsertDietView.fxml'.";

    }

}

