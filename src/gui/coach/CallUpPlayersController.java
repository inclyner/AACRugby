package gui.coach;
//not done not Tested
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Users.Player;
import gui.Main;
import gui.manager.TableDeleteSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<TableCallUpGame> tableViewCallUpPlayers;

    @FXML
    private CheckBox chbSelectAllPlayers;

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
    void onClickBtnSave(ActionEvent event) {

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

        tableViewCallUpPlayers.setItems(getTable());
    }

    private ObservableList<TableCallUpGame> getTable() {
        try {
            Main main = new Main();
            //ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ObservableList<TableCallUpGame> tabela = FXCollections.observableArrayList();
            //System.out.println(players);
            /*
            for(Player player : players){
                TableCallUpGame tab = new TableCallUpGame(player.getEmail());
                tabela.add(tab);
            }*/


            for (int i = 0; i < 20; i++) {

                TableCallUpGame data = new TableCallUpGame("Rodrigo");
                tabela.add(data);
            }

            return tabela;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
