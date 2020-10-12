package Client;

import Commands.Command;
import Commands.CommandInvokerImp;
import Commands.CommandReceiverImp;
import Commands.ConcreteCommands.*;
import Commands.Utils.HashEncrypterImp;
import Interfaces.*;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;


/**
 * Клиентский модуль для Guice Dependency Injection.
 */
public class ClientModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CommandInvoker.class).to(CommandInvokerImp.class);
        bind(Sender.class).to(SenderImp.class);
        bind(Session.class).to(SessionImp.class);
        bind(CommandReceiver.class).to(CommandReceiverImp.class);
        bind(Receiver.class).to(ReceiverImp.class);
        bind(Decrypting.class).to(DecryptingImp.class);
        bind(HashEncrypter.class).to(HashEncrypterImp.class);


        Multibinder<Command> commandBinder = Multibinder.newSetBinder(binder(), Command.class);
        commandBinder.addBinding().to(Add.class);
        commandBinder.addBinding().to(Clear.class);
        commandBinder.addBinding().to(CountByGroupAdmin.class);
        commandBinder.addBinding().to(ExecuteScript.class);
        commandBinder.addBinding().to(Exit.class);
        commandBinder.addBinding().to(MaxByGroupAdmin.class);
        commandBinder.addBinding().to(Help.class);
        commandBinder.addBinding().to(MinBySemesterEnum.class);
        commandBinder.addBinding().to(Info.class);
        commandBinder.addBinding().to(RemoveLower.class);
        commandBinder.addBinding().to(RemoveGreater.class);
        commandBinder.addBinding().to(RemoveByID.class);
        commandBinder.addBinding().to(Show.class);
        commandBinder.addBinding().to(Head.class);
        commandBinder.addBinding().to(Update.class);
    }
}
