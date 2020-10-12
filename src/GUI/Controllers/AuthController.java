package GUI.Controllers;

import Interfaces.CommandReceiver;
import Localization.LocalizationClass;
import Localization.localization_ru;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class AuthController {
    private CommandReceiver commandReceiver;
    private AnchorPane pane;
    private Scene scene;
    private Stage primaryStage;
    private final LocalizationClass currentLocalization = new localization_ru();

    public void setCommandReceiver(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


}
