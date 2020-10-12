package Commands.ConcreteCommands;

import Commands.Command;
import Interfaces.CommandReceiver;
import com.google.inject.Inject;

import java.io.IOException;

/**
 * Конкретная команда показа содержания коллекции.
 */
public class Show extends Command {
    private static final long serialVersionUID = 32L;
    transient private final CommandReceiver commandReceiver;

    @Inject
    public Show (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде show.");
        }
        commandReceiver.show();
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    protected String writeInfo() {
        return String.format(commandReceiver.getCurrentBundle().getString("showInf"),"show");
    }
}
