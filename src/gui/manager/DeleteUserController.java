package gui.manager;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
            ArrayList<String> names = new ArrayList<>();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Users");
            alert.setContentText("Are you sure you want to delete the selected users?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.CANCEL)
                return;
            else if (option.get() == ButtonType.OK){

                for(TableDeleteSetter tb : deleteTableView.getItems()){
                    if(tb.getCheckBox().isSelected()){
                        names.add(tb.getName());
                    }
                }
                System.out.println(names);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void initialize() {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeOfUser.setCellValueFactory(new PropertyValueFactory<>("typeOfUser"));
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        deleteTableView.setItems(getTable());
    }

    private ObservableList<TableDeleteSetter> getTable() {
        ObservableList<TableDeleteSetter> tabela = FXCollections.observableArrayList();
        for (int i = 0; i < 20; i++) {

            CheckBox checkBox = new CheckBox();

            TableDeleteSetter data = new TableDeleteSetter("Rodrigo", "Rodrigo","rodrigo@aac.pt");
            tabela.add(data);
        }

        return tabela;
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
