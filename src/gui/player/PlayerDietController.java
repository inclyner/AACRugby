package gui.player;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;

public class PlayerDietController {

    @FXML
    private ComboBox<String> cmbPersonalData;

    @FXML
    private TextArea tfdietInformation;

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
        cmbPersonalData.setItems(optionsViewPersonalData);
        String ncc=null;
        try {
            ncc = main.getModelManager().getNcc(main.getModelManager().getEmailLogged());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tfdietInformation.setText(main.getModelManager().getDietNotes(Long.parseLong(ncc)));

    }
}
