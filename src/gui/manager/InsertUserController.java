package gui.manager;
// done and tested
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;

import javax.mail.MessagingException;
import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import Utils.Utils;
import javafx.stage.Stage;

public class InsertUserController {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;
    @FXML
    private CheckBox chbFemale;
    @FXML
    private CheckBox chbMale;
    @FXML
    private ComboBox<String> cmbAptitude;
    @FXML
    private ComboBox<String> cmbPosition;
    @FXML
    private ComboBox<String> cmbTypeUser;
    @FXML
    private Label labelAptitude;

    @FXML
    private Label labelHeight;

    @FXML
    private Label labelPosition;

    @FXML
    private Label labelWeight;
    @FXML
    private TextField tfAge;
    @FXML
    private DatePicker tfBirthDate;
    @FXML
    private TextField tfCitizenCardNumber;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfHeight=null;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private TextField tfWeight=null;

    private String sexSelected;
    private ObservableList<String> typeOfUsersList = FXCollections.observableArrayList(
            "Doctor", "Player", "Coach");
    private ObservableList<String> AptitudeList = FXCollections.observableArrayList(
            "Fit","Not Fit");
    private ObservableList<String> PositionsList = FXCollections.observableArrayList(
            "Wing","Centre", "Fly-Half",
            "Scrum-Half","Number Eight", "Flanker",
            "Hooker","Prop");

    @FXML
    void onCreateBtnClick(ActionEvent event) {
        String tittle = null;
        String message = null;
        boolean error = false;
        int age = 0;
        if(!tfAge.getText().isEmpty())
            age = Integer.parseInt(tfAge.getText());
        try {
            Main main = new Main();
            if(cmbTypeUser.getSelectionModel().getSelectedItem() == null ||
                    (!chbMale.isSelected() && !chbFemale.isSelected()) ||
                    tfName.getText().isEmpty() ||
                    tfBirthDate.getValue() == null ||
                    tfEmail.getText().isEmpty() ||
                    tfAge.getText().isEmpty() ||
                    tfCitizenCardNumber.getText().isEmpty() ||
                    tfPhoneNumber.getText().isEmpty() ||
                    tfPassword.getText().isEmpty()
            ){
                error = true;
                tittle = "Missing data!";
                message = "You must fill all the required fields";

            }
            else if(cmbTypeUser.getSelectionModel().getSelectedItem().equals("Player") &&
                    (tfHeight.getText().isEmpty() || tfWeight.getText().isEmpty() ||
                            cmbPosition.getSelectionModel().getSelectedItem() == null ||
                            cmbAptitude.getSelectionModel().getSelectedItem() == null))
            {
                error = true;
                tittle = "Missing data!";
                message = "You must fill all the required fields!";
            }

            else if(age < 15 ||
                    (Utils.calculateAge(
                            Utils.getDateAsLocalDate(String.valueOf(tfBirthDate.getValue())),
                            Utils.getCurrentDate())
                    ) < 15)
            {
                error = true;
                tittle = "Invalid Age!";
                message = "The user must be at least 15 years old!";
            }

            if(error){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(tittle);
                alert.setContentText(message);
                alert.showAndWait();
            } else {
                if(chbFemale.isSelected())
                    sexSelected= "Female";
                else
                    sexSelected="Male";
                String r = main.getModelManager().insertUser(cmbTypeUser.getSelectionModel().getSelectedIndex()+1, tfCitizenCardNumber.getText(), tfName.getText(), tfEmail.getText() ,tfPassword.getText(), sexSelected, tfBirthDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), tfPhoneNumber.getText(), cmbAptitude.getSelectionModel().getSelectedItem(), tfHeight.getText(), tfWeight.getText(), cmbPosition.getSelectionModel().getSelectedItem());
                if(r.equals("User is now in the System")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Great");
                    alert.setContentText(r);
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Info");
                    alert.setContentText(r);
                    alert.showAndWait();
                }
                //main.changeScene("manager\\InsertUserView.fxml");
            }


        } catch (SQLException e){
            System.out.println("Aqui" + e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnSelectTypeOfUser(ActionEvent event) {
        String selectedType = cmbTypeUser.getSelectionModel().getSelectedItem().toString();
        //System.out.println(selectedType);

        if(selectedType.equals("Player")){

            tfWeight.setDisable(false);
            tfHeight.setDisable(false);
            cmbAptitude.setDisable(false);
            cmbPosition.setDisable(false);

            labelAptitude.setText("*");
            labelHeight.setText("*");
            labelWeight.setText("*");
            labelPosition.setText("*");
        } else {
            tfWeight.setDisable(true);
            tfHeight.setDisable(true);
            cmbAptitude.setDisable(true);
            cmbPosition.setDisable(true);

            tfWeight.setText(null);
            tfHeight.setText(null);

            labelAptitude.setText(null);
            labelHeight.setText(null);
            labelWeight.setText(null);
            labelPosition.setText(null);
        }
    }


    @FXML
    void onCancelBtnClick(ActionEvent event) {
        try {
            Main main = new Main();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Canceling Operation");
            alert.setContentText("Are you sure you want do cancel the operation?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get() == null)
                return;
            else if(option.get() == ButtonType.CANCEL)
                return;
            else if(option.get() == ButtonType.OK)
                main.changeScene("manager\\ManagerMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        if(cmbTypeUser.getSelectionModel().getSelectedItem().isEmpty() ||
                (!chbMale.isSelected()) && !chbFemale.isSelected() ||
                tfName.getText().isEmpty() ||
                tfBirthDate.getValue() == null ||
                tfEmail.getText().isEmpty() ||
                tfAge.getText().isEmpty() ||
                tfCitizenCardNumber.getText().isEmpty() ||
                tfPhoneNumber.getText().isEmpty() ||
                tfPassword.getText().isEmpty()
        ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Canceling Operation");
                alert.setContentText("Are you sure you want do cancel the operation?");
                alert.showAndWait();
        }
*/


    }


    @FXML
    void onSelectBirthDate(ActionEvent event) {
        LocalDate currentDate = Utils.getCurrentDate();
        LocalDate birthAsDate = Utils.getDateAsLocalDate(String.valueOf(tfBirthDate.getValue()));
        int age = Utils.calculateAge(birthAsDate,currentDate);

        tfAge.setText(Integer.toString(age));
    }

    @FXML
    void onSelectFemaleChb(ActionEvent event) {
        if(chbMale.isSelected()){
            chbMale.setSelected(false);
        }
    }

    @FXML
    void onSelectMaleChb(ActionEvent event) {
        if(chbFemale.isSelected()){
            chbFemale.setSelected(false);
        }
    }

    @FXML
    void initialize() {
        try{
            Main main = new Main();
            Stage stage = main.getStg();
            stage.setResizable(false);
            stage.setWidth(620);
            stage.setHeight(510);

            tfWeight.setDisable(true);
            tfHeight.setDisable(true);
            cmbAptitude.setDisable(true);
            cmbPosition.setDisable(true);

            cmbTypeUser.setItems(typeOfUsersList);
            cmbAptitude.setItems(AptitudeList);
            cmbPosition.setItems(PositionsList);

            labelAptitude.setText(null);
            labelHeight.setText(null);
            labelWeight.setText(null);
            labelPosition.setText(null);
        } catch (SQLException e){
            System.err.println(e);
        }

    }

}

