package ilya.lab.server.Utility;

import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.common.IO.IOManager;
import ilya.lab.server.Сommands.Command;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * executes a scanned line
 */
public final class LineExecuter {
    private LineExecuter() {
    }

    /**
     * tries to execute a scanned line
     *
     * @param s                 String with a line to execute
     * @param commands          list of all commands
     * @param io                passed IOManager
     * @throws IOException
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    public static void executeLine(String s, HashMap<String, Command> commands, IOManager io) throws IOException, WrongFileFormatException, CtrlDException {
        String[] words = s.trim().split("\\s++");

        String command = words[0];
        String[] args = Arrays.copyOfRange(words, 1, words.length);

        if (Objects.equals(command, "")) {
            return;
        }
        CommandsManager.execute(command, args, commands, io);
    }
}
