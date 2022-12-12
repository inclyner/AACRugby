package gui.coach;
// not done not tested
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Users.Player;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.Game;

public class InsertPlayersNotesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbGames;

    @FXML
    private ComboBox<String> cmbPlayers;

    @FXML
    private TextArea tfGameNotes;

    @FXML
    void onClickBackBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\CoachMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickSaveBtn(ActionEvent event) {
        try {
            System.out.println(tfGameNotes.getText()+"w");
            Main main = new Main();
            ArrayList<Long> nCC = new ArrayList<>();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ArrayList<Game> games = main.getModelManager().getAllGames();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insert notes about player");
            alert.setContentText("Are you sure you want to insert this notes?");
            Optional<ButtonType> option = alert.showAndWait();
            int indexp=cmbPlayers.getSelectionModel().getSelectedIndex();
            int indexg=cmbGames.getSelectionModel().getSelectedIndex();
            Long ncc=players.get(indexp).getnCC(players.get(indexp).getEmail());
            int id=games.get(indexg).getId();
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                main.getModelManager().getinsertNotesAboutPlayer(ncc, id, tfGameNotes.getText(), true);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSelectGames(ActionEvent event) {

    }

    @FXML
    void onSelectPlayers(ActionEvent event) {

    }

    private ObservableList<String> getPlayers(){
        try {
            Main main = new Main();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ObservableList<String> names = FXCollections.observableArrayList();
            for(Player p: players){
                String name = main.getModelManager().getNameUser(p.getEmail());
                names.add(name);
            }
            return names;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private ObservableList<String> getGames(){
        try {
            Main main = new Main();
            ArrayList<Game> games = main.getModelManager().getAllGames();
            ObservableList<String> game = FXCollections.observableArrayList();
            for(Game p: games){
                String name = main.getModelManager().getNameOfGame(p);
                game.add(name);
            }
            return game;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";
        //assert cmbGames != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";
        cmbGames.setItems(getGames());
        cmbPlayers.setItems(getPlayers());
        assert tfGameNotes != null : "fx:id=\"tfGameNotes\" was not injected: check your FXML file 'InsertPlayersNotesView.fxml'.";

    }

}

