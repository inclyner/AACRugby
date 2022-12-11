package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UserPersonalDataController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tfAge;

    @FXML
    private DatePicker tfBirthDate;

    @FXML
    private TextField tfCC;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPhoneNumber;

    @FXML
    void onClickBtnBack(ActionEvent event) {
        try{
            Main main = new Main();
            int typeOfUser = main.getModelManager().checksTypeUser(main.getModelManager().getEmailLogged());
            // doctor
            if(typeOfUser == 1) {
                main.changeScene("doctor\\DoctorMainView.fxml");
            }
            // coach
            else if(typeOfUser == 3){
                main.changeScene("coach\\CoachMainView.fxml");
            }
            // manager
            else if(typeOfUser == 4){
                main.changeScene("manager\\ManagerMainView.fxml");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClivkBtnSave(ActionEvent event) {

    }

    @FXML
    void onSelectBirthDate(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfAge != null : "fx:id=\"tfAge\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfBirthDate != null : "fx:id=\"tfBirthDate\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfCC != null : "fx:id=\"tfCC\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfPhoneNumber != null : "fx:id=\"tfPhoneNumber\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";

    }

}
