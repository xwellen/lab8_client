package Commands.ConcreteCommands;

import Commands.Command;
import Interfaces.CommandReceiver;
import com.google.inject.Inject;

import java.io.IOException;

/**
 * Конкретная команда помощи.
 */
public class Help extends Command {
    private static final long serialVersionUID = 32L;
    transient private final CommandReceiver commandReceiver;

    @Inject
    public Help (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде help.");
        }
        commandReceiver.help();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    protected String writeInfo() {
        return String.format(commandReceiver.getCurrentBundle().getString("helpInf"),"help");
    }
}
