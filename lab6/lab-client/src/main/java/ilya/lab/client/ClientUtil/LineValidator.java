package ilya.lab.client.ClientUtil;

import ilya.lab.client.IO.IOManager;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;

import java.io.IOException;
import java.util.HashMap;

/**
 * commands manager that executes passed commands and checks number of arguments
 */
public final class LineValidator {
    private LineValidator() {
    }

    /**
     *
     * @param given         given number of arguments
     * @param required      correct number of arguments
     * @return              returns whether the number of arguments is correct
     */
    private static boolean checkNumberOfArguments(int given, int required) {
        return given == required;
    }

    /**
     * executes commands
     *
     * @param command       command to execute
     * @param args          command's arguments
     * @param commandsInfo  list of all commands
     * @param io            passed IOManager
     * @throws IOException
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    public static boolean checkLine(String command, String[] args, HashMap<String, CommandRules> commandsInfo, IOManager io) throws IOException, WrongFileFormatException, CtrlDException {
        if (commandsInfo.containsKey(command)) {
            if (!checkNumberOfArguments(commandsInfo.get(command).getNumberOfArgs(), args.length)) {
                io.printWarning("Wrong number of arguments!");
                return false;
            }
            try {
                if (command.equals("update")) {
                    Long.parseLong(args[0]);
                }
                if (command.equals("remove_by_id")) {
                    Long.parseLong(args[0]);
                }
                if (command.equals("filter_less_than_distance")) {
                    Float.parseFloat(args[0]);
                }
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        } else {
            io.printWarning("Command \"" + command + "\" not found!");
            return false;
        }

        /*if (commandsInfo.containsKey(command)) {
            if (!checkNumberOfArguments(commandsInfo.get(command).getNumberOfArgs(), args.length)) {
                io.printWarning("Wrong number of arguments!");
                if (io.getIsFile()) {
                    throw new WrongFileFormatException();
                }
                return false;
            } else {
                io.printWarning("Command \"" + command + "\" not found!");
                if (io.getIsFile()) {
                    throw new WrongFileFormatException();
                }
                return false;
            }
        }
        return true;*/
    }
}
