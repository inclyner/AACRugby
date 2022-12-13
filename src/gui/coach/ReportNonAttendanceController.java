package gui.coach;
// not done not tested
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Users.Player;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import logic.Game;
import logic.Practise;

public class ReportNonAttendanceController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbPlayer;

    @FXML
    private ComboBox<String> cmbPractice;

    private ArrayList<Practise> PRACTISES = new ArrayList<>();
    private ArrayList<Player> PLAYERS = new ArrayList<>();

    @FXML
    void onClickBtnBack(ActionEvent event) {
        try {
            Main main = new Main();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Canceling Operation");
            alert.setContentText("Are you sure you want do cancel the operation?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK)
                main.changeScene("coach\\CoachMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickBtnSave(ActionEvent event) {
        try {
            Main main = new Main();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insert not attendance about player");
            alert.setContentText("Are you sure you want to insert this?");
            Optional<ButtonType> option = alert.showAndWait();
            String mail = PLAYERS.get(cmbPlayer.getSelectionModel().getSelectedIndex()).getEmail();
            int id= PRACTISES.get(cmbPractice.getSelectionModel().getSelectedIndex()).getId();
            String ncc= main.getModelManager().getNcc(mail);
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                main.getModelManager().getrepportNonAttendance(Long.parseLong(ncc));
                main.changeScene("coach\\CoachMainView.fxml");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<String> getPractises(){
        try {
            Calendar cal = Calendar.getInstance();
            Date dataAtual = cal.getTime();
            Main main = new Main();
            ArrayList<Practise> g = main.getModelManager().getAllPractise();
            ObservableList<String> game = FXCollections.observableArrayList();
            for(Practise p: g){
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(p.getDate());
                if(dataAtual.after(date)) {
                    String name = main.getModelManager().getNameOfPractise(p);
                    game.add(name);
                    PRACTISES.add(p);
                }
            }
            System.out.println(game);
            return game;
        }catch (SQLException | ParseException e){
            System.out.println(e);
            throw new RuntimeException();
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
                PLAYERS.add(p);
            }
            return names;
        }catch (SQLException e){
            throw new RuntimeException();
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

        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'ReportNonAttendanceView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'ReportNonAttendanceView.fxml'.";
        assert cmbPlayer != null : "fx:id=\"cmbPlayer\" was not injected: check your FXML file 'ReportNonAttendanceView.fxml'.";
        cmbPractice.setItems(getPractises());
        cmbPlayer.setItems(getPlayers());
    }

}

