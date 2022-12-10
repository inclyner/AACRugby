package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TextField tfEmail;
    @FXML
    private Button loginBtn;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrongLogIn;

    @FXML
    void onClickLogin(ActionEvent event) {
        try {
            Main main = new Main();
            //System.out.println("Sucesso");

            //main.changeScene("player\\playerMainView.fxml");

            if(main.getModelManager().login(tfEmail.getText().toString(), password.getText().toString())){
                int typeOfUser = main.getModelManager().checksTypeUser(tfEmail.getText(),password.getText());
                // doctor
                if(typeOfUser == 1) {
                    main.changeScene("manager\\ManagerMainView.fxml");
                }
                // player
                else if(typeOfUser == 2){
                    main.changeScene("player\\playerMainView.fxml");
                }
                // coach
                else if(typeOfUser == 3){
                    main.changeScene("player\\playerMainView.fxml");
                }
                // manager
                else if(typeOfUser == 4){
                    main.changeScene("manager\\ManagerMainView.fxml");
                }
            }
            else if(tfEmail.getText().isEmpty() || password.getText().isEmpty())
                wrongLogIn.setText("You must fill all credentials");
            else
                wrongLogIn.setText("Email or Password incorrect!");


        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void onClickImage(MouseEvent event) {
        try {
            String url_open ="https://www.academica-oaf.pt/";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        wrongLogIn.setText("");
        assert tfEmail != null : "fx:id=\"cc\" was not injected: check your FXML file 'loginView.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'loginView.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginView.fxml'.";

    }

}




