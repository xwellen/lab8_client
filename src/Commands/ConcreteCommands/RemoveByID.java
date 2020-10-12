package Commands.ConcreteCommands;

import Commands.Command;
import Interfaces.CommandReceiver;
import com.google.inject.Inject;

import java.io.IOException;

/**
 * Конкретная команда удаления по ID.
 */
public class RemoveByID extends Command {
    private static final long serialVersionUID = 32L;
    transient private final CommandReceiver commandReceiver;

    @Inject
    public RemoveByID (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length == 2) { commandReceiver.removeById(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    protected String writeInfo() {
        return String.format(commandReceiver.getCurrentBundle().getString("removeByID"),"remove_by_id");
    }
}
