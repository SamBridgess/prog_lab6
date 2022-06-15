package ilya.lab.server.Сommands;

import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Utility.LineExecuter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * execute_script command
 */
public class ExecuteScriptCommand extends Command {
    private final HashMap<String, Command> commands;

    public ExecuteScriptCommand(IOManager io, HashMap<String, Command> commands) {
        super(1, io);
        this.commands = commands;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @throws IOException
     * @throws CtrlDException
     * @throws WrongFileFormatException
     */
    @Override
    public void execute(String[] args) throws IOException, CtrlDException, WrongFileFormatException {
        File file = new File(args[0]);
        if (!file.exists()) {
            getIOManager().printWarning("File \"" + file.getName() + "\" not found!");
            return;
        }
        try {
            if (!getIOManager().addFileToFileStack(file)) {
                getIOManager().printWarning("Recursion detected!");
                throw new WrongFileFormatException();
            }
            getIOManager().fillExecutionStack(file);
            getIOManager().printConfirmation("Starting to execute " + file.getName());
            while (!getIOManager().isLastFileExecuted()) {
                LineExecuter.executeLine(getIOManager().getNextLine(), commands, getIOManager());
            }
        } finally {
            getIOManager().popFile();
        }
        getIOManager().printConfirmation(file.getName() + " executed successfully");
    }
}
