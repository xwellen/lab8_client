package Interfaces;

import BasicClasses.Person;
import BasicClasses.StudyGroup;
import GUI.Controllers.MainStageController;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public interface CommandReceiver {
    void setAuthorizationData(String login, String password);

    Stage getPrimaryStage();

    void setPrimaryStage(Stage primaryStage);

    void help() throws IOException;

    void info() throws IOException, ClassNotFoundException, InterruptedException;

    void show() throws IOException, ClassNotFoundException, InterruptedException;

    void add() throws IOException, InterruptedException, ClassNotFoundException;

    void update(String ID) throws IOException, InterruptedException, ClassNotFoundException;

    void removeById(String ID) throws IOException, InterruptedException, ClassNotFoundException;

    void clear() throws IOException, InterruptedException, ClassNotFoundException;

    void exit() throws IOException;

    void head() throws IOException, InterruptedException, ClassNotFoundException;

    void removeGreater() throws IOException, InterruptedException, ClassNotFoundException;

    void removeLower() throws IOException, ClassNotFoundException, InterruptedException;

    void minBySemesterEnum() throws IOException, InterruptedException, ClassNotFoundException;

    void maxByGroupAdmin() throws IOException, InterruptedException, ClassNotFoundException;

    void countByGroupAdmin() throws IOException, InterruptedException, ClassNotFoundException;

    void register(String login, String password) throws IOException, InterruptedException, ClassNotFoundException;

    void executeScript(String path);

    void tryAuth(String login, String password) throws ClassNotFoundException, InterruptedException;

    void getCollection(String requireType) throws ClassNotFoundException, InterruptedException;

    String getLogin();

    List<String> getCommandsName();

    CommandInvoker getCommandInvoker();

    void setStudyGroup(StudyGroup studyGroup);

    void setGroupAdmin(Person groupAdmin);

    MainStageController getMainStageController();

    void setMainStageController(MainStageController mainStageController);

    ResourceBundle getCurrentBundle();

    void setCurrentBundle(ResourceBundle currentBundle);
}
