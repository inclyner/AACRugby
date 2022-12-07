package gui.manager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.Icon;
import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ManagerMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML // fx:id="icon"
    private Icon icon; // Value injected by FXMLLoader

    @FXML
    private Button btnAproveRequests;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnInsertUser;

    @FXML
    void onClickAccountIcon(MouseEvent event) {
        try {
            Main main = new Main();
            main.showNewWindow("UserPersonalDataView.fxml","Personal Data");
            //main.changeScene("manager\\InsertUserView.fxml");

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void onClickInsertUser(MouseEvent event) {
        try {
            Main main = new Main();
            main.showNewWindow("manager\\InsertUserView.fxml","Insert new User");
            //main.changeScene("manager\\InsertUserView.fxml");

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void onClickDeleteUser(MouseEvent event) {
        try {
            Main main = new Main();
            main.showNewWindow("manager\\DeleteUserView.fxml","Delete new User");
            //main.changeScene("manager\\DeleteUserView.fxml");

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
