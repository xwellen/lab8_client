package Client;

import BasicClasses.StudyGroup;
import Commands.SerializedCollection;
import Commands.SerializedCommands.*;
import Commands.SerializedResAuth;
import GUI.Controllers.HiPanelController;
import GUI.Controllers.MainStageController;
import GUI.Controllers.RegistrationController;
import Interfaces.CommandReceiver;
import Interfaces.Decrypting;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DecryptingImp implements Decrypting {
    private final CommandReceiver commandReceiver;

    @Inject
    public DecryptingImp(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void decrypt(Object o) throws IOException {
        if (o instanceof SerializedMessage) {
            SerializedMessage serializedMessage = (SerializedMessage) o;
            FXMLLoader loader = new FXMLLoader(DecryptingImp.class.getResource("/GUI/Views/MainStage.fxml"));
            Parent sceneFXML = loader.load();
            MainStageController ctrl = (loader.getController());
            if (serializedMessage.getMessage() != null) ctrl.showInfo(translate(serializedMessage.getMessage()));
            else {
                if (serializedMessage.getLinkedList().isEmpty()){
                    ctrl.showInfo(commandReceiver.getCurrentBundle().getString("collectionIsEmpty"));
                } else ctrl.showInfo(serializedMessage.getLinkedList()
                        .stream().map(studyGroup -> studyGroup.toLanguageString(commandReceiver.getCurrentBundle())).collect(Collectors.joining()));
            }
        }
        if (o instanceof SerializedResAuth) {
            SerializedResAuth serializedResAuth = (SerializedResAuth) o;

            if (serializedResAuth.getType().equals("auth")) {
                FXMLLoader loader = new FXMLLoader(DecryptingImp.class.getResource("/GUI/Views/HiPanel.fxml"));
                Parent sceneFXML = loader.load();
                HiPanelController ctrl = (loader.getController());
                ctrl.setCommandReceiver(commandReceiver);
                if (serializedResAuth.getRes()) {
                    try {
                        ctrl.changeToMain(commandReceiver.getPrimaryStage(), commandReceiver, commandReceiver.getCurrentBundle());
                        commandReceiver.getCollection("return_collection_init");
                    } catch (ClassNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                } else ctrl.showAlert(commandReceiver.getCurrentBundle().getString("unableToLogIn"));
            }

            if (serializedResAuth.getType().equals("reg")) {
                FXMLLoader loader = new FXMLLoader(DecryptingImp.class.getResource("/GUI/Views/Register.fxml"));
                Parent sceneFXML = loader.load();
                RegistrationController ctrl = (loader.getController());
                ctrl.setCommandReceiver(commandReceiver);
                if (serializedResAuth.getRes()) {
                    try {
                        ctrl.changeToMain(commandReceiver.getPrimaryStage(), commandReceiver, commandReceiver.getCurrentBundle());
                        ctrl.showSuccessMessage(commandReceiver.getCurrentBundle().getString("userSuccessfullyRegistered"));
                        commandReceiver.getCollection("return_collection_init");
                    } catch (ClassNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                } else ctrl.showAlert(commandReceiver.getCurrentBundle().getString("aUserWithThisLoginAlreadyExists"));
            }
        }

        if (o instanceof SerializedCollection) {
            SerializedCollection serializedCollection = (SerializedCollection) o;
            LinkedList<StudyGroup> linkedList = serializedCollection.getLinkedList();
            List<List<Integer>> idElementsAllUsers = serializedCollection.getIdElementsAllUsers();
            if (serializedCollection.getRequireType().equals("init")) {

                new Thread(()->{
                   while (!Thread.currentThread().isInterrupted()) {
                        try {
                            commandReceiver.getCollection("regular");
                            Thread.sleep(3000);
                       } catch (InterruptedException | ClassNotFoundException e) {
                           e.printStackTrace();
                        }
                    }
                }).start();

                MainStageController mainStageController = commandReceiver.getMainStageController();

                mainStageController.setCommandReceiver(commandReceiver);
                mainStageController.setCollection(linkedList, idElementsAllUsers);

            } else {
                MainStageController mainStageController = commandReceiver.getMainStageController();
                mainStageController.setCollection(linkedList, idElementsAllUsers);
                mainStageController.updateTable();
                mainStageController.visual();
            }
        }
    }

    @Override
    public void requireCollection() {
        while (true) {
            try {
                commandReceiver.getCollection("regular");
                Thread.sleep(3000);
            } catch (InterruptedException | ClassNotFoundException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
    }

    private String translate(String message){
        switch (message.toLowerCase()) {
            case "элемент добавлен": return commandReceiver.getCurrentBundle().getString("itemAdded");
            case "элемент не добавлен": return commandReceiver.getCurrentBundle().getString("itemNotAdded");
            case "элемент обновлен": return commandReceiver.getCurrentBundle().getString("itemUpdated");
            case "элемент не обновлен": return commandReceiver.getCurrentBundle().getString("itemNotUpdated");
            case "элемент создан другим пользователем": return commandReceiver.getCurrentBundle().getString("itemCreatedByAnotherUser");
            case "элемента с таким ID нет в коллекции": return commandReceiver.getCurrentBundle().getString("anItemWithThisIdIsNotInTheCollection");
            case "некорректный аргумент": return commandReceiver.getCurrentBundle().getString("invalidArgument");
            case "элемент удален": return commandReceiver.getCurrentBundle().getString("itemDeleted");
            case "ваши элементы коллекции удалены": return commandReceiver.getCurrentBundle().getString("yourCollectionItemsHaveBeenDeleted");
            case "таких элементов не найдено": return commandReceiver.getCurrentBundle().getString("noSuchItemsFound");
            case "элемент не прошел валидацию на стороне сервера": return commandReceiver.getCurrentBundle().getString("itemFailedServerSideValidation");
        }

        if (message.contains("removeElements")){
            message = message.replace("removeElements", commandReceiver.getCurrentBundle().getString("deletedItemsWithID"));
        } else if (message.contains("ошибка при удалении из бд элемента с id=")){
            message = message.replace("Ошибка при удалении из бд элемента с id=",
                    commandReceiver.getCurrentBundle().getString("anErrorOccurredWhileDeletingAnElementWithIdFromTheDatabase"));
        } else if (message.contains("%data")){
            message = message.replace("%type", commandReceiver.getCurrentBundle().getString("collectionType"));
            message = message.replace("%data", commandReceiver.getCurrentBundle().getString("collectionInitializationDate"));
            message = message.replace("%size", commandReceiver.getCurrentBundle().getString("collectionLength"));
        }
        return message;
    }
}
