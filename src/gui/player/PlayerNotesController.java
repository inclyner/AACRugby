package gui.player;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;

public class PlayerNotesController {

    @FXML
    private ComboBox<String> cmbPersonalData;
    @FXML
    private Button btnBack;


    private ObservableList<String> optionsViewPersonalData = FXCollections.observableArrayList(
            "Personal Data","Notes","Diet Information");


    @FXML
    void onClickBackBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("player\\PlayerMainView.fxml");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSelectTypeOfData(ActionEvent event) {
        try{
            Main main = new Main();
            String optionSelected = cmbPersonalData.getSelectionModel().getSelectedItem();

            if(optionSelected.equals("Personal Data")){
                main.changeScene("player\\PlayerPersonalDataView.fxml");
            } else if(optionSelected.equals("Notes")){
                main.changeScene("player\\PlayerNotesView.fxml");
            } else if(optionSelected.equals("Diet Information")){
                main.changeScene("player\\PlayerDietView.fxml");
            }

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void initialize() {
        cmbPersonalData.setItems(optionsViewPersonalData);
    }
}
