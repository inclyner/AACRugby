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
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    private ComboBox<String> cmbAptitude;

    private ObservableList<String> AptitudeList = FXCollections.observableArrayList("Fit","Not Fit");

    private ArrayList<Player> PLAYERS = new ArrayList<>();
    private ArrayList<Game> GAMES = new ArrayList<>();

    @FXML
    void onClickBackBtn(ActionEvent event) {
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
    void onClickSaveBtn(ActionEvent event) {
        try {
            Main main = new Main();
            ArrayList<Long> nCC = new ArrayList<>();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ArrayList<Game> games = main.getModelManager().getAllGames();
            if (CheckFields()){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Fields Empty");
                alert1.setContentText("Fill all the fields");
                alert1.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insert notes about player");
            alert.setContentText("Are you sure you want to insert this notes?");
            Optional<ButtonType> option = alert.showAndWait();
            String mail = PLAYERS.get(cmbPlayers.getSelectionModel().getSelectedIndex()).getEmail();
            int id= GAMES.get(cmbGames.getSelectionModel().getSelectedIndex()).getId();
            String ncc= main.getModelManager().getNcc(mail);
            boolean fit=true;
            if (Objects.equals("Not Fit",cmbAptitude.getSelectionModel().getSelectedItem()))
                fit=false;
            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                main.getModelManager().getinsertNotesAboutPlayer(Long.parseLong(ncc), id, tfGameNotes.getText(), fit);
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

    private ObservableList<String> getGames(){
        try {
            Calendar cal = Calendar.getInstance();
            Date dataAtual = cal.getTime();
            Main main = new Main();
            ArrayList<Game> g = main.getModelManager().getAllGames();
            ObservableList<String> game = FXCollections.observableArrayList();
            for(Game p: g){
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(p.getDate());
                if(dataAtual.after(date)) {
                    String name = main.getModelManager().getNameOfGame(p);
                    game.add(name);
                    GAMES.add(p);
                }
            }
            System.out.println(game);
            return game;
        }catch (SQLException | ParseException e){
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    private boolean CheckFields(){
        if (cmbGames.getSelectionModel().getSelectedIndex()==-1 || cmbAptitude.getSelectionModel().getSelectedIndex()==-1 ||
        cmbPlayers.getSelectionModel().getSelectedIndex() ==-1 || tfGameNotes.getText().isEmpty())
            return true;
        return false;
    }

    @FXML
    void initialize() throws SQLException {

        Main main = new Main();

        Stage stage = main.getStg();
        stage.setResizable(false);
        stage.setWidth(620);
        stage.setHeight(510);
        cmbGames.setItems(getGames());
        cmbPlayers.setItems(getPlayers());
        cmbAptitude.setItems(AptitudeList);
    }

}

