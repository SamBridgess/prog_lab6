package ilya.lab.client.ClientUtil;

import ilya.lab.client.IO.IOManager;
import ilya.lab.common.Exceptions.WrongFileFormatException;

import java.io.File;
import java.io.IOException;

public class ScriptManager {
    private IOManager io;
    public ScriptManager(IOManager io) {
        this.io = io;
    }
    public void addScript(String path) throws WrongFileFormatException, IOException {
        File file = new File(path);
        if(!file.exists()) {
            io.printWarning("File \"" + file.getName() + "\" not found!");
        } else {
            if (!io.addFileToFileStack(file)) {
                io.printWarning("Recursion detected!");
                throw new WrongFileFormatException();
            }
            io.fillExecutionStack(file);
            io.printConfirmation("Starting to execute " + file.getName());
        }
    }
}