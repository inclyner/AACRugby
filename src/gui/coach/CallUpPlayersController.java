package gui.coach;
//not done not Tested
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Users.Player;
import gui.Main;
import gui.manager.TableDeleteSetter;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.Game;

public class CallUpPlayersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<TableCallUpGame, CheckBox> callToGame;

    @FXML
    private TableColumn<TableCallUpGame, String> name;

    @FXML
    private TableColumn<TableCallUpGame, String> email;

    @FXML
    private TableView<TableCallUpGame> tableViewCallUpPlayers;

    @FXML
    private CheckBox chbSelectAllPlayers;

    @FXML
    private ComboBox<String> cmbGame;

    @FXML
    void onClickBtnBack(ActionEvent event) {
        try {
            Main main = new Main();

            if(isAnyUserSelected()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Canceling Operation");
                alert.setContentText("Are you sure you want do cancel the operation?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.CANCEL)
                    return;
                else if (option.get() == ButtonType.OK)
                    main.changeScene("coach\\CoachMainView.fxml");
            } else
                main.changeScene("coach\\CoachMainView.fxml");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*try {
            Main main = new Main();
            main.changeScene("coach\\CoachMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @FXML
    void onClickBtnSave(ActionEvent event) {
        try {
            Main main = new Main();
            ArrayList<Long> nCC = new ArrayList<>();
            ArrayList<Game> games = main.getModelManager().getAllGames();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Call up Players");
            alert.setContentText("Are you sure you want to call up this players?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                for(TableCallUpGame tb : tableViewCallUpPlayers.getItems()){
                    if(tb.getCheckBox().isSelected()){
                        nCC.add(Long.parseLong(main.getModelManager().getNcc(tb.getEmail())));
                    }
                }
                String a = main.getModelManager().callup(nCC, 1);
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                if (!a.equals("Insert date about call up in the database")) {
                    alert1.setTitle("Call up");
                    alert1.setContentText(a);
                    alert1.showAndWait();
                }
                else{
                    main.changeScene("coach\\CoachMainView.fxml");
                }
                //tableViewCallUpPlayers.setItems(getTable());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSelectchbAllPlayers(ActionEvent event) {
        if(chbSelectAllPlayers.isSelected()){

            for(TableCallUpGame table : tableViewCallUpPlayers.getItems()){
                table.getCheckBox().setSelected(true);
            }
        } else {
            for(TableCallUpGame table : tableViewCallUpPlayers.getItems()){
                table.getCheckBox().setSelected(false);
            }
        }
    }

    private ObservableList<String> getGames(){
        try {
            Calendar cal = Calendar.getInstance();
            Date dataAtual = cal.getTime();
            cal.add(Calendar.DATE, -3);
            Date daysbefore = cal.getTime();
            Main main = new Main();
            ArrayList<Game> g = main.getModelManager().getAllGames();
            ObservableList<String> game = FXCollections.observableArrayList();
            for(Game p: g){
                System.out.println(dataAtual);
                System.out.println(daysbefore);
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(p.getDate());
                if(dataAtual.after(date) && daysbefore.before(date)) {
                    String name = main.getModelManager().getNameOfGame(p);
                    game.add(name);
                }
            }
            System.out.println(game);
            return game;
        }catch (SQLException | ParseException e){
            System.out.println(e);
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

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        callToGame.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cmbGame.setItems(getGames());
        tableViewCallUpPlayers.setItems(getTable());
    }


    private ObservableList<TableCallUpGame> getTable() {
        try {
            Main main = new Main();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ObservableList<TableCallUpGame> tabela = FXCollections.observableArrayList();
            for(Player p: main.getModelManager().getPlayersAvailable()){
                TableCallUpGame tab = new TableCallUpGame(p.getNameUser(p.getEmail()), p.getEmail());
                tabela.add(tab);
            }
            return tabela;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAnyUserSelected(){
        for(TableCallUpGame tb : tableViewCallUpPlayers.getItems()){
            if(tb.getCheckBox().isSelected()){
                return true;
            }
        }
        return false;
    }
}
