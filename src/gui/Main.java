package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.ModelManager;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.sql.SQLException;


public class Main extends Application {
    private ModelManager modelManager = new ModelManager();
    private static Stage stg;

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

        Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        Scene scene = new Scene(root, 600, 400, Color.WHITE);
        Image image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQgWV1T_EgX1G_nTPaUWR-AcFgvzdKS6Yk-dhQ-2QgRw&s");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Rugby Team Management");
        stage.getIcons().add(image);
        stage.setMinWidth(400);
        stage.show();
    }
}
