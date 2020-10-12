package Interfaces;

import Commands.Command;

import java.util.HashMap;
import java.util.List;

public interface CommandInvoker {
    void executeCommand(String[] commandName);

    HashMap<String, Command> getCommandMap();

    List<String> getCommandsName();
}
