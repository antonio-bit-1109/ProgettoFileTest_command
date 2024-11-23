package command.implem;

import command.interf.command;


public class ExitCommand implements command {

    @Override
    public void Execute() {
        System.exit(0);
    }
}
