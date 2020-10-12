package Commands.ConcreteCommands;

import Commands.Command;
import Interfaces.CommandReceiver;
import com.google.inject.Inject;

import java.io.IOException;

/**
 * Конкретная команда выхода.
 */
public class Exit extends Command {
    private final CommandReceiver commandReceiver;

    @Inject
    public Exit (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде exit.");
        }
        commandReceiver.exit();
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    protected String writeInfo() {
        return String.format(commandReceiver.getCurrentBundle().getString("exitInf"),"exit");
    }
}
