package gui.manager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label lbHello;

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
            //main.getModelManager().getAllPlayer();
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
       try {
            Main main = new Main();
            lbHello.setText("Hello manager " + main.getModelManager().getNameLogged() +"!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
