package Commands.ConcreteCommands;

import Commands.Command;
import Interfaces.CommandReceiver;
import com.google.inject.Inject;

import java.io.IOException;

/**
 * Конкретная команда выполнения скрипта.
 */
public class ExecuteScript extends Command {
    private final CommandReceiver commandReceiver;
    private String path;

    @Inject
    public ExecuteScript(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) throws StackOverflowError, IOException {
        try {
            if (args.length == 2) { path = args[1]; commandReceiver.executeScript(args[1]); }
            else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
        } catch (StackOverflowError ex) {
            System.out.println("Ошибка! Обнаружен выход за пределы стека.");
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    protected String writeInfo() {
        return String.format(commandReceiver.getCurrentBundle().getString("executeScriptInf"),"execute_script");
    }

    public String getPath() {
        return path;
    }
}
