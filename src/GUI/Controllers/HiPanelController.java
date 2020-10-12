package GUI.Controllers;

import Interfaces.CommandReceiver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HiPanelController {
    private CommandReceiver commandReceiver;
    private Stage primaryStage;
    private AnchorPane pane;
    private Scene scene;
    private ResourceBundle currentBundle = ResourceBundle.getBundle("bundles/bundle", new Locale("ru"));

    public void setCommandReceiver(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML private Button authUserBtn;
    @FXML private Button regUserWindowBtn;
    @FXML private TextField userLoginField;
    @FXML private PasswordField userPasswordField;
    @FXML private Button setRu;
    @FXML private Button setFr;
    @FXML private Button setNo;
    @FXML private Button setEsNI;

    @FXML
    void displayRegWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/Views/Register.fxml"));
            pane = (AnchorPane) loader.load();
            scene = new Scene(pane, 350, 390);
            primaryStage.setScene(scene);
            primaryStage.show();

            RegistrationController controller = loader.getController();
            controller.setCommandReceiver(commandReceiver);
            controller.setPrimaryStage(primaryStage);
            controller.setCurrentBundle(currentBundle);
            controller.changeLanguage();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void authUserAction(ActionEvent event) {
        if (!userLoginField.getText().isEmpty() && !userPasswordField.getText().isEmpty()) {
            try {
                commandReceiver.setCurrentBundle(currentBundle);
                commandReceiver.setPrimaryStage(primaryStage);
                commandReceiver.tryAuth(userLoginField.getText().trim(), userPasswordField.getText().trim());
            } catch (ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        } else showAlert(currentBundle.getString("didNotEnterUsernameOrPassword"));
    }


    public void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(currentBundle.getString("err"));

        alert.setHeaderText(null);
        alert.setContentText(alertMessage);

        alert.showAndWait();
    }

    public void changeToMain(Stage primaryStage, CommandReceiver commandReceiver, ResourceBundle resourceBundle) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/Views/MainStage.fxml"));
        pane = loader.load();
        scene = new Scene(pane, 1198, 494);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("StudyGroupProject");
        primaryStage.show();

        MainStageController controller = loader.getController();
        commandReceiver.setMainStageController(controller);
        controller.setCommandReceiver(commandReceiver);
        controller.setPrimaryStage(primaryStage);
        controller.setCurrentBundle(resourceBundle);
        controller.changeLanguage();
    }

    public void changeLanguage(){
        authUserBtn.setText(currentBundle.getString("logIn"));
        regUserWindowBtn.setText(currentBundle.getString("checkIn"));
        userLoginField.setPromptText(currentBundle.getString("enterYourLogin"));
        userPasswordField.setPromptText(currentBundle.getString("enterYourPassword"));
    }

    @FXML private void setFrLanguage(){
        currentBundle = ResourceBundle.getBundle("bundles/bundle", new Locale("fr"));
        commandReceiver.setCurrentBundle(currentBundle);
        setFr.setDisable(true);
        setRu.setDisable(false);
        setEsNI.setDisable(false);
        setNo.setDisable(false);
        changeLanguage();
    }

    @FXML private void setRuLanguage(){
        currentBundle = ResourceBundle.getBundle("bundles/bundle", new Locale("ru"));
        commandReceiver.setCurrentBundle(currentBundle);
        setRu.setDisable(true);
        setFr.setDisable(false);
        setEsNI.setDisable(false);
        setNo.setDisable(false);
        changeLanguage();
    }

    @FXML private void setNoLanguage(){
        currentBundle = ResourceBundle.getBundle("bundles/bundle", new Locale("no"));
        commandReceiver.setCurrentBundle(currentBundle);
        setNo.setDisable(true);
        setFr.setDisable(false);
        setRu.setDisable(false);
        setEsNI.setDisable(false);
        changeLanguage();
    }

    public void setEsNILanguage(){
        currentBundle = ResourceBundle.getBundle("bundles/bundle", new Locale("es", "NI"));
        commandReceiver.setCurrentBundle(currentBundle);
        setEsNI.setDisable(true);
        setFr.setDisable(false);
        setRu.setDisable(false);
        setNo.setDisable(false);
        changeLanguage();
    }

    public void setCurrentBundle(ResourceBundle currentBundle) {
        this.currentBundle = currentBundle;
    }

    public ResourceBundle getCurrentBundle() {
        return currentBundle;
    }
}