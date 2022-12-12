package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.ModelManager;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;


public class Main extends Application {
    private ModelManager modelManager = new ModelManager();
    private static Stage stg;
    private static final Image image = new Image("http://rugbyaac.com/wp-content/uploads/2018/12/Logo-AACRugby.png");

    public ModelManager getModelManager() {
        return modelManager;
    }

    public Main() throws SQLException {}

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //RootPane rootPane = new RootPane(modelManager);
        stg = stage;
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        Scene scene = new Scene(root, 600, 480, Color.WHITE);

        stage.setScene(scene);

        stage.setTitle("Rugby Team Management");
        stage.getIcons().add(image);
        //stage.setMinWidth(400);
        stage.show();
    }
    
    public Stage getStg(){
        return stg;
    }

    public void setStg(Stage stage){
        stg = stage;
    }

    public void changeScene(String fxml){
        try{
            Parent pane = FXMLLoader.load(getClass().getResource(fxml));
            stg.getScene().setRoot(pane);
        } catch (IOException e){
            System.err.println(e);
        }
    }

}
