package gui.manager;
//not done not tested
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import logic.ChangeRequest;

import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;

public class ApproveRequestsController {

    @FXML
    private TableColumn<TableRequests, String> fromValue;

    @FXML
    private TableColumn<TableRequests, String> name;

    @FXML
    private TableColumn<TableRequests, String> requestedChange;

    @FXML
    private TableView<TableRequests> requestsTableView;
    @FXML
    private TableColumn<TableRequests, String> toValue;

    @FXML
    private Button btnApprove;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDeny;

    @FXML
    void onClickTableRow(MouseEvent event) {
        TableRequests value = requestsTableView.getSelectionModel().getSelectedItem();
        System.out.println(value.getName());
    }

    @FXML
    void onClickApproveBtn(ActionEvent event) {
        TableRequests value = requestsTableView.getSelectionModel().getSelectedItem();
        try {
            Main main = new Main();
            if (value == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nothing is selected!");
                alert.showAndWait();
            } else {
                Long ncc =  main.getModelManager().getnCCChange(value.getToValue(),value.getFromValue());
                String r = main.getModelManager().approveReq(ncc, true);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(r);
                alert.showAndWait();

                requestsTableView.setItems(getTable());
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    @FXML
    void onClickBackBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("manager\\ManagerMainView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickDenyBtn(ActionEvent event) throws SQLException {
        TableRequests value = requestsTableView.getSelectionModel().getSelectedItem();
        try {
            Main main = new Main();
            if (value == null) {
                System.out.println("Nothing is selected!");
            } else {
                Long ncc =  main.getModelManager().getnCCChange(value.getToValue(),value.getFromValue());
                main.getModelManager().approveReq(ncc, false);
                requestsTableView.setItems(getTable());
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    @FXML
    void initialize() throws SQLException {
        fromValue.setCellValueFactory(new PropertyValueFactory<>("fromValue"));
        toValue.setCellValueFactory(new PropertyValueFactory<>("toValue"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        requestedChange.setCellValueFactory(new PropertyValueFactory<>("requestedChange"));
        requestsTableView.setItems((getTable()));
    }

    private ObservableList<TableRequests> getTable() throws SQLException {
        Main main = new Main();
        ObservableList<TableRequests> tabela = FXCollections.observableArrayList();
        ArrayList<ChangeRequest> requests = main.getModelManager().getAllRequests();

        for (ChangeRequest changeRequest: requests){
            TableRequests tab = new TableRequests(main.getModelManager().getNameUserNcc(String.valueOf(changeRequest.getPlayerCC())), "Email", changeRequest.getOldInfo(), changeRequest.getNewInfo());
            tabela.add(tab);
        }

        return tabela;
    }
}
