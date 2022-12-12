package gui.player;
// not done not tested
import Utils.Utils;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import static Utils.Utils.calculateAge;

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
    private boolean clicked = false;

    private ObservableList<String> optionsViewPersonalData = FXCollections.observableArrayList(
            "Personal Data","Notes","Diet Information");

    @FXML
    void onClickBtnBack(ActionEvent event) throws SQLException {

            Main main = new Main();
            if(clicked){
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

        }


    @FXML
    void onSelectTypeOfData(ActionEvent event) throws SQLException {

            Main main = new Main();
            String optionSelected = cmbPersonalData.getSelectionModel().getSelectedItem();

            if(optionSelected.equals("Personal Data")){
                main.changeScene("player\\PlayerPersonalDataView.fxml");
            } else if(optionSelected.equals("Notes")){
                main.changeScene("player\\PlayerNotesView.fxml");
            } else if(optionSelected.equals("Diet Information")){
                main.changeScene("player\\PlayerDietView.fxml");
            }


    }

    @FXML
    void onClickBtnSave(ActionEvent event) throws SQLException{
        Main main = new Main();
        if(clicked){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Send request to change data");
            alert.setContentText("Are you sure you want to send a request to change data?");
            Optional<ButtonType> option = alert.showAndWait();
            String userCC = tfCC.getText();
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                if (!Objects.equals(tfEmail.getText(), main.getModelManager().getEmailUserNcc(userCC))) {
                    main.getModelManager().requestChange(main.getModelManager().getEmailUserNcc(userCC),tfEmail.getText(), Long.parseLong(userCC));
                }
                if(!Objects.equals(tfPhoneNumber.getText(), main.getModelManager().getPhoneNumberUserNcc(userCC))) {
                    main.getModelManager().requestChange(main.getModelManager().getPhoneNumberUserNcc(userCC), tfPhoneNumber.getText(),Long.parseLong(userCC));
                }
            }
        }
    }

    @FXML
    void onSelectBirthDate(ActionEvent event) {}

    @FXML
    void onClickPencilEmail(MouseEvent event) {
        clicked=true;
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
        clicked=true;
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
        clicked=false;
        cmbPersonalData.setItems(optionsViewPersonalData);
        Main m = new Main();
        LocalDate currentDate;
        String nCC= m.getModelManager().getNcc(m.getModelManager().getEmailLogged());

        tfEmail.setText(m.getModelManager().getEmailLogged());
        tfName.setText(m.getModelManager().getNameUser(m.getModelManager().getEmailLogged()));
        tfPhoneNumber.setText(m.getModelManager().getPhoneNumberUserNcc(nCC));
        tfCC.setText(nCC);

        String dateString=m.getModelManager().getBirthDatenCC(nCC);
        DatePicker datePicker = new DatePicker();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        tfBirthDate.setValue(LocalDate.parse(dateString, formatter));
        tfAge.setText(String.valueOf(Utils.calculateAge(Utils.getDateAsLocalDate(String.valueOf(tfBirthDate.getValue())), Utils.getCurrentDate())));
        tfHeight.setText(m.getModelManager().getHeightnCC(nCC));
        tfWeight.setText(m.getModelManager().getWeightnCC(nCC));
        cmbPosition.setValue(m.getModelManager().getpositionnCC(nCC));
        cmbAptitude.setValue(m.getModelManager().getAptitudenCC(nCC));

    }


}
