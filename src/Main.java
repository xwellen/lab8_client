import Client.ClientModule;
import Client.DecryptingImp;
import GUI.Controllers.HiPanelController;
import Interfaces.CommandReceiver;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.*;
import javafx.stage.Stage;
import java.net.URL;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new ClientModule());
        CommandReceiver commandReceiver = injector.getInstance(CommandReceiver.class);

        FXMLLoader loader = new FXMLLoader(DecryptingImp.class.getResource("/GUI/Views/HiPanel.fxml"));
        Parent root = loader.load();
        HiPanelController ctrl = (loader.getController());
        ctrl.setCommandReceiver(commandReceiver);
        ctrl.setPrimaryStage(primaryStage);
        primaryStage.setTitle("StudyGroupProject");
        primaryStage.setScene(new Scene(root, 350, 350));
        primaryStage.show();

//        URL music = getClass().getResource("GUI/Music/HiMark.mp3");
//        Media media = new Media(music.toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        Thread.sleep(100);
//        mediaPlayer.play();
    }
}