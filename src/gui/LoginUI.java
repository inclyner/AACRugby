package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.ModelManager;

public class LoginUI extends BorderPane {
    ModelManager model;
    TextField tfLogin;
    TextField tfPassword;

    public LoginUI(ModelManager model) {
        this.model = model;
        createLayout();
        registerHandlers();
        update();
    }

    private void update() {
        this.setVisible(model != null);
    }

    private void createLayout   () {
        tfLogin = new TextField();
        tfPassword = new TextField();

    }
    private void registerHandlers() {

    }

    public void addEspecificationsButtons(Button b){
        b.setFont(new Font("Fira Code", 14));
        b.setTextFill(Color.web("#0047AB"));
    }
    public void addEspecificationsToolbar(HBox t){
        t.setSpacing(5);
    }

}
