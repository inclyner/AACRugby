package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.ModelManager;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField cc;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    void onClickLogin(ActionEvent event) {
        try {
            Main main = new Main();
            if(main.getModelManager().login(cc.getText().toString(), password.getText().toString()))
                System.out.println("Sucesso");
            else
                System.out.println("erro");
        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void initialize() {
        assert cc != null : "fx:id=\"cc\" was not injected: check your FXML file 'loginView.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'loginView.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginView.fxml'.";

    }

}




