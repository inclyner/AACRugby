package gui.manager;
// not done not tested
import Users.Coach;
import Users.Doctor;
import Users.Manager;
import Users.Player;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteUserController {

    @FXML
    private TableView<TableDeleteSetter> deleteTableView;

    @FXML
    private TableColumn<TableDeleteSetter, CheckBox> checkBox;

    @FXML
    private TableColumn<TableDeleteSetter, String> name;

    @FXML
    private TableColumn<TableDeleteSetter, String> typeOfUser;

    @FXML
    private TableColumn<TableDeleteSetter, String> email;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    void onBackBtnClick(ActionEvent event) {
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
                    main.changeScene("manager\\ManagerMainView.fxml");
            } else
                main.changeScene("manager\\ManagerMainView.fxml");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSaveBtnClick(ActionEvent event) {
        try {
            Main main = new Main();
            ArrayList<String> emails = new ArrayList<>();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Users");
            alert.setContentText("Are you sure you want to delete the selected users?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){

                for(TableDeleteSetter tb : deleteTableView.getItems()){
                    if(tb.getCheckBox().isSelected()){
                        emails.add(tb.getEmail());
                    }
                }

                main.getModelManager().deleteUsers(emails);
                System.out.println(emails);
                deleteTableView.setItems(getTable());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeOfUser.setCellValueFactory(new PropertyValueFactory<>("typeOfUser"));
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        deleteTableView.setItems(getTable());
    }

    private ObservableList<TableDeleteSetter> getTable() {
        try {
            Main main = new Main();
            ArrayList<Player> players = main.getModelManager().getAllPlayer();
            ArrayList<Doctor> doctors = main.getModelManager().getAllDoctor();
            ArrayList<Coach> coaches = main.getModelManager().getAllCoach();
            ArrayList<Manager> managers = main.getModelManager().getAllManager();
            ObservableList<TableDeleteSetter> tabela = FXCollections.observableArrayList();
            System.out.println(players);
            for(Player p: players){
                String name = main.getModelManager().getNameUser(p.getEmail());
                TableDeleteSetter tab = new TableDeleteSetter(name,"Player",p.getEmail());
                tabela.add(tab);
            }
            for(Coach p: coaches){
                String name = main.getModelManager().getNameUser(p.getEmail());
                TableDeleteSetter tab = new TableDeleteSetter(name,"Coach",p.getEmail());
                tabela.add(tab);
            }
            for(Manager p: managers){
                String name = main.getModelManager().getNameUser(p.getEmail());
                TableDeleteSetter tab = new TableDeleteSetter(name,"Manager",p.getEmail());
                tabela.add(tab);
            }
            for(Doctor p: doctors){
                String name = main.getModelManager().getNameUser(p.getEmail());
                TableDeleteSetter tab = new TableDeleteSetter(name,"Doctor",p.getEmail());
                tabela.add(tab);
            }

            return tabela;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAnyUserSelected(){
        for(TableDeleteSetter tb : deleteTableView.getItems()){
            if(tb.getCheckBox().isSelected()){
                return true;
            }
        }
        return false;
    }
}
