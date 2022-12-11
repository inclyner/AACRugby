package gui.coach;
// not done not tested
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.DateTimeStringConverter;

public class SchedulePracticeController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox chbSelectAll;

    @FXML
    private TableView<TableCallUpGame> tableCallUp;
    @FXML
    private TableColumn<TableCallUpGame, CheckBox> checkBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<TableCallUpGame, String> name;

    @FXML
    private TextField tfBeginTime;

    @FXML
    private TextField tfEndTime;

    @FXML
    private TextField tfLocal;

    @FXML
    void onClickBtnBack(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\CoachMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickSaveBtn(ActionEvent event) {

    }

    @FXML
    void initialize()  {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";
        assert chbSelectAll != null : "fx:id=\"chbSelectAll\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";
        assert checkBox != null : "fx:id=\"checkBox\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";
        assert tfBeginTime != null : "fx:id=\"tfBeginTime\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";
        assert tfEndTime != null : "fx:id=\"tfEndTime\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";
        assert tfLocal != null : "fx:id=\"tfLocal\" was not injected: check your FXML file 'SchedulePracticeView.fxml'.";

        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            tfBeginTime.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
            tfEndTime.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
        } catch (ParseException e){
            System.err.println(e);
        }
    }

}
