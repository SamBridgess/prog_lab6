package ilya.lab.server.Utility;

import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Сommands.Command;

import java.io.IOException;
import java.util.HashMap;

/**
 * commands manager that executes passed commands and checks number of arguments
 */
public final class CommandsManager {
    private CommandsManager() {
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
     * @param commands      list of all commands
     * @param io            passed IOManager
     * @throws IOException
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    public static void execute(String command, String[] args, HashMap<String, Command> commands, IOManager io) throws IOException, WrongFileFormatException, CtrlDException {
        if (commands.containsKey(command)) {
            if (checkNumberOfArguments(commands.get(command).getNumberOfArguments(), args.length)) {
                commands.get(command).execute(args);
            } else {
                io.printWarning("Wrong number of arguments!");
                if (io.getIsFile()) {
                    throw new WrongFileFormatException();
                }
            }
        } else {
            io.printWarning("Command \"" + command + "\" not found!");
            if (io.getIsFile()) {
                throw new WrongFileFormatException();
            }
        }
    }

    /**
     * @return      returns commands manual
     */
    public static String getCommandsHelp() {
        return "help : вывести справку по доступным командам\n"
                + "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n"
                + "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n"
                + "add {element} : добавить новый элемент в коллекцию\n"
                + "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n"
                + "remove_by_id id : удалить элемент из коллекции по его id\n"
                + "clear : очистить коллекцию\n"
                + "save : сохранить коллекцию в файл\n"
                + "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n"
                + "exit : завершить программу (без сохранения в файл)\n"
                + "remove_first : удалить первый элемент из коллекции\n"
                + "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n"
                + "sort : отсортировать коллекцию в естественном порядке\n"
                + "filter_less_than_distance distance : вывести элементы, значение поля distance которых меньше заданного\n"
                + "print_ascending : вывести элементы коллекции в порядке возрастания\n"
                + "print_field_descending_distance : вывести значения поля distance всех элементов в порядке убывания";
    }

}
