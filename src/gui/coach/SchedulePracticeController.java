package gui.coach;
// not done not tested
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import Users.Player;
import gui.Main;
import gui.manager.TableDeleteSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    void onClickBtnBack(ActionEvent event) throws SQLException {
        Main main = new Main();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Canceling Operation");
        alert.setContentText("Are you sure you want do cancel the operation?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.CANCEL)
            return;
        else if (option.get() == ButtonType.OK)
            main.changeScene("coach\\CoachMainView.fxml");
    }

    @FXML
    void onClickSaveBtn(ActionEvent event) throws SQLException, ParseException {
        Main main = new Main();
        ArrayList<Long> nCC = new ArrayList<>();
        if (CheckFields() && !isAnyUserSelected()){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Fields Empty");
            alert1.setContentText("Fill all the fields");
            alert1.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Schedule Practice?");
        alert.setContentText("Are you sure you want to schedule this practice?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.CANCEL)
            return;
        else if (option.get() == ButtonType.OK){

            for(TableCallUpGame tb : tableCallUp.getItems()){
                if(tb.getCheckBox().isSelected()){
                    Long n = Long.valueOf(main.getModelManager().getNcc(tb.getEmail()));
                    nCC.add(n);
                }
            }
            String r = main.getModelManager().schedulePractices(nCC, tfLocal.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) ,tfBeginTime.getText(), tfEndTime.getText());
            if(r.equals("Operation Successfull")){
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setContentText(r);
                alert2.showAndWait();
                main.changeScene("coach\\CoachMainView.fxml");
            }else{
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle(r);
                alert2.showAndWait();
            }
        }
    }

    //Nota select all nao funciona
    @FXML
    void onSelectchbAllPlayers(ActionEvent event) {
        if(chbSelectAll.isSelected()){
            for(TableCallUpGame table : tableCallUp.getItems()){
                table.getCheckBox().setSelected(true);
            }
        } else {
            for(TableCallUpGame table : tableCallUp.getItems()){
                table.getCheckBox().setSelected(false);
            }
        }
    }

    private boolean CheckFields(){
        if (tfBeginTime.getText().isEmpty() || tfEndTime.getText().isEmpty() || tfLocal.getText().isEmpty()
                || datePicker.getValue()==null)
            return true;
        return false;
    }
    @FXML
    void initialize()  {

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
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        //email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableCallUp.setItems(getPlayers());

    }

    private ObservableList<TableCallUpGame> getPlayers(){
        try {
            Main main = new Main();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ObservableList<TableCallUpGame> names = FXCollections.observableArrayList();
            for(Player p: players){
                String name = main.getModelManager().getNameUser(p.getEmail());
                TableCallUpGame tab = new TableCallUpGame(name, p.getEmail());
                names.add(tab);
            }
            return names;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private boolean isAnyUserSelected(){
        for(TableCallUpGame tb : tableCallUp.getItems()){
            if(tb.getCheckBox().isSelected()){
                return true;
            }
        }
        return false;
    }

}
