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
            io.println("File \"" + file.getName() + "\" not found!");
        } else {
            if (!io.addFileToFileStack(file)) {
                io.println("Recursion detected!");
                throw new WrongFileFormatException();
            }
            io.fillExecutionStack(file);
            io.println("Starting to execute " + file.getName());
        }
    }
}