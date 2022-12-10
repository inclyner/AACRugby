package gui.manager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ManagerMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAproveRequests;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnInsertUser;

    @FXML
    void onClickInsertUser(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("manager\\InsertUserView.fxml");

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void onClickDeleteUser(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("manager\\DeleteUserView.fxml");

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void onClickApprovalRequests(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("manager\\ApproveRequestsView.fxml");

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void initialize() {

        assert btnAproveRequests != null : "fx:id=\"btnAproveRequests\" was not injected: check your FXML file 'ManagerMainView.fxml'.";
        assert btnDeleteUser != null : "fx:id=\"btnDeleteUser\" was not injected: check your FXML file 'ManagerMainView.fxml'.";
        assert btnInsertUser != null : "fx:id=\"btnInsertUser\" was not injected: check your FXML file 'ManagerMainView.fxml'.";

    }

}
