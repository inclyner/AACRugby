package gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import logic.ModelManager;

public class RootPane extends BorderPane {
    ModelManager modelManager;

    public RootPane(ModelManager modelManager){
        this.modelManager = modelManager;

        createViews();
    }

    public void createViews(){
        StackPane stackPane = new StackPane(new LoginUI(modelManager));
        this.setCenter(stackPane);
    }
}
