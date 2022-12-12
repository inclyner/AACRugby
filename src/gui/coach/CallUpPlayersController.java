package gui.coach;
//not done not Tested
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Users.Player;
import gui.Main;
import gui.manager.TableDeleteSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
            //for()

            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){
                for(TableCallUpGame tb : tableViewCallUpPlayers.getItems()){
                    if(tb.getCheckBox().isSelected()){
                        nCC.add(Long.parseLong(main.getModelManager().getNcc(tb.getEmail())));
                    }
                }
                String a = main.getModelManager().callup(nCC);
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

    @FXML
    void initialize() {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        callToGame.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableViewCallUpPlayers.setItems(getTable());
    }

    private ObservableList<TableCallUpGame> getTable() {
        try {
            Main main = new Main();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ObservableList<TableCallUpGame> tabela = FXCollections.observableArrayList();
            //System.out.println(players);
            for(Player p: players){
                String name = main.getModelManager().getNameUser(p.getEmail());
                TableCallUpGame tab = new TableCallUpGame (p.getNameUser(p.getEmail()), p.getEmail());
                tabela.add(tab);
            }
            return tabela;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*try {
            Main main = new Main();
            //ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ObservableList<TableCallUpGame> tabela = FXCollections.observableArrayList();
            //System.out.println(players);
            /*
            for(Player player : players){
                TableCallUpGame tab = new TableCallUpGame(player.getEmail());
                tabela.add(tab);
            }


            for (int i = 0; i < 20; i++) {

                TableCallUpGame data = new TableCallUpGame("Rodrigo");
                tabela.add(data);
            }

            return tabela;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
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
