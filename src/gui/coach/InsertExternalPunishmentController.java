package gui.coach;
// not done not tested
import java.awt.image.AreaAveragingScaleFilter;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.Game;
import logic.Practise;

public class InsertExternalPunishmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbPlayer;

    @FXML
    private Spinner<Integer> tfNumberGames;

    @FXML
    private TextArea tfPunishmentNote;

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
        try{
            Main main = new Main();
            if (CheckFields()){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Fields Empty");
                alert1.setContentText("Fill all the fields");
                alert1.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insert external punishment about player");
            alert.setContentText("Are you sure you want to insert this?");
            Optional<ButtonType> option = alert.showAndWait();
            String mail = PLAYERS.get(cmbPlayer.getSelectionModel().getSelectedIndex()).getEmail();
            String ncc= main.getModelManager().getNcc(mail);
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                main.getModelManager().getInsertPlayersPunishement(Long.parseLong(ncc), tfPunishmentNote.getText(), tfNumberGames.getValue());
                main.changeScene("coach\\CoachMainView.fxml");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        //assert cmbPlayer != null : "fx:id=\"cmbPlayer\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        cmbPlayer.setItems(getPlayers());
        assert tfNumberGames != null : "fx:id=\"tfNumberGames\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        assert tfPunishmentNote != null : "fx:id=\"tfPunishmentNote\" was not injected: check your FXML file 'InsertExternalPunishmentView.fxml'.";
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20);

        valueFactory.setValue(1);
        tfNumberGames.setValueFactory(valueFactory);
    }

}

