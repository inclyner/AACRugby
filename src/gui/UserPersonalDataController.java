package gui;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    void onClickBtnSave(ActionEvent event) {

    }

    @FXML
    void onSelectBirthDate(ActionEvent event) {

    }

    @FXML
    void initialize() throws SQLException{

        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfAge != null : "fx:id=\"tfAge\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfBirthDate != null : "fx:id=\"tfBirthDate\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfCC != null : "fx:id=\"tfCC\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";
        assert tfPhoneNumber != null : "fx:id=\"tfPhoneNumber\" was not injected: check your FXML file 'UserPersonalDataView.fxml'.";



        Main m = new Main();
        String nCC=m.getModelManager().getNcc(m.getModelManager().getEmailLogged());
        tfEmail.setText(m.getModelManager().getEmailLogged());
        tfName.setText(m.getModelManager().getNameUser(m.getModelManager().getEmailLogged()));
        tfPhoneNumber.setText(m.getModelManager().getPhoneNumberUserNcc(nCC));
        tfCC.setText(nCC);

        String dateString=m.getModelManager().getBirthDatenCC(nCC);
        DatePicker datePicker = new DatePicker();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        tfBirthDate.setValue(LocalDate.parse(dateString, formatter));
        tfAge.setText(String.valueOf(Utils.calculateAge(Utils.getDateAsLocalDate(String.valueOf(tfBirthDate.getValue())), Utils.getCurrentDate())));

        Stage stage = m.getStg();
        stage.setResizable(false);
        stage.setWidth(620);
        stage.setHeight(510);

    }

}
