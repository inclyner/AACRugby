package gui.player;
// not done not tested
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.Optional;

public class PlayerPersonalDataController {


    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbAptitude;

    @FXML
    private ComboBox<String> cmbPersonalData;

    @FXML
    private ComboBox<String> cmbPosition;

    @FXML
    private TextField tfAge;

    @FXML
    private DatePicker tfBirthDate;

    @FXML
    private TextField tfCC;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfHeight;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPhoneNumber;

    @FXML
    private TextField tfWeight;

    @FXML
    private ImageView pencilEmail;

    @FXML
    private ImageView pencilPhoneNumber;

    private ObservableList<String> optionsViewPersonalData = FXCollections.observableArrayList(
            "Personal Data","Notes","Diet Information");

    @FXML
    void onClickBtnBack(ActionEvent event) {

        try {
            Main main = new Main();
            if(wereChangesNotSaved()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Canceling Operation");
                alert.setContentText("Are you sure you want do cancel the operation?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.CANCEL)
                    return;
                else if (option.get() == ButtonType.OK)
                    main.changeScene("player\\PlayerMainView.fxml");
            } else
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
    void onClickBtnSave(ActionEvent event) throws SQLException{
        Main main = new Main();
        if(wereChangesNotSaved()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Send request to change data");
            alert.setContentText("Are you sure you want to send a request to change data?");
            Optional<ButtonType> option = alert.showAndWait();
            String userCC = main.getModelManager().getNameUserNcc(tfCC.getText());
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                if (tfEmail.getText() != main.getModelManager().getEmailUserNcc(userCC)) {
                    main.getModelManager().requestChange(main.getModelManager().getEmailUserNcc(userCC),tfEmail.getText());
                }
                if(tfPhoneNumber.getText() != main.getModelManager().getPhoneNumberUserNcc(userCC)) {
                    main.getModelManager().requestChange(main.getModelManager().getPhoneNumberUserNcc(userCC), tfPhoneNumber.getText());
                }
            }
        }
    }

    @FXML
    void onSelectBirthDate(ActionEvent event) {

    }

    @FXML
    void onClickPencilEmail(MouseEvent event) {
        if(!tfEmail.isEditable()){
            tfEmail.setStyle("-fx-border-color: #c1a670");
            tfEmail.setEditable(true);
            tfEmail.setFocusTraversable(true);
        } else {
            tfEmail.setStyle("");
            tfEmail.setEditable(false);
            tfEmail.setFocusTraversable(false);
        }

    }

    @FXML
    void onClickPencilPhone(MouseEvent event) {
        if(!tfPhoneNumber.isEditable()){
            tfPhoneNumber.setStyle("-fx-border-color: #c1a670");
            tfPhoneNumber.setEditable(true);
            tfPhoneNumber.setFocusTraversable(true);
        } else {
            tfPhoneNumber.setStyle("");
            tfPhoneNumber.setEditable(false);
            tfPhoneNumber.setFocusTraversable(false);
        }
    }


    @FXML
    void initialize() throws SQLException {
        cmbPersonalData.setItems(optionsViewPersonalData);
        Main m = new Main();
        String userName = m.getModelManager().getNameUser(m.getModelManager().getEmailLogged());
        tfName.setText(userName);

    }

    private boolean wereChangesNotSaved() {
        try {
            Main main = new Main();
            String userCC = main.getModelManager().getNameUserNcc(tfCC.getText());
            //tfEmail tfPhoneNumber
            if(tfPhoneNumber.getText()!=main.getModelManager().getPhoneNumberUserNcc(userCC)|| tfEmail.getText()!=main.getModelManager().getEmailUserNcc(userCC)){
                return true;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

}
