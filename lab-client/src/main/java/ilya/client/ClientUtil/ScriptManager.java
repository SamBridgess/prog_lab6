package ilya.client.ClientUtil;

import ilya.client.IO.IOManager;
import ilya.common.Exceptions.WrongFileFormatException;

import java.io.File;
import java.io.IOException;

public final class ScriptManager {
    private ScriptManager() {

    }
    public static void addScript(IOManager io, String path) throws WrongFileFormatException, IOException {
        File file = new File(path);
        if (!file.exists()) {
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
