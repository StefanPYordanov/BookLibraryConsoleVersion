package org.example.commands;

public class ExitCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Thank you for using Book Library!");
        System.exit(0);
    }
}
